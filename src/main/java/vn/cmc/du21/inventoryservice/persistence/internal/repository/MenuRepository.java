package vn.cmc.du21.inventoryservice.persistence.internal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Menu;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByUserId(long userId);
}
