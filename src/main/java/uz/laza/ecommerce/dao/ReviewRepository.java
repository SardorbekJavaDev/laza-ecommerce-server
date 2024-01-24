package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Review;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}