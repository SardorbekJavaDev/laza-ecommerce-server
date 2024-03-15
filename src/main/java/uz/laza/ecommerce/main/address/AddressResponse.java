package uz.laza.ecommerce.main.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.laza.ecommerce.main.product.ProductResponse;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Integer id;
    private String name;
    private String country;
    private String city;
    private String phone;
    private String address;
    private Boolean isPrimary;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
