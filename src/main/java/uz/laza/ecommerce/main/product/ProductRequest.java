package uz.laza.ecommerce.main.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.user.GenderType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String title;
    private String description;
    private Long price;
    private ProductSize size;
    private Integer count;
    private GenderType genderType;
    private Integer attachId;

}
