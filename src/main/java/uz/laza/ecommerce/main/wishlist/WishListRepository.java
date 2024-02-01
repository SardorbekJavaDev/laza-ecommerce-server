package uz.laza.ecommerce.main.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WishListRepository extends JpaRepository<WishList, UUID> {
}