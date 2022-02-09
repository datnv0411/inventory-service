package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSize;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSizeId;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeId> {

}
