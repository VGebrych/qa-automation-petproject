package assertions.api;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageobjects.api.products.Product;
import pageobjects.api.products.ResponseProducts;
import utils.Utils;

import java.util.Arrays;

public class ProductsAssertions extends BaseApiAssertion {

    public static void assertAllProducts(ResponseProducts responseProducts) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseProducts.getProducts().isEmpty(), "Product list should not be empty");
        // Validate usertype
        for (Product p : responseProducts.getProducts()) {
            softAssert.assertTrue(Arrays.asList("Women", "Men", "Kids").contains(p.getCategory().getUsertype().getUsertype()),
                    "Usertype is invalid: " + p.getCategory().getUsertype().getUsertype());
        }
        softAssert.assertAll();
    }

    public static void assertNoDuplicateIds(ResponseProducts responseProducts, SoftAssert softAssert) {
        BaseApiAssertion.verifyDuplicateIds(responseProducts.getProducts(), Product::getId,
                Product::getName, softAssert);
    }

    public static void assertProductsNotEmptyIfExpected(ResponseProducts searchProductResponse, String query, boolean expectEmpty) {
        if (expectEmpty) {
            Assert.assertTrue(searchProductResponse.getProducts().isEmpty(),
                    "Expected empty result for query: " + query);
        } else {
            Assert.assertFalse(searchProductResponse.getProducts().isEmpty(),
                    "Expected non-empty result for query: " + query);
        }
    }

    public static void assertProductFieldsAndRelevance(ResponseProducts searchProductResponse, String query) {
        SoftAssert softAssert = new SoftAssert();
        for (Product p : searchProductResponse.getProducts()) {
            softAssert.assertNotNull(p.getName(), "Product name is null");
            softAssert.assertNotNull(p.getPrice(), "Product price is null");
            softAssert.assertNotNull(p.getBrand(), "Product brand is null");
            softAssert.assertNotNull(p.getCategory(), "Product category is null");

            // 4. Flexible relevance check if query is provided
            if (query != null && !query.isEmpty()) {
                String normalizedQuery = Utils.normalizeString(query);
                String normalizedName = Utils.normalizeString(p.getName());
                String normalizedCategory = Utils.normalizeString(p.getCategory().getCategory());

                softAssert.assertTrue(
                        normalizedName.contains(normalizedQuery) || normalizedCategory.contains(normalizedQuery),
                        "Product not relevant for query '" + query + "': " + p.getName() +
                                " | Category: " + p.getCategory().getCategory()
                );
            }
        }
        softAssert.assertAll();
    }
}