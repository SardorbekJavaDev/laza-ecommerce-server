package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.laza.ecommerce.entity.base.Base;
import uz.laza.ecommerce.enums.GenderType;
import uz.laza.ecommerce.enums.Role;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Base implements UserDetails {

    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private GenderType gender;

    @Column(name = "attach_id")
    private UUID attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", updatable = false, insertable = false)
    private Attach attach;

    @ManyToMany(mappedBy = "users")
    private Set<Address> addressEntities = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



















