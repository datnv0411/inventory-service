package vn.cmc.du21.inventoryservice.presentation.external.request;

import java.util.List;
import java.util.Set;

public class MenuRequest {
    private String menuName;
    private long userId;
    private Set<ProductRequest> products;

    public MenuRequest() {
    }

    public MenuRequest(String menuName, long userId) {
        this.menuName = menuName;
        this.userId = userId;
    }

    public MenuRequest(String menuName, long userId, Set<ProductRequest> products) {
        this.menuName = menuName;
        this.userId = userId;
        this.products = products;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductRequest> products) {
        this.products = products;
    }
}
