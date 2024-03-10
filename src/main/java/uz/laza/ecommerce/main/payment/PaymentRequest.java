package uz.laza.ecommerce.main.payment;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.Base;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest extends Base {

    private Long totalPrice;

}
