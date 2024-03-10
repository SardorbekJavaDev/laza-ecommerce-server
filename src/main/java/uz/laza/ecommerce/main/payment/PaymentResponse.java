package uz.laza.ecommerce.main.payment;

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
public class PaymentResponse extends Base {

    private Long totalPrice;
    private PaymentStatus status;
    private LocalDateTime lastModified;
    private LocalDateTime createDate;

}
