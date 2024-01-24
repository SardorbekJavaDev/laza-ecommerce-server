package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.entity.base.Base;
import uz.laza.ecommerce.enums.OrderStatus;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Base {

    @Column
    private Long price;
    @Column
    private Long count;
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "product_id")
    private UUID productId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private Product product;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

//    firstly user have to order, next we response payment.json, next if user done payment, next we complete user's order

}
