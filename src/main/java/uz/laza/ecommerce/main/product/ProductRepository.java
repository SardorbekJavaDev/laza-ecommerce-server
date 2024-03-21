package uz.laza.ecommerce.main.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.main.brand.Brand;
import uz.laza.ecommerce.main.user.GenderType;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByGenderAndBrand(GenderType gender, Brand brand, Pageable pageable);

    Page<Product> findAllByGender(GenderType gender, Pageable pageable);

    Page<Product> findAllByBrand(Brand brand, Pageable pageable);
}