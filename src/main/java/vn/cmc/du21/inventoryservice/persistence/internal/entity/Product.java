package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import vn.cmc.du21.inventoryservice.common.VNCharacterUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String productName;
    private String productSearch;
    private String quantitative;
    private String description;
    private Timestamp createTime;
    private String productImage;
    @Transient
    private long priceDefault;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSize> productSizes;

    @ManyToMany(mappedBy = "products")
    private Set<Menu> menus;

    public Product() {
    }

    public Product(long productId, String productName, String productSearch, String quantitative
            , String description, Timestamp createTime, String productImage, Category category
            , Set<ProductSize> productSizes, Set<Menu> menus) {
        this.productId = productId;
        this.productName = productName;
        this.productSearch = productSearch;
        this.quantitative = quantitative;
        this.description = description;
        this.createTime = createTime;
        this.productImage = productImage;
        this.category = category;
        this.productSizes = productSizes;
        this.menus = menus;
    }

    public long getPriceDefault() {
        return priceDefault;
    }

    public void setPriceDefault(long priceDefault) {
        this.priceDefault = priceDefault;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSearch() {
        return productSearch;
    }

    public void setProductSearch(String productSearch) {
        this.productSearch = productSearch;
    }

    public String getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(String quantitative) {
        this.quantitative = quantitative;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(Set<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
