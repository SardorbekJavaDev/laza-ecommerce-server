package uz.laza.ecommerce.main.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    private String owner;
    private String number;
    private String cvv;
    private LocalDate exp;

}
