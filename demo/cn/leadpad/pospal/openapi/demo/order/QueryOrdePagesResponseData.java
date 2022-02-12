/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.demo.order;

import java.util.Collections;
import java.util.List;

import cn.leadpad.pospal.openapi.sdk.PosaplOpenApiResponseData;
import cn.leadpad.pospal.openapi.sdk.PostBackParameter;

/**
 * @Type: cn.leadpad.pospal.openapi.demo.order.QueryOrdeResponseData
 * @ClassName: QueryOrdeResponseData
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2016年3月1日 下午4:34:57
 * @Description: <br/>
 */
public class QueryOrdePagesResponseData extends PosaplOpenApiResponseData{
	OrderesponseDataDetail data;

	public OrderesponseDataDetail getData() {
		return data;
	}
	
	public static class OrderesponseDataDetail {
		
		PostBackParameter postBackParameter;
		List<Order> result;
		int pageSize;
		public PostBackParameter getPostBackParameter() {
			return postBackParameter;
		}
		public List<Order> getResult() {
			return result == null ? Collections.emptyList() : result;
		}
		public int getPageSize() {
			return pageSize;
		}
	}
}

