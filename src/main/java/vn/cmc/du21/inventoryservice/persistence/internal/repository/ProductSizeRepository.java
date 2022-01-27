package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSize;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSizeId;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeId> {
}
