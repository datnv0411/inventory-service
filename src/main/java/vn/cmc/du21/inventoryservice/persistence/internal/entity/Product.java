package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String productName;
    @Transient
    private String productSearch;

    private String quantitative;
    private String description;
    private Timestamp createTime;
    private String productImage;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSize> productSizes;

    @ManyToMany(mappedBy = "products")
    private Set<Menu> menus;
}
