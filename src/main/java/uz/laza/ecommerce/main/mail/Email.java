package uz.laza.ecommerce.main.mail;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import uz.laza.ecommerce.base.Base;

@Entity
@Getter
@Setter
@Table(name = "email_history")
public class Email extends Base {

    @Column(nullable = false)
    private String toEmail;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmailType type;
}
