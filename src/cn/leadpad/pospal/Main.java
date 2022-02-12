package cn.leadpad.pospal;

import cn.leadpad.pospal.constants.Key;
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
        List<POSProduct> items = addPOSProduct();
        System.out.println(Main.class.getSimpleName() + ", posProducts size = " + items.size());
        System.out.println(Main.class.getSimpleName() + ", posProducts list = \n" + items.toString());

        int i = 0;
        for (POSProduct item : items) {
            System.out.println("Start to add POS product : " + item.getNameProduct());
            addShopifyProduct(item);
            i++;
            System.out.println("POS product at weebo-cafe.myshopify.com : " + item.getNameProduct());
        }
    }

    static void test() throws IOException {

        System.out.println(QueryProductImagePages.class.getSimpleName() + ", array = " + "\n"
                + queryProductImagePages().toString());

        System.out.println(QueryProductCategoryPages.class.getSimpleName() + ", array = " + "\n"
                + queryCategoryProductPages().toString());

//        fresh fish, categoryUid = 1632812446686375714
        long categoryUid = 1632812446686375714L;
        System.out.println(QueryProductPage.class.getSimpleName() + ", array = " + "\n"
                + queryProductPages(categoryUid).toString());
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
                product.getCustomerPriceProduct() +
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
                product.getStockProduct() +
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

    static List<POSProduct> addPOSProduct() throws IOException {
        List<POSProduct> posProducts = new ArrayList<>();

        List<ProductImageData> images = queryProductImagePages();
        List<CategoryProductData> categories = queryCategoryProductPages();

        for (CategoryProductData category : categories) {
            List<ProductData> products = queryProductPages(category.getCategoryUid());

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

    static List<CategoryProductData> queryCategoryProductPages() throws IOException {
        return QueryProductCategoryPages.run();

    }

    static List<ProductData> queryProductPages(long categoryUid) throws IOException {
        return QueryProductPage.run(categoryUid);

    }


}
