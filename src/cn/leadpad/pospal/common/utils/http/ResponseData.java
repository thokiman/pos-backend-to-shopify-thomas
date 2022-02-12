/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.common.utils.http;

/**
 * @Type: cn.leadpad.pospal.common.utils.http.ResopnseData
 * @ClassName: ResopnseData
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:07:21
 * @Description: <br/>
 */
public class ResponseData{
	private int responseCode;
	private String responseContent;
	
	private String contentEncoding;
	private int contentLength;
	private String contentType;
	private long date;
	private long expiration;
	private long lastModifed;
	
	public boolean success(){
		return 200 == responseCode;
	}
	
	
	
	
	
	void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}





	void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}





	void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}





	void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}





	void setContentType(String contentType) {
		this.contentType = contentType;
	}





	void setDate(long date) {
		this.date = date;
	}





	void setExpiration(long expiration) {
		this.expiration = expiration;
	}





	void setLastModifed(long lastModifed) {
		this.lastModifed = lastModifed;
	}





	public String getContentEncoding() {
		return contentEncoding;
	}
	public int getContentLength() {
		return contentLength;
	}
	public String getContentType() {
		return contentType;
	}
	public long getDate() {
		return date;
	}
	public long getExpiration() {
		return expiration;
	}
	public long getLastModifed() {
		return lastModifed;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public String getResponseContent() {
		return responseContent;
	}
}
