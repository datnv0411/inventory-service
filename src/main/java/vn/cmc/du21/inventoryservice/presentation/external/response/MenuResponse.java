package vn.cmc.du21.inventoryservice.presentation.external.response;

import java.util.List;
import java.util.Set;

public class MenuResponse {
    private long menuId;
    private String menuName;
    private long userId;
    private Set<ProductResponse> products;

    private List<SizeResponse> sizeResponseList;

    public MenuResponse() {
    }

    public MenuResponse(long menuId, String menuName, long userId, Set<ProductResponse> products, List<SizeResponse> sizes) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.userId = userId;
        this.products = products;
        this.sizeResponseList = sizes;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductResponse> products) {
        this.products = products;
    }

    public List<SizeResponse> getSizeResponseList() {
        return sizeResponseList;
    }

    public void setSizeResponseList(List<SizeResponse> sizeResponseList) {
        this.sizeResponseList = sizeResponseList;
    }
}
