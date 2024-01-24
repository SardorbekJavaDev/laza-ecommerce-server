package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Card;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}