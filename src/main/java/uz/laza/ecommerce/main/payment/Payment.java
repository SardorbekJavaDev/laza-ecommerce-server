package uz.laza.ecommerce.main.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.user.User;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment extends Base {

    @Column
    private Long totalPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}
