package vn.cmc.du21.inventoryservice.presentation.external.response;

import java.sql.Timestamp;

public class ProductResponse {
    private long productId;
    private String productName;
    private String productSearch;
    private String quantitative;
    private String description;
    private Timestamp createTime;
    private String productImage;

    public ProductResponse() {
    }

    public ProductResponse(long productId, String productName, String productSearch, String quantitative
            , String description, Timestamp createTime, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productSearch = productSearch;
        this.quantitative = quantitative;
        this.description = description;
        this.createTime = createTime;
        this.productImage = productImage;
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
}
