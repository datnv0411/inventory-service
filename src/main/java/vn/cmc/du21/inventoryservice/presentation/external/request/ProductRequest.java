package vn.cmc.du21.inventoryservice.presentation.external.request;

public class ProductRequest {
    private long productId;

    public ProductRequest() {
    }

    public ProductRequest(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
