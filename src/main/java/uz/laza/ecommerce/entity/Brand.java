package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.entity.base.Base;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand extends Base {

    @Column
    private String name;

    @Column(name = "attach_id")
    private UUID attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",updatable = false,insertable = false)
    private Attach attach;

}
