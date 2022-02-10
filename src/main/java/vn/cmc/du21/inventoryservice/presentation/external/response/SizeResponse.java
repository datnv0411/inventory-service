package vn.cmc.du21.inventoryservice.presentation.external.response;

public class SizeResponse {
    private long sizeId;
    private String sizeName;
    private long price;
    private long priceSale;

    public SizeResponse() {
    }

    public SizeResponse(long sizeId, String sizeName, long price, long priceSale) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.price = price;
        this.priceSale = priceSale;
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
