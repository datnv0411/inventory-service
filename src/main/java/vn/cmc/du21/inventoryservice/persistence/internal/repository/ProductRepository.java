package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * " +
            " FROM PRODUCTSIZE ps " +
            " inner join PRODUCT p on ps.product_productId = p.productId " +
            " where productSearch LIKE %:productSearch% "
            , nativeQuery = true)
    List<Product> findByProductName(
             @Param(value = "productSearch") String productSearch
    );

    Page<Product> findAllByCategory_CategoryId(long categoryId, Pageable pageable);
}
