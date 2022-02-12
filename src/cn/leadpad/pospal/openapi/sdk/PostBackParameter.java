/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.sdk;


/**
 * @Type: cn.leadpad.pospal.common.PostBackParameter
 * @ClassName: PostBackParameter
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午5:30:32
 * @Description: <br/>
 */
public class PostBackParameter {
	private String parameterType;
	private String parameterValue;
	public PostBackParameter(String parameterType, String parameterValue) {
		this.parameterType = parameterType;
		this.parameterValue = parameterValue;
	}
}
