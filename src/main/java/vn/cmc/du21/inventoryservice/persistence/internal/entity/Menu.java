package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuId;
    private String menuName;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "menuSize", joinColumns = @JoinColumn(name = "menuId"), inverseJoinColumns = @JoinColumn(name = "sizeId"))
    private Set<Size> sizes = new HashSet<>();

    private long totalMoney;
    private long userId;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "menuProduct", joinColumns = @JoinColumn(name = "menuId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products = new HashSet<>();

    public Menu() {
    }

    public Menu(long menuId, String menuName, Set<Size> sizes, long totalMoney, long userId, Set<Product> products) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.sizes = sizes;
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

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
