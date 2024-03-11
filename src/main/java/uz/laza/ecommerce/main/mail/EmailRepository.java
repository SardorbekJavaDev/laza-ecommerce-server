package uz.laza.ecommerce.main.mail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    Page<Email> findByToEmailAndType(String toEmail, EmailType type, Pageable pageable);

}