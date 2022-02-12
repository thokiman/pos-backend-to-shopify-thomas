package cn.leadpad.pospal.constants;

public class Key {

    public static final String POS_APP_ID = "4436939EE5757EBC9AF59921004FB124";
    public static final String POS_APP_KEY = "743404288889468019";
    public static final String POS_URL_PREFIX = "https://area9-win.pospal.cn:443/pospal-api2";
    public static final String POS_URL_MIDDLE_PRODUCT = "/openapi/v1/productOpenApi/";
    public static final String POS_URL_MIDDLE_SUPPLIER = "/openapi/v1/supplierOpenApi/";
    public static final String QUERY_PRODUCT_CATEGORY_PAGES = "queryProductCategoryPages";
    public static final String QUERY_PRODUCT_IMAGES_PAGES = "queryProductImagePages";
    public static final String QUERY_PRODUCT_BY_BARCODE = "queryProductByBarcode";
    public static final String QUERY_PRODUCT_PAGES = "queryProductPages";
    public static final String UPDATE_PRODUCT_INFO = "updateProductInfo";
    public static final String QUERY_PRODUCT_BY_UID = "queryProductByUid";
    public static final String ADD_PRODUCT_INFO = "addProductInfo";
    public static final String QUERY_PRODUCT_UNIT_DEF = "queryAllProductUnitDef";
    public static final String QUERY_SUPPLIER_PAGES = "querySupplierPages";
    public static final String QUERY_PRODUCT_BARCODE = "queryProductBarcodes";


    // Access Shopify Admin
    // Create a Private app, this will give you Access Token and Password.
    // Those need to be used with Basic Auth in Postman
    public static final String SHOPIFY_API_KEY = "099a349cd8049cdeb166110b1f5c6fee";
    public static final String SHOPIFY_API_SECRET_KEY = "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";
    public static final String SHOPIFY_API_VERSION = "2022-01";
    public static final String SHOPIFY_ADMIN_ACCESS_TOKEN_HEADER = "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";
    public static final String SHOPIFY_STOREFRONT_ACCESS_TOKEN_HEADER = "cdb1e9c549a4f1e1ec9b454f8c78bce5";
    public static final String SHOPIFY_STOREFRONT_NAME = "weebo-cafe.myshopify.com";
    public static final String SHOPIFY_POST_PRODUCT_INFO = "products.json";
    public static final String SHOPIFY_POST_PRODUCT_IMAGE = "products/7526486900983/images.json";
    public static final String SHOPIFY_URL_PREFIX = "https://" + SHOPIFY_API_KEY + ":" + SHOPIFY_API_SECRET_KEY
            + "@" + SHOPIFY_STOREFRONT_NAME + "/admin/api/"
            + SHOPIFY_API_VERSION + "/";


}
