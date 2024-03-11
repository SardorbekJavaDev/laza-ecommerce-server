package uz.laza.ecommerce.main.review;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private double rating;
    private String description;
    private Integer productId;

}
