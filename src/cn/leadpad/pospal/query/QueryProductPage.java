package cn.leadpad.pospal.query;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import cn.leadpad.pospal.constants.Key;
import cn.leadpad.pospal.openapi.sdk.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QueryProductPage {
    // fresh fish, category uid
    //1632812446686375714

    static List<ProductData> productDataList = new ArrayList<>();

    public static List<ProductData> run (long categoryUid) throws IOException {
        int wantQueryRecordSize = 25;
        int storeRecordSize = 15;

        String urlString = Key.POS_URL_PREFIX+ Key.POS_URL_MIDDLE_PRODUCT + Key.QUERY_PRODUCT_PAGES;
        UserCertificate certificate = new UserCertificate(Key.POS_APP_ID, Key.POS_APP_KEY);
        CategoryDetail categoryDetail = new CategoryDetail(categoryUid);

        boolean queryNextPage = true;
        RequestProductData requestProductData = new RequestProductData();
        do {
            ResponseData sendRequestData = PospalApiService
                    .sendRequestData(urlString, requestProductData, categoryDetail ,certificate);
            String responseContent = sendRequestData.getResponseContent();
            System.out.println("1. responseContent " + QueryProductPage.class.getSimpleName());
            System.out.println(responseContent);
            // Convert the data returned by the server into a java instance for processing
            ProductResponseData productResponseData = JsonConvertor.fromJson(responseContent, ProductResponseData.class);

            // Whether to query the next page of data.
            // wantQueryRecordSize > realQueryRecordSize indicates that all data has been taken out。
            int realQueryRecordSize = productResponseData.getData().getResult().size();
            queryNextPage = (wantQueryRecordSize > realQueryRecordSize);
            System.out.println("2.");
            System.out.println("wantQueryRecordSize="+wantQueryRecordSize+",realQueryRecordSize="+realQueryRecordSize);

            List<ProductInfo> products =  productResponseData.getData().getResult();

            for (ProductInfo product : products) {
                if (productDataList.size() <= storeRecordSize-1) {
                    ProductData productData = new ProductData(
                            product.getUid(),product.getCategoryUid(),product.getName(),product.getBarcode(),product.getBuyPrice(),
                            product.getSellPrice(),product.getStock(), product.getCustomerPrice()
                            ,product.getDescription(),product.getAttribute1(),product.getAttribute2(),
                            product.getAttribute3(),product.getAttribute4());

                    productDataList.add(productData);
                }
            }

            // Set the return parameters required to query the next page, postBackParameter
            requestProductData.setPostBackParameter(productResponseData.getData().getPostBackParameter());

        } while(queryNextPage);

        System.out.println(QueryProductPage.class.getSimpleName()+ ", array list size = " + productDataList.size());

        return productDataList;

    }



    static class RequestProductData  extends PosaplOpenApiRequestData {
        PostBackParameter postBackParameter;

        void setPostBackParameter (PostBackParameter postBackParameter) {
            this.postBackParameter = postBackParameter;
        }
    }


    static class ProductResponseData {
        ProductResponseDataDetail data;

      ProductResponseDataDetail getData() {
            return data;
        }
    }

    static class ProductResponseDataDetail {
        PostBackParameter postBackParameter;
        List<ProductInfo> result;
        int pageSize;

        public PostBackParameter getPostBackParameter() {
            return postBackParameter;
        }

        public List<ProductInfo> getResult() {
            return result;
        }

        public int getPageSize() {
            return pageSize;
        }
    }
    static class ProductInfo {
        private long uid;
        private long categoryUid;
        private String name;
        private String barcode;
        private BigDecimal buyPrice;
        private BigDecimal sellPrice;
        private BigDecimal stock;
        private String pinyin;
        private BigDecimal customerPrice;
        private int isCustomerDiscount;
        private String description;
        private String attribute1;
        private String attribute2;
        private String attribute3;
        private String attribute4;

        public long getUid() {
            return uid;
        }

        public long getCategoryUid() {
            return categoryUid;
        }

        public String getName() {
            return name;
        }

        public String getBarcode() {
            return barcode;
        }

        public BigDecimal getBuyPrice() {
            return buyPrice;
        }

        public BigDecimal getSellPrice() {
            return sellPrice;
        }

        public BigDecimal getStock() {
            return stock;
        }

        public String getPinyin() {
            return pinyin;
        }

        public BigDecimal getCustomerPrice() {
            return customerPrice;
        }

        public int getIsCustomerDiscount() {
            return isCustomerDiscount;
        }

        public String getDescription() {
            return description;
        }

        public String getAttribute1() {
            return attribute1;
        }

        public String getAttribute2() {
            return attribute2;
        }

        public String getAttribute3() {
            return attribute3;
        }

        public String getAttribute4() {
            return attribute4;
        }
    }

}
