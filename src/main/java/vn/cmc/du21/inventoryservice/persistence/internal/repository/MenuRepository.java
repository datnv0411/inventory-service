package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Menu;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByUserId(long userId);
    @Query(value = "SELECT * FROM menu WHERE userId = :userId AND menuId = :menuId"
            , nativeQuery = true)
    Optional<Menu> findByUserIdAndMenuId(
            @Param(value = "userId") long userId, @Param(value = "menuId") long menuId
    );
}
