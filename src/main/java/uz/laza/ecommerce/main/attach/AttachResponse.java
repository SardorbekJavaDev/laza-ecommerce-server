package uz.laza.ecommerce.main.attach;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.laza.ecommerce.base.Base;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachResponse extends Base {

    private String path;

    private String extension;

    private String originalName;

    private Long fileSize;

    private String url;

    public AttachResponse(String url) {
        this.url = url;
    }
}
