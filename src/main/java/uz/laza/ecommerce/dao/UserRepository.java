package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);
}