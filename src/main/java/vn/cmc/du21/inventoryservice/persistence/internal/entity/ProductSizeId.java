package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductSizeId implements Serializable {
    @Column(name = "productId")
    private long productId;

    @Column(name = "sizeId")
    private long sizeId;

    public ProductSizeId() {
    }

    public ProductSizeId(long productId, long sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }
}
