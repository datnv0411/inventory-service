package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuId;
    private String menuName;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    private long totalMoney;
    private long userId;

    @ManyToMany
    @JoinTable(name = "menuProduct", joinColumns = @JoinColumn(name = "menuId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products;
}
