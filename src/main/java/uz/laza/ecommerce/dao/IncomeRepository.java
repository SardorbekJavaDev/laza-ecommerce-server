package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Income;

import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, UUID> {
}