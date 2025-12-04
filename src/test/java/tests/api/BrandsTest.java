package tests.api;

import assertions.api.BrandsAssertions;
import base.BaseTestApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.brands.Brand;
import pageobjects.api.brands.ResponseBrands;
import testUtils.ApiTestUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


public class BrandsTest extends BaseTestApi {

    private final String brandsApiPath = "brandsList";

    private ResponseBrands getBrandsResponse() {
        return given()
                .when().get(brandsApiPath)
                .then()
                .extract()
                .as(ResponseBrands.class);
    }

    @Test(testName = "API 3: Get All Brands List", groups = {"API"})
    public void getAllBrandsList() {
        ResponseBrands responseBrands = getBrandsResponse();

        Assert.assertEquals(responseBrands.getResponseCode(), 200, "Response code should be 200");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseBrands.getBrands().isEmpty(), "Brands list should not be empty");

        BrandsAssertions.verifyDuplicateIds(responseBrands.getBrands(),
                Brand::getId, Brand::getBrand, softAssert);

        softAssert.assertAll();
    }

    @Test(groups = {"API"})
    public void verifyNoDuplicateBrandNamesIgnoreCaseStream() {
        ResponseBrands responseBrands = getBrandsResponse();
        SoftAssert softAssert = new SoftAssert();

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
        softAssert.assertAll();
    }

    @Test(groups = {"API"})
    public void verifyBrandsListExactMatch() {
        ResponseBrands responseBrands = getBrandsResponse();

        Set<String> expectedBrands = Set.of("Polo",
                "H&M",
                "Madame",
                "Mast & Harbour",
                "Babyhug",
                "Allen Solly Junior",
                "Kookie Kids",
                "Biba");

        Set<String> actualBrands = new HashSet<>();
        for (Brand brand : responseBrands.getBrands()) {
            actualBrands.add(brand.getBrand());
        }
        SoftAssert softAssert = new SoftAssert();

        for (String expected : expectedBrands) {
            softAssert.assertTrue(actualBrands.contains(expected),
                    "Mandatory brand missing: " + expected);
        }

        for (String actual : actualBrands) {
            softAssert.assertTrue(expectedBrands.contains(actual),
                    "Unexpected brand found: " + actual);
        }
        softAssert.assertAll();
    }

    @Test(testName = "API 4: PUT To All Brands List", groups = {"API"})
    public void putToAllBrandsList() {

        BrandsAssertions.verifyMethodNotSupported(brandsApiPath, "PUT", "405",
                "This request method is not supported.");

    }
}