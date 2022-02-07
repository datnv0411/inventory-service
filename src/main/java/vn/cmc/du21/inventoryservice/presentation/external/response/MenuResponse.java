package vn.cmc.du21.inventoryservice.presentation.external.response;

import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Size;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

public class MenuResponse {
    private long menuId;
    private String menuName;
    private long sizeId;
    private long totalMoney;
    private long userId;
    private Set<ProductResponse> products;

    public MenuResponse() {
    }

    public MenuResponse(long menuId, String menuName, long sizeId, long totalMoney, long userId, Set<ProductResponse> products) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.sizeId = sizeId;
        this.totalMoney = totalMoney;
        this.userId = userId;
        this.products = products;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductResponse> products) {
        this.products = products;
    }
}
