package uz.laza.ecommerce.main.review;

import jakarta.persistence.*;
import lombok.*;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.user.User;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse extends Base {

    private double rating;
    private String description;
    private Integer productId;
    private LocalDateTime lastModified;
    private LocalDateTime createDate;


}
