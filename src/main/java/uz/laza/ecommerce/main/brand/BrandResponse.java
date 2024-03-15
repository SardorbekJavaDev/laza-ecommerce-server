package uz.laza.ecommerce.main.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.attach.Attach;
import uz.laza.ecommerce.main.attach.AttachResponse;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {

    private Integer id;
    private String name;
    private AttachResponse attachResponse;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
