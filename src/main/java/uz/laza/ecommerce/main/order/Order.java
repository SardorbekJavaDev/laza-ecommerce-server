package uz.laza.ecommerce.main.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.user.User;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Base {

    @Column
    private Long price;
    @Column
    private Integer count;
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "product_id")
    private Integer productId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private Product product;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

//    firstly user have to order, next we are response payment.json, next if user done payment, next we complete user's order

}
