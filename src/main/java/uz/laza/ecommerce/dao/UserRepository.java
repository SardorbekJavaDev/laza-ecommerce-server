package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}