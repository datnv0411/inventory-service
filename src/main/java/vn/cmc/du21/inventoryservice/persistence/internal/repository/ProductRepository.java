package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM PRODUCT" +
            " WHERE productSearch LIKE %:productSearch% "
            , nativeQuery = true)
    List<Product> findByProductName(
             @Param(value = "productSearch") String productSearch
    );

    @Query(value = "SELECT productId, productName, productSearch, quantitative, description" +
            ", createTime, productImage, categoryId, min(priceSale) as priceDefault " +
            " FROM PRODUCTSIZE ps " +
            " inner join PRODUCT p on ps.product_productId = p.productId " +
            " where productSearch LIKE %:productSearch% " +
            " group by ps.product_productId " +
            " order by min(priceSale) "
            , nativeQuery = true)
    List<Product> findByProductNameAndSortByPriceSale(
            @Param(value = "productSearch") String productSearch
    );
}
