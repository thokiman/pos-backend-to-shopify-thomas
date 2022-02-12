/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.demo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import cn.leadpad.pospal.openapi.sdk.PosaplOpenApiRequestData;
import cn.leadpad.pospal.openapi.sdk.PosaplOpenApiResponseData;
import cn.leadpad.pospal.openapi.sdk.PospalApiService;
import cn.leadpad.pospal.openapi.sdk.PostBackParameter;
import cn.leadpad.pospal.openapi.sdk.UserCertificate;

/**
 * @Type: cn.leadpad.pospal.openapi.demo.DemoMain
 * @ClassName: DemoMain
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:50:25
 * @Description: <br/>
 */
public class DemoMain {
	public static void main(String[] args) throws IOException {
		// 用你的凭证信息替换以下三个变量的内容
		String urlPreFix = "https://company.pospal.cn:18443";
		String appId = "roger1";
		String appKey = "roger1AppKey";
		 
		String urlString = urlPreFix+"/pospal-api2/openapi/v1/productOpenApi/queryProductImagePages";
		//String urlString = "http://localhost:8080/pospal-api2/openapi/v1/productOpenApi/queryProductImagePages";
		// 银豹收银系统开放接口的访问凭证
		UserCertificate certificate = new UserCertificate(appId, appKey);
		
		
		//设置代码服务器，java发送的数需要手动设置代理,fiddler才能抓到包。
		//HttpUtil.setCustomProxy("127.0.0.1", 8888);
		
		//是否需要进行下一页数据的查询。
		boolean queryNextPage = true;
		RequestImageData requestData = new RequestImageData();
		do {
			// 发送请求参数并从服务端取得返回的数据
			ResponseData sendRequestData = PospalApiService.sendRequestData(urlString, requestData, certificate);
			String responseContent = sendRequestData.getResponseContent();
			System.out.println(responseContent);
			// 将服务端返回的数据转换成java 实例来处理
			ImageResponseData imageResponseData = JsonConvertor.fromJson(responseContent, ImageResponseData.class);
			
			// 是否需要进行下一页数据的查询。
			// wantQuerySize > realQuerySize说明数据已经全部取出下来了。
			int wantQuerySize = imageResponseData.getData().getPageSize();
			int realQuerySize = imageResponseData.getData().getResult().size();
			queryNextPage = !(wantQuerySize > realQuerySize);
			
			System.out.println("wantQuerySize="+wantQuerySize+",realQuerySize="+realQuerySize);
			System.out.println(responseContent);
			
			// 设置查询下一页所需要的回传参数 postBackParameter
			requestData.setPostBackParameter(imageResponseData.getData().getPostBackParameter());
			
		} while(queryNextPage);
	}
	
	
	static class RequestImageData extends PosaplOpenApiRequestData{
		PostBackParameter postBackParameter;
		
		void setPostBackParameter(PostBackParameter postBackParameter) {
			this.postBackParameter = postBackParameter;
		}
	}
	
	static class ImageResponseData extends PosaplOpenApiResponseData{
		ImageResponseDataDetail data;

		ImageResponseDataDetail getData() {
			return data;
		}
		
	}
	
	static class ImageResponseDataDetail {
		PostBackParameter postBackParameter;
		List<ProductImage> result;
		int pageSize;
		PostBackParameter getPostBackParameter() {
			return postBackParameter;
		}
		List<ProductImage> getResult() {
			return result == null ? Collections.emptyList() : result;
		}
		int getPageSize() {
			return pageSize;
		}
	}
	
	static class ProductImage {
		private Long productUid;
	    private String productName;
	    private String productBarcode;
	    private String imageUrl;
	    
		Long getProductUid() {
			return productUid;
		}
		String getProductName() {
			return productName;
		}
		String getProductBarcode() {
			return productBarcode;
		}
		String getImageUrl() {
			return imageUrl;
		}
	    
	}

}
