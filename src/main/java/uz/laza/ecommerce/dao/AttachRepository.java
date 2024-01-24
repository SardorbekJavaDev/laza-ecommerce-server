package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Attach;

import java.util.UUID;

public interface AttachRepository extends JpaRepository<Attach, UUID> {
}