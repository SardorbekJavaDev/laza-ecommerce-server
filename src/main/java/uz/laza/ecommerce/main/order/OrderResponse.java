package uz.laza.ecommerce.main.order;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Integer id;
    private Integer count;
    private Integer productId;
    private Long price;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;


}
