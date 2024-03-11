package uz.laza.ecommerce.main.mail;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String toEmail;
    private EmailType type;
    private int page;
    private int size;

}
