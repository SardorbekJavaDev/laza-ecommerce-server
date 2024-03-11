package uz.laza.ecommerce.main.brand;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.base.Base;
import uz.laza.ecommerce.main.attach.Attach;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand extends Base {

    @Column
    private String name;

    @Column(name = "attach_id")
    private Integer attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",updatable = false,insertable = false)
    private Attach attach;

}
