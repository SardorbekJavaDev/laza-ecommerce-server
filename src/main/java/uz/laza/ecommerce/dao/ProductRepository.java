package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}