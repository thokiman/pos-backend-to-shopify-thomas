package cn.leadpad.pospal.query;

import java.math.BigDecimal;

public class ProductData {
    private long uid; // 1
    private long categoryUid; // 1
    private String name; // FRESH SHA ZUI FISH
    private String barcode;
    private BigDecimal buyPrice; // 16
    private BigDecimal sellPrice; // 18
    private BigDecimal stock; // 0
    private BigDecimal customerPrice; // 16
    private String description;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    public ProductData(long uid, long categoryUid, String name, String barcode, BigDecimal buyPrice, BigDecimal sellPrice, BigDecimal stock, BigDecimal customerPrice, String description, String attribute1, String attribute2, String attribute3, String attribute4) {
        this.uid = uid;
        this.categoryUid = categoryUid;
        this.name = name;
        this.barcode = barcode;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.customerPrice = customerPrice;
        this.description = description;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(long categoryUid) {
        this.categoryUid = categoryUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(BigDecimal customerPrice) {
        this.customerPrice = customerPrice;
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
