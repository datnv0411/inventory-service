package vn.cmc.du21.inventoryservice.persistence.internal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "SIZE")
public class Size implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sizeId;
    private String sizeName;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private Set<Menu> menus;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private Set<ProductSize> productSizes;

}
