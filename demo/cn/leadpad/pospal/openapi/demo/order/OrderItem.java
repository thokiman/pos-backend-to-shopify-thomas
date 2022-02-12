/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.demo.order;

import java.math.BigDecimal;

/**
 * @Type: cn.leadpad.pospal.openapi.demo.order.OrderItem
 * @ClassName: OrderItem
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2016年3月1日 下午4:31:00
 * @Description: <br/>
 */
public class OrderItem {
	private String productName;
	private Long productUid;
	private String productBarcode;
	private BigDecimal eshopSellPrice;
	private int productQuantity;
	private BigDecimal productBuyPrice;
	private BigDecimal productSellPrice;
	private Boolean isCustomerDiscount;
	private BigDecimal customerDiscount;
	private BigDecimal customerPrice;
	private Long promotionRuleUid;
	//private Boolean isCustomerPoint;
	private String comment;
	public String getProductName() {
		return productName;
	}
	public Long getProductUid() {
		return productUid;
	}
	public String getProductBarcode() {
		return productBarcode;
	}
	public BigDecimal getEshopSellPrice() {
		return eshopSellPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public BigDecimal getProductBuyPrice() {
		return productBuyPrice;
	}
	public BigDecimal getProductSellPrice() {
		return productSellPrice;
	}
	public Boolean getIsCustomerDiscount() {
		return isCustomerDiscount;
	}
	public BigDecimal getCustomerDiscount() {
		return customerDiscount;
	}
	public BigDecimal getCustomerPrice() {
		return customerPrice;
	}
	public Long getPromotionRuleUid() {
		return promotionRuleUid;
	}
	public String getComment() {
		return comment;
	}
}
