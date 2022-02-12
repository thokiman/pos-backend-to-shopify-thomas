package cn.leadpad.pospal.query;

import cn.leadpad.pospal.common.utils.JsonConvertor;
import cn.leadpad.pospal.common.utils.http.ResponseData;
import cn.leadpad.pospal.constants.Key;
import cn.leadpad.pospal.openapi.sdk.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryProductCategoryPages {


    static List<CategoryProductData> categoryProductDataList = new ArrayList<>();

    public static List<CategoryProductData> run() throws IOException {
        // pageSize -> this query is expected to take the number of records from the inventor.
        // if the length of the result / real query size < want query size then no query is required for next page
        int wantQueryRecordSize = 25;
        int storeRecordSize = 25;

        String urlString = Key.POS_URL_PREFIX + Key.POS_URL_MIDDLE_PRODUCT + Key.QUERY_PRODUCT_CATEGORY_PAGES;

        UserCertificate certificate = new UserCertificate(Key.POS_APP_ID, Key.POS_APP_KEY);

        boolean queryNextPage = true;
        RequestCategoryData requestCategoryData = new RequestCategoryData();

        do {
            ResponseData sendRequestData = PospalApiService.sendRequestData(urlString, requestCategoryData, certificate);
            String responseContent = sendRequestData.getResponseContent();

            System.out.println("1. responseContent " + QueryProductCategoryPages.class.getSimpleName());
            System.out.println(responseContent);

            CategoryResponseData categoryResponseData = JsonConvertor.fromJson(responseContent, CategoryResponseData.class);

//            wantQueryRecordSize = categoryResponseData.getData().getPageSize();
            int realQueryRecordSize = categoryResponseData.getData().getResult().size();
            queryNextPage = (wantQueryRecordSize > realQueryRecordSize);
            System.out.println("wantQueryRecordSize="+wantQueryRecordSize+",realQueryRecordSize="+realQueryRecordSize);

            List<CategoryProduct> categoryProducts =  categoryResponseData.getData().getResult();

            for (CategoryProduct categoryProduct : categoryProducts) {

                if (categoryProductDataList.size() <= storeRecordSize-1) {
                    CategoryProductData categoryProductData = new CategoryProductData(
                            categoryProduct.getCategoryUid(),categoryProduct.getCategoryParentUid(),categoryProduct.getName());

                    categoryProductDataList.add(categoryProductData);
                }

            }
            requestCategoryData.setPostBackParameter(categoryResponseData.getData().getPostBackParameter());


        }while (queryNextPage);


        System.out.println(QueryProductCategoryPages.class.getSimpleName()+ ", array list size = " + categoryProductDataList.size());

        return categoryProductDataList;
    }

    static class RequestCategoryData extends PosaplOpenApiRequestData {
        PostBackParameter postBackParameter;

        void setPostBackParameter(PostBackParameter postBackParameter) {
            this.postBackParameter = postBackParameter;
        }
    }

    static class CategoryResponseData extends PosaplOpenApiResponseData {
        CategoryResponseDataDetail data;

        CategoryResponseDataDetail getData () {
            return data;
        }
    }
    static class CategoryResponseDataDetail {
            PostBackParameter postBackParameter;
            List<CategoryProduct> result ;
            int pageSize;

        public PostBackParameter getPostBackParameter() {
            return postBackParameter;
        }

        public List<CategoryProduct> getResult() {
            return result;
        }

        public int getPageSize() {
            return pageSize;
        }
    }
    static class CategoryProduct {
        private long categoryUid;
        private long categoryParentUid;
        private String name;

        public long getCategoryUid() {
            return categoryUid;
        }

        public long getCategoryParentUid() {
            return categoryParentUid;
        }

        public String getName() {
            return name;
        }
    }

 }
