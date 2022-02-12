/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.demo.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Type: cn.leadpad.pospal.openapi.demo.order.Order
 * @ClassName: Order
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2016年3月1日 下午4:31:23
 * @Description: <br/>
 */
public class Order {
	private BigDecimal totalAmount;
    private String customerNumber;
    private String orderNo;
    private Short state;
	private String payMethod;
	private String orderRemark;
	private Date orderDateTime;
	
	private String contactAddress; //送货地址
	private String contactName;//下单者姓名
	private String contactTel;//联系电话
	
	private List<OrderItem> items;

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public Short getState() {
		return state;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public List<OrderItem> getItems() {
		return items;
	}
}
