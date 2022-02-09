package vn.cmc.du21.inventoryservice.presentation.external.response;

public class SizeResponse {
    private long sizeId;
    private String nameSize;
    private long price;
    private long priceSale;

    public SizeResponse() {
    }

    public SizeResponse(long sizeId, String nameSize, long price, long priceSale) {
        this.sizeId = sizeId;
        this.nameSize = nameSize;
        this.price = price;
        this.priceSale = priceSale;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
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
