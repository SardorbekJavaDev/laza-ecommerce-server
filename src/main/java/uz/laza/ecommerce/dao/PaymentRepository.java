package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}