package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.entity.base.Base;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "income")
public class Income extends Base {
    @Column
    private Long originalPrice;  // only for admin and we use this to calculate the profit
    @Column
    private Integer count;

    @Column
    private Integer profitPer;

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
}
