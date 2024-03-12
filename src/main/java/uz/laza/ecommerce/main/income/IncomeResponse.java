package uz.laza.ecommerce.main.income;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.product.ProductResponse;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponse {

    private Integer id;
    private Long totalPrice;  // this price calculated using VAT + profitPer + count * originalPrice
    private Integer count;
    private Integer profitPer;
    private Integer userId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    private ProductResponse product;

}
