package uz.laza.ecommerce.main.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.base.Base;
import uz.laza.ecommerce.main.attach.Attach;
import uz.laza.ecommerce.main.brand.Brand;
import uz.laza.ecommerce.main.user.GenderType;
import uz.laza.ecommerce.main.user.User;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends Base {

    @Column
    private String name;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Long price; // This price is based on the original price
    @Column
    @Enumerated(EnumType.STRING)
    private ProductSize size; // capacity/bulk
    @Column
    private Integer count;
    @Column
    private GenderType gender;

    @Column(name = "attach_id")
    private Integer attachId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", updatable = false, insertable = false)
    private List<Attach> attach;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private Brand brand;

}
