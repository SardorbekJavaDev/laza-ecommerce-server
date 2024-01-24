package uz.laza.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.laza.ecommerce.entity.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}