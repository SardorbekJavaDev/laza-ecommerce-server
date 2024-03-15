package uz.laza.ecommerce.main.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    private Integer id;
    private String owner;
    private String number;
    private String cvv;
    private LocalDate exp;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
