package uz.laza.ecommerce.main.attach;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import uz.laza.ecommerce.main.income.IncomeService;

@Slf4j
@RestController
@RequestMapping("/api/v1/attach")
@Api(tags = "Attach")
public class AttachController {


    @Autowired
    private AttachService attachService;

    /**
     * PUBLIC
     */

    @ApiOperation(value = "Upload", notes = "Method used for upload files")
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) {
        log.info("/upload");
        return ResponseEntity.ok(attachService.upload(file));
    }

    @ApiOperation(value = "Open", notes = "Method used for open files")
    @GetMapping(value = "/open/{id}", produces = MediaType.ALL_VALUE)
    public byte[] open(@PathVariable("id") Integer id) {
        log.info("/open/{id} {}", id);
        return attachService.open(id);
    }

    @ApiOperation(value = "Download", notes = "Method used for download files")
    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable("id") Integer id) {
        log.info("/download/{id} {}", id);
        return attachService.download(id);
    }

    /**
     * ADMIN
     */

    @ApiOperation(value = "List", notes = "Method used for get list of files from database",
            authorizations = @Authorization(value = "JWT Token"))
    @GetMapping("/adm")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                  HttpServletRequest request) {
        log.info("LIST page={} size={}", page, size);
//        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.list(page, size));
    }

    @ApiOperation(value = "Delete", notes = "Method used for delete files from local and database",
            authorizations = @Authorization(value = "JWT Token"))
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        log.info("DELETE {}", id);
//        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.delete(id));
    }

}
