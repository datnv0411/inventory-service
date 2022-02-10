package vn.cmc.du21.inventoryservice.presentation.external.response;

import vn.cmc.du21.inventoryservice.common.PathImageUtil;

import java.util.List;

public class ProductResponse {
    private long productId;
    private String productName;
    private String productSearch;
    private String quantitative;
    private String description;
    private String createTime;
    private String productImage;
    private long categoryId;
    private List<SizeResponse> sizeResponseList;

    public ProductResponse() {
    }

    public ProductResponse(long productId, String productName, String productSearch, String quantitative
            , String description, String createTime, String productImage, long categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productSearch = productSearch;
        this.quantitative = quantitative;
        this.description = description;
        this.createTime = createTime;
        this.productImage = productImage;
        this.categoryId = categoryId;
    }

    public ProductResponse(long productId, String productName, String productSearch, String quantitative
            , String description, String createTime, String productImage, long categoryId
            , List<SizeResponse> sizeResponseList) {
        this.productId = productId;
        this.productName = productName;
        this.productSearch = productSearch;
        this.quantitative = quantitative;
        this.description = description;
        this.createTime = createTime;
        this.productImage = productImage;
        this.categoryId = categoryId;
        this.sizeResponseList = sizeResponseList;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProductImage() {
        return PathImageUtil.getPathImage(this.productImage);
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List<SizeResponse> getSizeResponseList() {
        return sizeResponseList;
    }

    public void setSizeResponseList(List<SizeResponse> sizeResponseList) {
        this.sizeResponseList = sizeResponseList;
    }
}
