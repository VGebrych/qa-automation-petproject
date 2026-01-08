package assertions.ui;

import org.testng.Assert;
import pageobjects.api.products.Product;
import utils.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsUiAssertions {

    public static void assertUiMatchesApiProducts(List<String> uiProductNames, List <Product> apiProducts){
        Set<String> uiNormalized = uiProductNames.stream()
                .map(Utils::normalizeString)
                .collect(Collectors.toSet());

        Set<String> apiNormalized = apiProducts.stream()
                .map(Product::getName)
                .map(Utils::normalizeString)
                .collect(Collectors.toSet());

        Assert.assertEquals(uiNormalized, apiNormalized, "UI products list does not match API response");
    }

    public static void assertProductsListEmptyState(
            boolean isUiEmpty, boolean expectEmpty, String query
    ) {
        Assert.assertEquals(
                isUiEmpty,
                expectEmpty,
                "UI products empty state does not match expectation for search query: " + query
        );
    }
}