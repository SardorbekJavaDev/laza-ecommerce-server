package uz.laza.ecommerce.main.attach;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.laza.ecommerce.exception.AppBadRequestException;
import uz.laza.ecommerce.exception.ItemNotFoundException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachService {

    private final AttachRepository attachRepository;

    @Value("${application.attach.upload.folder}")
    private String attachFolder;

    @Value("${application.server.domain.name}")
    private String domainName;


    public AttachResponse upload(MultipartFile file) {
        Attach entity = new Attach();
        String pathFolder = getDateFolder();

        File folder = new File(attachFolder + "/" + pathFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

            byte[] bytes = file.getBytes();

            entity = saveAttach(entity, pathFolder, extension, file);

            Path url = Paths.get(folder.getAbsolutePath() + "/" + entity.getId() + "." + extension);

            Files.write(url, bytes);

            return toDTO(entity);
        } catch (IOException | RuntimeException e) {
            log.warn("Cannot Upload");
            delete(entity.getId());
            throw new AppBadRequestException(e.getMessage());
        }
    }

    public PageImpl<AttachResponse> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<AttachResponse> dtoList = new ArrayList<>();

        Page<Attach> entityPage = attachRepository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public byte[] open(Integer id) {
        byte[] data;

        Attach entity = getById(id);
        String pathFolder = entity.getPath() + "/" + id + "." + entity.getExtension();

        try {
            Path path = Paths.get(attachFolder + "/" + pathFolder);
            data = Files.readAllBytes(path);
            return data;
        } catch (IOException e) {
            log.warn("Cannot Open");
            return new byte[0];
        }
    }

    public ResponseEntity<?> download(Integer id) {
        try {
            Attach entity = getById(id);
            String path = entity.getPath() + "/" + id + "." + entity.getExtension();

            Path file = Paths.get(attachFolder + "/" + path);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + entity.getOriginName() + "\"")
                        .body(resource);
            } else {
                log.warn("Cannot Read");
                throw new AppBadRequestException("Could not read the file!");
            }

        } catch (MalformedURLException e) {
            log.warn("Cannot Download");
            throw new AppBadRequestException("Error" + e.getMessage());
        }
    }

    public Boolean delete(Integer id) {
        Attach entity = getById(id);

        File file = new File(attachFolder + "/" + entity.getPath() +
                "/" + entity.getId() + "." + entity.getExtension());

        if (file.delete()) {
            attachRepository.deleteById(entity.getId());
            return true;
        } else {
            log.warn("Cannot Read");
            attachRepository.deleteById(entity.getId());
            throw new AppBadRequestException("Could not read the file!");
        }
    }

    public Attach getById(Integer id) {
        return attachRepository.findById(id).orElseThrow(() -> {
            log.warn("Not found {}", id);
            return new ItemNotFoundException("Not found!");
        });
    }

    public AttachResponse toDTO(Attach entity) {
        AttachResponse response = new AttachResponse();
        response.setId(entity.getId());
        response.setPath(entity.getPath());
        response.setOriginalName(entity.getOriginName());
        response.setUrl(domainName + "attach/download/" + entity.getId());
        response.setCreatedDate(entity.getCreatedDate());
        response.setFileSize(entity.getSize());
        return response;
    }

    public Attach saveAttach(Attach entity, String pathFolder, String extension, MultipartFile file) {
        entity.setPath(pathFolder);
        entity.setOriginName(file.getOriginalFilename());
        entity.setExtension(extension);
        entity.setSize(file.getSize());
        attachRepository.save(entity);
        return entity;
    }

    public String toOpenUrl(Integer id) {
        return domainName + "attach/open/" + id;
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public String getDateFolder() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day;
    }
}
