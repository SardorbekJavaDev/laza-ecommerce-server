package uz.laza.ecommerce.main.address;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private String name;
    private String country;
    private String city;
    private String phone;
    private String address;
    private Boolean isPrimary;

}
