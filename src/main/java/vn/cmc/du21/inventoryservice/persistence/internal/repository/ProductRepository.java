package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
