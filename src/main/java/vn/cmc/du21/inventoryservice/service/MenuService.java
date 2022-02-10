package vn.cmc.du21.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Menu;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSize;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Size;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.MenuRepository;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    ProductRepository productRepository;
    private Menu mainMenu = new Menu();

    @Transactional
    public Menu addProduct(long productId) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                ()-> {
                    throw new RuntimeException("Product is not available");
                }
        );
        Set<Product> listProduct = mainMenu.getProducts();

        if (!listProduct.contains(foundProduct)) {
            listProduct.add(foundProduct);
            mainMenu.setProducts(listProduct);

            LinkedHashSet<Size> listSizeMenu = mainMenu.getSizes();
            LinkedHashSet<Size> listSizeProduct = new LinkedHashSet<>();

            for (ProductSize item : foundProduct.getProductSizes()) {
                listSizeProduct.add(item.getSize());
            }

            if (listSizeMenu.isEmpty()) {
                listSizeMenu.addAll(listSizeProduct);
            } else {
                for (Size item : listSizeMenu) {
                    if (!listSizeProduct.contains(item)) {
                        listSizeMenu.remove(item);
                    }
                }
            }
            mainMenu.setSizes(listSizeMenu);
        }
        return mainMenu;
    }

    @Transactional
    public Menu removeProduct(long productId) {
        Product foundProduct = productRepository.findById(productId).orElse(null);
        Set<Product> listProduct = mainMenu.getProducts();

        if(listProduct.contains(foundProduct))
        {
            listProduct.remove(foundProduct);
            mainMenu.setProducts(listProduct);

            LinkedHashSet<Size> listSizeMenu = mainMenu.getSizes();
            mainMenu.setSizes(listSizeMenu);
        }
        return mainMenu;
    }

    @Transactional
    public Menu createMenu(Menu menu) {
        mainMenu.setUserId(menu.getUserId());
        mainMenu.setMenuName(menu.getMenuName());
        Menu temp = mainMenu;
        mainMenu = new Menu();
        return menuRepository.save(temp);
    }

    @Transactional
    public void deleteMenu(long userId, long menuId) {
        Menu foundMenu = menuRepository.findByUserIdAndMenuId(userId, menuId).orElseThrow(null);
        menuRepository.delete(foundMenu);
    }

    @Transactional
    public Page<Menu> getPageMenu(long userId, String page, String size, String sortFiled) {

        int pageInt = Integer.parseInt(page) - 1;
        int sizeInt = Integer.parseInt(size);

        List<Menu> listMenu = menuRepository.findByUserId(userId);

        Pageable pageable = PageRequest.of(pageInt, sizeInt, Sort.by(sortFiled));
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listMenu.size());

        return new PageImpl<>(listMenu.subList(start, end), pageable, listMenu.size());
    }
    @Transactional
    public Menu getMenuById(long userId,long menuId) throws Throwable{
        return menuRepository.findByUserIdAndMenuId(userId, menuId).orElseThrow(()->{
            throw new RuntimeException("the menu was not found!!!");
        });
    }
}
