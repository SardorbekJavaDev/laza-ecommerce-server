package uz.laza.ecommerce.main.mail;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {

    private Integer id;
    private String toEmail;
    private EmailType type;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

}
