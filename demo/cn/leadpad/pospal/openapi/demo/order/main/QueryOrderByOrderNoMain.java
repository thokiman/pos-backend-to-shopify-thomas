package cn.leadpad.pospal.openapi.demo.order.main;
/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */


import java.io.IOException;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.http.HttpUtil;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import cn.leadpad.pospal.openapi.demo.order.Order;
import cn.leadpad.pospal.openapi.sdk.PosaplOpenApiRequestData;
import cn.leadpad.pospal.openapi.sdk.PosaplOpenApiResponseData;
import cn.leadpad.pospal.openapi.sdk.PospalApiService;
import cn.leadpad.pospal.openapi.sdk.UserCertificate;

/**
 * @Type: cn.leadpad.pospal.openapi.demo.DemoMain
 * @ClassName: DemoMain
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:50:25
 * @Description: <br/>
 */
public class QueryOrderByOrderNoMain {
	
	// 用你的凭证信息替换以下三个变量的内容
	//String urlPreFix = "https://company.pospal.cn:18443";
	private static final String urlPreFix = "http://localhost:8080";
	private static final String appId = "jiuyue01";
	private static final String appKey = "jiuyue01";
	private static final String urlString = urlPreFix+"/pospal-api2/openapi/v1/orderOpenApi/queryOrderByNo";
	
	// 银豹收银系统开放接口的访问凭证
	private static final UserCertificate certificate = new UserCertificate(appId, appKey);
	
	public static void main(String[] args) throws IOException {
		//设置代码服务器，java发送的数需要手动设置代理,fiddler才能抓到包。
		HttpUtil.setCustomProxy("127.0.0.1", 8888);
		
		// 发送请求参数并从服务端取得返回的数据
		String orderNo = "20160229154859064104";
		QueryOrderByNoRequestData requestData = new QueryOrderByNoRequestData(orderNo);
		ResponseData sendRequestData = PospalApiService.sendRequestData(urlString, requestData, certificate);
		
		String responseContent = sendRequestData.getResponseContent();
		System.out.println(responseContent);
		
		QueryOrderByNoResponseData data = (QueryOrderByNoResponseData)JsonConvertor.fromJson(responseContent, QueryOrderByNoResponseData.class);
		System.out.println(JsonConvertor.toJson(data.data));
	}
	
	public static class QueryOrderByNoRequestData extends PosaplOpenApiRequestData{
		private String orderNo;

		public QueryOrderByNoRequestData(String orderNo) {
			this.orderNo = orderNo;
		}
		
	}
	
	public static class QueryOrderByNoResponseData extends PosaplOpenApiResponseData{
		private Order data;
		
	}
	

}
