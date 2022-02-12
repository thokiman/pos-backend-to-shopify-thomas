/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.sdk;

/**
 * @Type: cn.leadpad.pospal.common.utils.dto.RequestData
 * @ClassName: RequestData
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:19:59
 * @Description: <br/>
 */
public class PosaplOpenApiRequestData {
	private String appId;
	private long categoryUid;

	void setAppId(String appId) {
		this.appId = appId;
	}
	void setCategoryUid(long categoryUid) {
		this.categoryUid = categoryUid;
	}
}
