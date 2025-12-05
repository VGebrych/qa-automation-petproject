package assertions.api;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageobjects.api.brands.Brand;
import pageobjects.api.brands.ResponseBrands;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BrandsAssertions extends BaseApiAssertion {

    public static void assertBrandsListNotEmpty(ResponseBrands responseBrands) {
        Assert.assertFalse(responseBrands.getBrands().isEmpty(), "Brands list should not be empty");
    }

    public static void assertNoDuplicateBrandNamesIgnoreCase(ResponseBrands responseBrands, SoftAssert softAssert) {
        Map<String, List<Integer>> duplicates = responseBrands.getBrands().stream()
                .collect(Collectors.groupingBy(
                        b -> b.getBrand().toLowerCase(),
                        Collectors.mapping(Brand::getId, Collectors.toList())
                ));

        duplicates.forEach((brand, ids) -> {
            if (ids.size() > 1) {
                softAssert.fail("Duplicate brand found: " + brand + " (IDs: " + ids + ")");
            }
        });
    }

    public static void assertBrandsExactMatch(ResponseBrands responseBrands, Set<String> expectedBrands, SoftAssert softAssert) {
        Set<String> actualBrands = new HashSet<>();
        for (Brand brand : responseBrands.getBrands()) {
            actualBrands.add(brand.getBrand());
        }

        for (String expected : expectedBrands) {
            softAssert.assertTrue(actualBrands.contains(expected),
                    "Mandatory brand missing: " + expected);
        }

        for (String actual : actualBrands) {
            softAssert.assertTrue(expectedBrands.contains(actual),
                    "Unexpected brand found: " + actual);
        }
    }
}