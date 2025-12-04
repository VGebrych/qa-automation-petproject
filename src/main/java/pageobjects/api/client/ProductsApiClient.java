package pageobjects.api.client;
import io.restassured.response.Response;
import pageobjects.api.products.ResponseProducts;

import java.util.Map;

public class ProductsApiClient extends BaseApiClient {
    public static final String productsApiPath = "productsList";
    public static final String searchProductApiPath = "searchProduct";

    public ResponseProducts getAllProductList (){
        return get(productsApiPath).as(ResponseProducts.class);
    }

    public ResponseProducts searchProduct(String query){
        return postForm(searchProductApiPath, Map.of("search_product", query))
                .as(ResponseProducts.class);
    }

    public Response searchProductWithoutParam() {
        return postForm(searchProductApiPath, Map.of());
    }
}