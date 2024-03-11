package uz.laza.ecommerce.main.mail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
@Api(tags = "Email")
public class EmailController {

    private final EmailService emailService;

    /**
     * ADMIN
     */

    @Deprecated
    @ApiOperation(value = "List", notes = "Method used for get list of email's history",
            authorizations = @Authorization(value = "JWT Token"))
    @GetMapping("/")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("LIST page={} size={}", page, size);
        return ResponseEntity.ok(emailService.paginationList(page, size));
    }

    @Deprecated
    @ApiOperation(value = "Delete", notes = "Method used for delete email history",
            authorizations = @Authorization(value = "JWT Token"))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.info("DELETE {}", id);
        return ResponseEntity.ok(emailService.delete(id));
    }

    @Deprecated
    @ApiOperation(value = "List", notes = "Method used for get list of email's history by user email and message type",
            authorizations = @Authorization(value = "JWT Token"))
    @GetMapping("/special")
    public ResponseEntity<?> special(@RequestBody EmailRequest request) {
        log.info("LIST id={} page={} size={}", request.getToEmail(), request.getPage(), request.getSize());
        return ResponseEntity.ok(emailService.special(request));
    }
}
