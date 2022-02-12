package cn.leadpad.pospal.query;

public class ProductImageData {
    private long productUid;
    private String productName; // FRESH SHA ZUI FISH
    private String imageUrl; // ""

    public ProductImageData(long productUid, String productName, String imageUrl) {
        this.productUid = productUid;
        this.productName = productName;
        this.imageUrl = imageUrl;
    }

    public long getProductUid() {
        return productUid;
    }

    public void setProductUid(long productUid) {
        this.productUid = productUid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
