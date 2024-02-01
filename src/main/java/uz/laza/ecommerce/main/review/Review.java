package uz.laza.ecommerce.main.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.user.User;
import uz.laza.ecommerce.main.product.Product;


@Getter
@Setter
@Entity
@Table(name = "review")
public class Review extends Base {

    @Column(name = "rating")
    private double rating;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

}
