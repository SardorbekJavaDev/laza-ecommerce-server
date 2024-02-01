package uz.laza.ecommerce.main.wishlist;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.user.User;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "product_wish_list")
public class WishList extends Base {

    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

}
