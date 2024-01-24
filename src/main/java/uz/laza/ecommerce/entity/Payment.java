package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.entity.base.Base;
import uz.laza.ecommerce.enums.OrderStatus;
import uz.laza.ecommerce.enums.PaymentStatus;

import java.util.UUID;

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
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}
