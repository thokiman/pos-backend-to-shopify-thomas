package cn.leadpad.pospal.query;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import cn.leadpad.pospal.constants.Key;
import cn.leadpad.pospal.openapi.sdk.*;

import java.io.IOException;
import java.util.*;

public class QueryProductImagePages {

    static List<ProductImageData> categoryProductDataList = new ArrayList<>();

    public static List<ProductImageData> run() throws IOException {
        // Replace the contents of the following three variables with your credential information
        String appId = Key.POS_APP_ID;
        String appKey = Key.POS_APP_KEY;


        String urlString = Key.POS_URL_PREFIX+ Key.POS_URL_MIDDLE_PRODUCT + Key.QUERY_PRODUCT_IMAGES_PAGES;
        //String urlString = "http://localhost:8080/pospal-api2/openapi/v1/productOpenApi/queryProductImagePages";
        // Access credentials for the open interface of Yinbao's cash register system
        UserCertificate certificate = new UserCertificate(appId, appKey);



        // Set the code server, the number sent by java needs to manually set the proxy, fiddler can catch the package.
        // HttpUtil.setCustomProxy("127.0.0.1", 8888);

        // Whether to query the next page of data.
        boolean queryNextPage = true;
        RequestImageData requestImageData = new RequestImageData();
        do {
            // Send request parameters and get the returned data from the server
            ResponseData sendRequestData = PospalApiService
                    .sendRequestData(urlString, requestImageData, certificate);
            String responseContent = sendRequestData.getResponseContent();
            System.out.println("1. responseContent " + QueryProductImagePages.class.getSimpleName());
            System.out.println(responseContent);
            // Convert the data returned by the server into a java instance for processing
            ImageResponseData imageResponseData = JsonConvertor.fromJson(responseContent, ImageResponseData.class);

            // Whether to query the next page of data.
            // wantQueryRecordSize > realQueryRecordSize indicates that all data has been taken out。
            int wantQueryRecordSize = imageResponseData.getData().getPageSize();
            int realQueryRecordSize = imageResponseData.getData().getResult().size();
            queryNextPage = !(wantQueryRecordSize > realQueryRecordSize);
            System.out.println("2.");
            System.out.println("wantQueryRecordSize="+wantQueryRecordSize+",realQueryRecordSize="+realQueryRecordSize);

            List<ProductImage> productImages =  imageResponseData.getData().getResult();

            for (ProductImage productImage : productImages) {
                ProductImageData productImageData = new ProductImageData(
                        productImage.getProductUid(),productImage.getProductName(),productImage.getImageUrl());

                categoryProductDataList.add(productImageData);
            }


            // Set the return parameters required to query the next page, postBackParameter
            requestImageData.setPostBackParameter(imageResponseData.getData().getPostBackParameter());

        } while(queryNextPage);

        return categoryProductDataList;
    }


    static class RequestImageData extends PosaplOpenApiRequestData {
        PostBackParameter postBackParameter;

        void setPostBackParameter(PostBackParameter postBackParameter) {
            this.postBackParameter = postBackParameter;
        }
    }

    static class ImageResponseData extends PosaplOpenApiResponseData {
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
        private long productUid;
        private String productName;
        private String productBarcode;
        private String imageUrl;

        long getProductUid() {
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
