package uz.laza.ecommerce.main.income;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.base.Base;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.user.User;

@Getter
@Setter
@Entity
@Table(name = "income")
public class Income extends Base {

    @Column
    private Long totalPrice;  // this price calculated using VAT + profitPer + count * originalPrice
    @Column
    private Integer count;

    @Column
    private Integer profitPer;

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

}
