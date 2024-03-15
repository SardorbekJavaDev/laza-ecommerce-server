package uz.laza.ecommerce.main.attach;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.laza.ecommerce.base.Base;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachResponse extends Base {

    private Integer id;
    private String path;
    private String extension;
    private String originalName;
    private Long fileSize;
    private String url;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

    public AttachResponse(String url) {
        this.url = url;
    }
}
