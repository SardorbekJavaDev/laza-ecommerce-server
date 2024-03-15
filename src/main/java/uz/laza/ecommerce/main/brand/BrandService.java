package uz.laza.ecommerce.main.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.attach.Attach;
import uz.laza.ecommerce.main.attach.AttachResponse;
import uz.laza.ecommerce.main.attach.AttachService;
import uz.laza.ecommerce.main.product.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;
    private final AttachService attachService;

    public BrandResponse create(BrandRequest request) {
        Brand address = new Brand();
        address.setName(request.getName());
        address.setAttachId(request.getAttachId());
        return toDTO(repository.save(address));
    }

    public BrandResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public BrandResponse update(Integer id, BrandRequest request) {
        Brand address = get(id);
        address.setName(request.getName());
        address.setAttachId(request.getAttachId());
        return toDTO(repository.save(address));
    }

    public BrandResponse delete(Integer id) {
        Brand address = get(id);
        repository.delete(address);
        return null;
    }

    public PageImpl<BrandResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<BrandResponse> dtoList = new ArrayList<>();

        Page<Brand> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Brand get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    //todo log
                    return new ItemNotFoundException("Not found !");
                });
    }

    public BrandResponse toDTO(Brand entity) {
        Attach attach = attachService.getById(entity.getAttachId());
        return BrandResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .attachResponse(AttachResponse.builder()
                        .id(attach.getId())
                        .path(attach.getPath())
                        .originalName(attach.getOriginName())
                        .url(attach.getUrl())
                        .createdDate(attach.getCreatedDate())
                        .fileSize(attach.getSize())
                        .build())
                .createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModified())
                .build();
    }

}
