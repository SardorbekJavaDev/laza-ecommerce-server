package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Brand;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
}