package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product>findAllByCategory_CategoryId(long categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM PRODUCT WHERE categoryId = :categoryId", nativeQuery = true)
    List<Product>findAllByCategoryId(@Param(value = "categoryId") long categoryId);

}
