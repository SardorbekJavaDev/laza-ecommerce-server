package uz.laza.ecommerce.main.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer attachId;

    public ProductRequest(String name, String title, Long price, ProductSize size, Integer count) {
        this.name = name;
        this.title = title;
        this.price = price;
        this.size = size;
        this.count = count;
    }
}
