package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "SIZE")
public class Size implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sizeId;
    private String sizeName;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductSize> productSizes;

    public Size() {
    }

    public Size(long sizeId, String sizeName, LinkedHashSet<ProductSize> productSizes) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.productSizes = productSizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return sizeId == size.sizeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeId);
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Set<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(LinkedHashSet<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }
}
