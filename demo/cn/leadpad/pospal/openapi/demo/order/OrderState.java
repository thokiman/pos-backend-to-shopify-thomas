/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.demo.order;


/** 网络定单状态 */
public class OrderState {
	public static final short NULL = 0;// 定单创建时的状态
	public static final short SYNCED = 1;// 定单已同步到客户端的状态
	public static final short SHIPPED = 2;// 定单已发货状态
	public static final short CANCELED = 3;// 定单取消状态
	public static final short COMPLETED = 4;// 定单结束状态
}
