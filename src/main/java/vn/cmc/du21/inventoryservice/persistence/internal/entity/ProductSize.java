package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;

@Entity
@Table
public class ProductSize {
    @EmbeddedId
    private ProductSizeId productSizeId;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    private Size size;

    private int totalQuantity;
    private long price;
    private long priceSale;
}
