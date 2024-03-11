package uz.laza.ecommerce.main.review;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

    private Integer id;
    private double rating;
    private String description;
    private Integer productId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
