package uz.laza.ecommerce.main.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Integer count;
    private Integer productId;

}
