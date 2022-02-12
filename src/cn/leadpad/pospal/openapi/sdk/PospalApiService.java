/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.openapi.sdk;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.MD5Util;
import cn.leadpad.pospal.common.utils.http.HttpUtil;
import cn.leadpad.pospal.common.utils.http.RequestHeader;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Type: cn.leadpad.pospal.common.PospalApiSerivce
 * @ClassName: PospalApiSerivce
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0
 * @Date: 2015年12月18日 下午4:23:13
 * @Description: <br/>
 */
public class PospalApiService {

    private static List<RequestHeader> getCommonRequestHeader() {
        List<RequestHeader> headers = new ArrayList<>();
        headers.add(new RequestHeader("User-Agent", "openApi"));
        headers.add(new RequestHeader("Content-Type", "application/json; charset=utf-8"));
        headers.add(new RequestHeader("accept-encoding", "gzip,deflate"));

        return headers;
    }

    public static ResponseData sendRequestData(String urlString, PosaplOpenApiRequestData requestData, UserCertificate certificate) throws IOException {
        return sendRequestData(urlString, requestData, certificate, System.currentTimeMillis());
    }

    public static ResponseData sendRequestData(String urlString, PosaplOpenApiRequestData requestData, CategoryDetail categoryDetail, UserCertificate certificate) throws IOException {
        return sendRequestData(urlString, requestData, categoryDetail, certificate, System.currentTimeMillis());
    }

    public static ResponseData sendRequestData(String urlString, PosaplOpenApiRequestData requestData, UserCertificate certificate, long requetTime) throws IOException {
        requestData.setAppId(certificate.getAppId());
        String content = JsonConvertor.toJson(requestData);
        Collection<RequestHeader> headers = getCommonRequestHeader();
        try {
            String dataSignature = MD5Util.encryptToMd5String(content, certificate.getAppKey());
            headers.add(new RequestHeader("data-signature", dataSignature));
            headers.add(new RequestHeader("time-stamp", requetTime + ""));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();// ignore
        }

        return HttpUtil.post(urlString, content, headers);
    }

    public static ResponseData sendRequestData(String urlString, PosaplOpenApiRequestData requestData, CategoryDetail categoryDetail, UserCertificate certificate, long requetTime) throws IOException {
        requestData.setAppId(certificate.getAppId());
        requestData.setCategoryUid(categoryDetail.getCategoryDetailUid());
        String content = JsonConvertor.toJson(requestData);
        Collection<RequestHeader> headers = getCommonRequestHeader();
        try {
            String dataSignature = MD5Util.encryptToMd5String(content, certificate.getAppKey());
            headers.add(new RequestHeader("data-signature", dataSignature));
            headers.add(new RequestHeader("time-stamp", requetTime + ""));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();// ignore
        }

        return HttpUtil.post(urlString, content, headers);
    }

    public static void sendPostToShopify(String stringUrl, String requestBody, String shopifyAccessToken) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(stringUrl);

        StringEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);
        httpPost.setHeader("X-Shopify-Access-Token", shopifyAccessToken);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("accept-encoding", "gzip,deflate");

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(responseBody);

    }
}
