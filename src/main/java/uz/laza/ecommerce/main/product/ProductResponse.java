package uz.laza.ecommerce.main.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.attach.Attach;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String name;
    private String title;
    private String description;
    private Long price; // This price is based on the original price
    private ProductSize size;
    private Integer count;
    private Integer attachId;
    private LocalDateTime createDate;

}
