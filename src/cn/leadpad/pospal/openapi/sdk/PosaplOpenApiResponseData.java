/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.sdk;

/**
 * @Type: cn.leadpad.pospal.common.ApiResponseData
 * @ClassName: ApiResponseData
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午5:34:34
 * @Description: <br/>
 */
public class PosaplOpenApiResponseData {
	private String status;
	private Integer errorCode;
	private String[] messages;
	
	public boolean isSuccess(){
		if(status == null ) status = "";
		return "success".equals(status.toLowerCase());
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
	
	public String[] getMessages() {
		return messages;
	}
	
	
}
