package pageobjects.api.client;

import io.restassured.response.Response;
import pageobjects.api.products.ResponseProducts;

import java.util.HashMap;
import java.util.Map;

public class ProductsApiClient extends BaseApiClient {
    private static final String productsApiPath = "productsList";
    private static final String searchProductApiPath = "searchProduct";

    public static String getProductsApiPath() {
        return productsApiPath;
    }

    public ResponseProducts getAllProductList() {
        return get(productsApiPath).as(ResponseProducts.class);
    }

    public ResponseProducts searchProduct(String query) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("search_product", query);

        return postForm(searchProductApiPath, formParams)
                .as(ResponseProducts.class);
    }

    public Response searchProductWithoutParam() {
        return postForm(searchProductApiPath, new HashMap<>());
    }
}