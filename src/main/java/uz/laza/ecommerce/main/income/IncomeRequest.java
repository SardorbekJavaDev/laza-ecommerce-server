package uz.laza.ecommerce.main.income;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequest {

    private Integer productId;
    private Integer count;
    private Integer profitPer;

}
