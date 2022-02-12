package cn.leadpad.pospal.query;

import java.math.BigDecimal;

public class POSProduct {
    private long uidProduct; // 1
    private long categoryUidProduct; // 1
    private String categoryNameProduct; //
    private String nameProduct; // // FRESH SHA ZUI FISH
    private String barcodeProduct;
    private BigDecimal buyPriceProduct; // 16
    private BigDecimal sellPriceProduct; // 0
    private BigDecimal customerPriceProduct; // 16
    private BigDecimal stockProduct;
    private String imageUrlProduct; // ""
    private String description;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    public POSProduct(long uidProduct, long categoryUidProduct, String categoryNameProduct, String nameProduct,
                      String barcodeProduct ,BigDecimal buyPriceProduct, BigDecimal sellPriceProduct, BigDecimal customerPriceProduct,BigDecimal stockProduct ,String imageUrlProduct, String description, String attribute1, String attribute2, String attribute3, String attribute4) {
        this.uidProduct = uidProduct;
        this.categoryUidProduct = categoryUidProduct;
        this.categoryNameProduct = categoryNameProduct;
        this.nameProduct = nameProduct;
        this.barcodeProduct = barcodeProduct;
        this.buyPriceProduct = buyPriceProduct;
        this.sellPriceProduct = sellPriceProduct;
        this.customerPriceProduct = customerPriceProduct;
        this.stockProduct = stockProduct;
        this.imageUrlProduct = imageUrlProduct;
        this.description = description;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
    }

    public String getBarcodeProduct() {
        return barcodeProduct;
    }

    public void setBarcodeProduct(String barcodeProduct) {
        this.barcodeProduct = barcodeProduct;
    }

    public BigDecimal getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(BigDecimal stockProduct) {
        this.stockProduct = stockProduct;
    }

    public long getUidProduct() {
        return uidProduct;
    }

    public void setUidProduct(long uidProduct) {
        this.uidProduct = uidProduct;
    }

    public long getCategoryUidProduct() {
        return categoryUidProduct;
    }

    public void setCategoryUidProduct(long categoryUidProduct) {
        this.categoryUidProduct = categoryUidProduct;
    }

    public String getCategoryNameProduct() {
        return categoryNameProduct;
    }

    public void setCategoryNameProduct(String categoryNameProduct) {
        this.categoryNameProduct = categoryNameProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public BigDecimal getBuyPriceProduct() {
        return buyPriceProduct;
    }

    public void setBuyPriceProduct(BigDecimal buyPriceProduct) {
        this.buyPriceProduct = buyPriceProduct;
    }

    public BigDecimal getSellPriceProduct() {
        return sellPriceProduct;
    }

    public void setSellPriceProduct(BigDecimal sellPriceProduct) {
        this.sellPriceProduct = sellPriceProduct;
    }

    public BigDecimal getCustomerPriceProduct() {
        return customerPriceProduct;
    }

    public void setCustomerPriceProduct(BigDecimal customerPriceProduct) {
        this.customerPriceProduct = customerPriceProduct;
    }

    public String getImageUrlProduct() {
        return imageUrlProduct;
    }

    public void setImageUrlProduct(String imageUrlProduct) {
        this.imageUrlProduct = imageUrlProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }
}
