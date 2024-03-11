package uz.laza.ecommerce.main.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.product.ProductSize;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishListResponse {

    private Integer id;
    private Integer userId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
