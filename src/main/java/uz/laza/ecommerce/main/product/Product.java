package uz.laza.ecommerce.main.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.attach.Attach;

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
    private ProductSize size;
    @Column
    private Integer count;

    @Column(name = "attach_id")
    private Integer attachId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", updatable = false, insertable = false)
    private List<Attach> attach;

}
