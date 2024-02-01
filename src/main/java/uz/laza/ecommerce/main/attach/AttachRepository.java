package uz.laza.ecommerce.main.attach;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachRepository extends JpaRepository<Attach, UUID> {
}