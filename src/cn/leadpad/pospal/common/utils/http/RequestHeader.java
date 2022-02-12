package cn.leadpad.pospal.common.utils.http;

public class RequestHeader{
	private String headerName;
	private String headerValue;
	public RequestHeader(String headerName, String headerValue) {
		this.headerName = headerName;
		this.headerValue = headerValue;
	}
	public String getHeaderName() {
		return headerName;
	}
	public String getHeaderValue() {
		return headerValue;
	}
	
}
