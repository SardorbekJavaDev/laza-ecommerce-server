package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.entity.base.Base;
import uz.laza.ecommerce.enums.ProductSize;

import java.util.List;
import java.util.UUID;

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
    private UUID attachId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", updatable = false, insertable = false)
    private List<Attach> attach;

}
