package uz.laza.ecommerce.main.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.Base;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Integer count;
    private Integer productId;
    private Long price;
    private LocalDateTime lastModified;
    private LocalDateTime createDate;

}
