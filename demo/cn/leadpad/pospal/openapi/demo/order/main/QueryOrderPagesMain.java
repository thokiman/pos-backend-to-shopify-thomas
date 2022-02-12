package cn.leadpad.pospal.openapi.demo.order.main;
/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */


import java.io.IOException;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.http.HttpUtil;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import cn.leadpad.pospal.openapi.demo.order.QueryOrdePagesResponseData;
import cn.leadpad.pospal.openapi.demo.order.QueryOrderPagesRequestData;
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
public class QueryOrderPagesMain {
	
	// 用你的凭证信息替换以下三个变量的内容
	//String urlPreFix = "https://company.pospal.cn:18443";
	private static final String urlPreFix = "http://localhost:8080";
	private static final String appId = "jiuyue01";
	private static final String appKey = "jiuyue01";
	private static final String urlString = urlPreFix+"/pospal-api2/openapi/v1/orderOpenApi/queryOrderPages";
	
	// 银豹收银系统开放接口的访问凭证
	private static final UserCertificate certificate = new UserCertificate(appId, appKey);
	
	public static void main(String[] args) throws IOException {
		//设置代码服务器，java发送的数需要手动设置代理,fiddler才能抓到包。
		HttpUtil.setCustomProxy("127.0.0.1", 8888);
		queryRemoteData("2016-02-29 00:00:00","2016-02-29 23:59:59",
			new ResponseDataProcessor(){
				@Override
				public void process(QueryOrdePagesResponseData responseData) {
					int wantQuerySize = responseData.getData().getPageSize();
					int realQuerySize = responseData.getData().getResult().size();
					System.out.println("wantQuerySize="+wantQuerySize+",realQuerySize="+realQuerySize);
					System.out.println(JsonConvertor.toJson(responseData));
					
					System.out.println();
				}
			}
		);
		
	}
	
	public static void queryRemoteData(String startTime,String endTime,ResponseDataProcessor responseDataProcessor) throws IOException{
		//是否需要进行下一页数据的查询。
		boolean queryNextPage = true;
		QueryOrderPagesRequestData requestData = new QueryOrderPagesRequestData(startTime,endTime);
		do {
			// 发送请求参数并从服务端取得返回的数据
			ResponseData sendRequestData = PospalApiService.sendRequestData(urlString, requestData, certificate);
			String responseContent = sendRequestData.getResponseContent();
			
			// 将服务端返回的数据转换成java 实例来处理
			QueryOrdePagesResponseData orderResponseData = JsonConvertor.fromJson(responseContent, QueryOrdePagesResponseData.class);
			
			responseDataProcessor.process(orderResponseData);
			
			// 是否需要进行下一页数据的查询。
			// wantQuerySize > realQuerySize说明数据已经全部取出下来了。
			int wantQuerySize = orderResponseData.getData().getPageSize();
			int realQuerySize = orderResponseData.getData().getResult().size();
			queryNextPage = !(wantQuerySize > realQuerySize);
			
			// 设置查询下一页所需要的回传参数 postBackParameter
			requestData.setPostBackParameter(orderResponseData.getData().getPostBackParameter());
			
		} while(queryNextPage);
	}
	
	interface ResponseDataProcessor{
		void process(QueryOrdePagesResponseData responseData);
	}
	
	
	

}
