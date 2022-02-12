/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.demo.order;

import cn.leadpad.pospal.openapi.sdk.PosaplOpenApiRequestData;
import cn.leadpad.pospal.openapi.sdk.PostBackParameter;

/**
 * @Type: cn.leadpad.pospal.openapi.demo.order.QueryOrderRequestData
 * @ClassName: QueryOrderRequestData
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2016年3月1日 下午4:32:35
 * @Description: <br/>
 */
public class QueryOrderPagesRequestData extends PosaplOpenApiRequestData{
	PostBackParameter postBackParameter;
	private String startTime;
	private String endTime;
	
	public QueryOrderPagesRequestData(String startTime, String endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public void setPostBackParameter(PostBackParameter postBackParameter) {
		this.postBackParameter = postBackParameter;
	}
}