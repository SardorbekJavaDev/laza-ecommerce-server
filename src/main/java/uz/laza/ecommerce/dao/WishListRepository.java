package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.WishList;

import java.util.UUID;

public interface WishListRepository extends JpaRepository<WishList, UUID> {
}