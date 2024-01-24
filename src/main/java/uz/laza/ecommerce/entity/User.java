package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.entity.base.Base;

import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends Base {

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;

    @Column(name = "attach_id")
    private UUID attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", updatable = false, insertable = false)
    private Attach attach;

    @ManyToMany(mappedBy = "users")
    private Set<Address> addressEntities = new HashSet<>();
}



















