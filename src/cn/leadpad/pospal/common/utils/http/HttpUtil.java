/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.common.utils.http;


import cn.leadpad.pospal.common.utils.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.net.Proxy.Type;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;


/**
 * @Type: cn.xmllk.common.utils.HttpUtil
 * @ClassName: HttpUtil
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年6月27日 下午10:04:31
 * @Description: <br/>
 */
public class HttpUtil {
	private static final Logger LOG= Logger.getLogger(HttpUtil.class);
	
	private static Proxy customProxy = null;
	public static void setCustomProxy(String hostName,int port){
		customProxy = new Proxy(Type.HTTP, new InetSocketAddress(hostName, port));
	}
	private static Proxy getCustomProxy(){
		return customProxy;
	}
	public static void clearCustomProxy(){
		customProxy = null;
	}
	
	
	public static ResponseData get(String urlString) throws IOException{
        return get(urlString, "UTF-8",null);
	}
	
	public static ResponseData get(String urlString,String resultCharSetName,Collection<RequestHeader> headers) throws IOException{
		return execute(urlString, "GET", null, resultCharSetName, headers);
	}
	
	public static ResponseData post(String urlString,String data,Collection<RequestHeader> headers) throws IOException{
		return post(urlString, data == null ? null : data.getBytes("UTF-8"),"UTF-8",headers);
	}
	
	public static ResponseData post(String urlString,byte[] data,String resultCharSetName,Collection<RequestHeader> headers) throws IOException{
		return execute(urlString, "POST", data, resultCharSetName, headers);
	}
	
	private static ResponseData execute(String urlString,String requestMethod,byte[] data,String resultCharSetName,Collection<RequestHeader> headers) throws IOException{
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;
        ResponseData responData = null;
        try {
        	urlConnection = createUrlConnection(urlString);
	        urlConnection.setRequestMethod(requestMethod);
	        urlConnection.setDoInput(true);
	        urlConnection.setUseCaches(false);
	        urlConnection.setConnectTimeout(10000);
	        urlConnection.setReadTimeout(10000);
	        setRequestHeader(urlConnection, headers);
	        if(StringUtils.equalsIgnoreCase("POST", requestMethod)){
	        	urlConnection.setDoOutput(true);
	        	outputStream = urlConnection.getOutputStream();
	        	data = data != null ? data : new byte[0];
	        	outputStream.write(data);
	        	outputStream.flush();
	        }
	        
	        responData = fetchResponseData(urlConnection, resultCharSetName);

		} catch (MalformedURLException e) {
			LOG.debug(e.getMessage(),e);
		} finally {
        	
        	IOUtils.closeQuietly(outputStream);
        	
        	if(urlConnection != null) {
        		urlConnection.disconnect();
        	}
       }
       return responData;
	}
	
	private static void setRequestHeader(HttpURLConnection urlConnection,Collection<RequestHeader> headers){
		if(headers != null && headers.size() > 0){
        	for(RequestHeader header : headers) {
        		urlConnection.setRequestProperty(header.getHeaderName(), header.getHeaderValue());
        	}
        }
	}
	private static HttpURLConnection createUrlConnection(String urlString) throws IOException{
		if(urlString.toLowerCase().startsWith("http://")){
			return createHttpUrlConnection(urlString);
		} else {
			try {
				return createHttpsUrlConnection(urlString);
			} catch (KeyManagementException | NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private static HttpURLConnection createHttpUrlConnection(String urlString) throws IOException {
		URL url = new URL(urlString);
		if(getCustomProxy() == null) {
			return (HttpURLConnection) url.openConnection();
		} else {
			return (HttpURLConnection) url.openConnection(getCustomProxy());
		}
	}
	
	private static HttpsURLConnection createHttpsUrlConnection(String urlString) 
			throws NoSuchAlgorithmException, KeyManagementException, IOException{
		SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new java.security.SecureRandom());
 
        URL url = new URL(urlString);
        HttpsURLConnection conn;
		if(getCustomProxy() == null) {
			conn = (HttpsURLConnection) url.openConnection();
		} else {
			conn = (HttpsURLConnection) url.openConnection(getCustomProxy());
		}
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        return conn;
	}
	
	
	private static class TrustAnyTrustManager implements X509TrustManager {
		 
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
 
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
	
	
	private static ResponseData fetchResponseData(HttpURLConnection urlConnection,String resultCharSetName) throws IOException{
		InputStream inputStream = null;
		try {
			int responseCode = urlConnection.getResponseCode();
	        ResponseData responseData = new ResponseData();
	        String contentEncoding = urlConnection.getContentEncoding();
	        responseData.setContentEncoding(contentEncoding);
        	responseData.setResponseCode(responseCode);
        	responseData.setContentLength(urlConnection.getContentLength());
        	responseData.setContentType(urlConnection.getContentType());
        	responseData.setDate(urlConnection.getDate());
        	responseData.setExpiration(urlConnection.getExpiration());
        	responseData.setLastModifed(urlConnection.getLastModified());
        	
        	inputStream = urlConnection.getInputStream();
        	if(StringUtils.equalsIgnoreCase("deflate", contentEncoding)){
        		inputStream = new DeflaterInputStream(inputStream);
        	} else if(StringUtils.equalsIgnoreCase("gzip", contentEncoding)){
        		inputStream = new GZIPInputStream(inputStream);
        	}
        	responseData.setResponseContent(IOUtils.toString(inputStream,resultCharSetName));
        	return responseData;
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}
}
