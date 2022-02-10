package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuId;
    private String menuName;

    @Transient
    private LinkedHashSet<Size> sizes = new LinkedHashSet<>();

    private long userId;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "menuProduct", joinColumns = @JoinColumn(name = "menuId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products = new LinkedHashSet<>();

    public Menu() {
    }

    public Menu(long menuId, String menuName, LinkedHashSet<Size> sizes, long userId, LinkedHashSet<Product> products) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.sizes = sizes;
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

    public LinkedHashSet<Size> getSizes() {

        LinkedHashSet<Size> listSizeMenu = new LinkedHashSet<>();
        for (ProductSize item : products.iterator().next().getProductSizes()) {
            listSizeMenu.add(item.getSize());
        }

        for(Product itemProduct : products)
        {
            LinkedHashSet<Size> listSizeProduct = new LinkedHashSet<>();
            for (ProductSize item : itemProduct.getProductSizes()) {
                listSizeProduct.add(item.getSize());
            }

            for (Size item : listSizeMenu) {
                if (!listSizeProduct.contains(item)) {
                    listSizeMenu.remove(item);
                }
            }
        }
        return listSizeMenu;
    }

    public void setSizes(LinkedHashSet<Size> sizes) {
        this.sizes = sizes;
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
