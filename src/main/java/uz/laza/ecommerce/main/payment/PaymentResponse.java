package uz.laza.ecommerce.main.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private Integer id;
    private Long totalPrice;
    private PaymentStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
