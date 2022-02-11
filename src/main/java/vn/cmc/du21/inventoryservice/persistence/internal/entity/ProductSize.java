package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "productsize")
public class ProductSize implements Serializable {
    @EmbeddedId
    private ProductSizeId productSizeId;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    private Size size;

    private long price;
    private long priceSale;

    public ProductSize() {
    }

    public ProductSize(ProductSizeId productSizeId, Product product, Size size, long price, long priceSale) {
        this.productSizeId = productSizeId;
        this.product = product;
        this.size = size;
        this.price = price;
        this.priceSale = priceSale;
    }

    public ProductSizeId getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(ProductSizeId productSizeId) {
        this.productSizeId = productSizeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(long priceSale) {
        this.priceSale = priceSale;
    }
}
