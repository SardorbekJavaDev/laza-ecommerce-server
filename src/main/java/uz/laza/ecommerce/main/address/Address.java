package uz.laza.ecommerce.main.address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.main.Base;
import uz.laza.ecommerce.main.user.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address extends Base {

    @Column
    private String name;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private Boolean isPrimary;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "address_user",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

}
