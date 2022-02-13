package cn.leadpad.pospal;

import cn.leadpad.pospal.constants.Key;
import cn.leadpad.pospal.constants.Test;
import cn.leadpad.pospal.openapi.sdk.PospalApiService;
import cn.leadpad.pospal.query.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        boolean hasPushPosProductToShopify = true;
        // options for test method
        Test options = Test.RUN_PRODUCT_CATEGORY_PAGES;
//{"uid":568853644844377361,"categoryUid":1554458415666862399,"name":"Potato - $4/KG","barcode":"1904041444504",
// "buyPrice":0.400,"sellPrice":4.00,"sellPrice2":4.00,"stock":-2.000,"maxStock":0,"minStock":0,"noStock":0,"pinyin":"Potato - $4/KG",
// "customerPrice":0.90,"description":"","isCustomerDiscount":1,"supplierUid":0,"enable":1,"productionDate":"",
// "createdDatetime":"2019-04-04 02:46:02","updatedDatetime":"2021-06-16 04:21:57",
// "attribute1":"y","attribute2":"","attribute3":"","attribute6":""},

        // query for test and real example
        int queryRecordProductCategory = 25;
        int storeRecordProductCategory = 2;
        int queryRecordProductPage = 25;
        int storeRecordProductPage = 10;
        // test POS Backend purposes
        long freshFishCategoryUid = 1632812446686375714L;

        if (hasPushPosProductToShopify) {
            sendPosToShopify(queryRecordProductCategory, storeRecordProductCategory, queryRecordProductPage, storeRecordProductPage);
        } else {
            switch (options) {
                case RUN_PRODUCT_CATEGORY_PAGES:
                    runTestProductCategoryPages(queryRecordProductCategory, storeRecordProductCategory);
                    break;
                case RUN_PRODUCT_PAGES:
                    runTestProductPages(freshFishCategoryUid, queryRecordProductPage, storeRecordProductPage);
                    break;
                case RUN_PRODUCT_IMAGE_URL:
                    runTestProductImagePages();
                    break;
                default:
                    System.out.println("Please type the options!");
                    break;
            }
        }
    }

    static void runTestProductImagePages() throws IOException {

        System.out.println(QueryProductImagePages.class.getSimpleName() + ", array = " + "\n"
                + queryProductImagePages().toString());

    }
    static void runTestProductCategoryPages(int queryRecordProductCategory, int storeRecordProductCategory) throws IOException {
        System.out.println(QueryProductCategoryPages.class.getSimpleName() + ", array = " + "\n"
                + queryProductCategoryPages(queryRecordProductCategory, storeRecordProductCategory).toString());

    }
    static void runTestProductPages(long categoryUid ,int queryRecordProductPage, int storeRecordProductPage) throws IOException {
          System.out.println(QueryProductPage.class.getSimpleName() + ", array = " + "\n"
               + queryProductPages(categoryUid, queryRecordProductPage, storeRecordProductPage).toString());
    }

    static void sendPosToShopify (int queryRecordProductCategory, int storeRecordProductCategory, int queryRecordProductPage, int storeRecordProductPage)  throws IOException{
        List<POSProduct> items = addPOSProduct(queryRecordProductCategory,  storeRecordProductCategory, queryRecordProductPage,  storeRecordProductPage);
        System.out.println( "items!!! = "  + items.size());

        System.out.println(Main.class.getSimpleName() + ", posProducts size = " + items.size());
        System.out.println(Main.class.getSimpleName() + ", posProducts list = \n" + items.toString());

        String tempItem = "";
        for (POSProduct item : items) {

            if (!item.getNameProduct().equals(tempItem)) {
                System.out.println("Different item is added to shopify = " + item.getNameProduct());
                System.out.println("Start to add POS product : " + item.getNameProduct());
                addShopifyProduct(item);
                System.out.println("POS product at weebo-cafe.myshopify.com : " + item.getNameProduct());
            }
            System.out.println("Duplicate item is not added");
            tempItem = item.getNameProduct();

        };

    }
    static void addShopifyProduct(POSProduct product) throws ClientProtocolException, IOException {
        String postUrl = Key.SHOPIFY_URL_PREFIX + Key.SHOPIFY_POST_PRODUCT_INFO;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dtfNow = dtf.format(now);

        String jsonString = "{\n" +
                "    \"product\": {\n" +
                "        \"title\": \"" +
                product.getNameProduct() +
                "\",\n" +
                "        \"body_html\": \"\\u003cstrong\\u003e" +
                product.getDescription() +
                "\\u003c\\/strong\\u003e\",\n" +
                "        \"product_type\": \"" +
                product.getCategoryNameProduct() +
                "\",\n" +
                "        \"variants\": [\n" +
                "            {\n" +
                "                \"price\": " +
                product.getCustomerPriceProduct().intValue() +
                ",\n" +
                "                \"barcode\": \"" +
                product.getBarcodeProduct() +
                "\",\n" +
                "                \"created_at\": \"" +
                dtfNow +
                "\",\n" +
                "                \"product_id\": " +
                product.getUidProduct() +
                ",\n" +
                "                \"inventory_quantity\": " +
                 product.getStockProduct().intValue() +
                "\n" +
                "            }\n" +
                "        ],\n" +
                "        \"images\": [\n" +
                "            {\n" +
                "                \"src\": " +
                product.getImageUrlProduct() +
                "\n" +
                "            }\n" +
                "        ],\n" +
                "        \"tags\": [\n" +
                "            \"" +
                product.getAttribute1() +
                "\",\n" +
                "            \"" +
                product.getAttribute2() +
                "\",\n" +
                "            \"" +
                product.getAttribute3() +
                "\",\n" +
                "            \"" +
                product.getAttribute4() +
                "\"\n" +
                "        ]\n" +
                "    }\n" +
                "}";


        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString);
        String jsonObjectAsString = jsonObject.toString();

        PospalApiService.sendPostToShopify(postUrl, jsonObjectAsString, Key.SHOPIFY_ADMIN_ACCESS_TOKEN_HEADER);
    }

    static List<POSProduct> addPOSProduct(int queryRecordProductCategory, int storeRecordProductCategory, int queryRecordProductPage, int storeRecordProductPage) throws IOException {
        List<POSProduct> posProducts = new ArrayList<>();

        List<ProductImageData> images = queryProductImagePages();
        List<CategoryProductData> categories = queryProductCategoryPages( queryRecordProductCategory,  storeRecordProductCategory);

        for (CategoryProductData category : categories) {
            List<ProductData> products = queryProductPages(category.getCategoryUid(), queryRecordProductPage,  storeRecordProductPage);

            for (ProductData product : products) {
                for (ProductImageData image : images) {
                    String imageStr;

                    if (product.getUid() == image.getProductUid()) {
                        imageStr = image.getImageUrl();
                    } else {
                        imageStr = "\"\"";
                    }

                    POSProduct posProduct = new POSProduct(product.getUid(), product.getCategoryUid(),
                            category.getName(), product.getName(), product.getBarcode(), product.getBuyPrice(), product.getSellPrice(),
                            product.getCustomerPrice(), product.getStock(), imageStr, product.getDescription(),
                            product.getAttribute1(), product.getAttribute2(), product.getAttribute3(), product.getAttribute4());

                    posProducts.add(posProduct);
                }
            }
        }
        return posProducts;
    }

    static List<ProductImageData> queryProductImagePages() throws IOException {
        return QueryProductImagePages.run();

    }

    static List<CategoryProductData> queryProductCategoryPages(int queryRecordProductCategory, int storeRecordProductCategory) throws IOException {

        return QueryProductCategoryPages.run(queryRecordProductCategory, storeRecordProductCategory);

    }

    static List<ProductData> queryProductPages(long categoryUid, int queryRecordProductPage, int storeRecordProductPage) throws IOException {
        return QueryProductPage.run(categoryUid,queryRecordProductPage,storeRecordProductPage);

    }


}
