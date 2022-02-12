/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.sdk;

/**
 * @Type: cn.leadpad.pospal.common.Certificate
 * @ClassName: Certificate
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:27:28
 * @Description: <br/>
 */
public class UserCertificate {
	private String appId;
	private String appKey;
	public UserCertificate(String appId, String appKey) {
		this.appId = appId;
		this.appKey = appKey;
	}
	
	String getAppId() {
		return appId;
	}
	String getAppKey() {
		return appKey;
	}
}
