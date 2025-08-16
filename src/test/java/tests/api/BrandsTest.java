package tests.api;

import base.BaseTestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.products.brands.Brand;
import pageobjects.api.products.brands.ResponseBrands;
import utils.Utils;

import java.util.HashSet;
import java.util.Set;

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

    @Test (testName = "API 3: Get All Brands List")
    public void getAllBrandsList() {
        ResponseBrands responseBrands = getBrandsResponse();

        Assert.assertEquals(responseBrands.getResponseCode(), 200);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseBrands.getBrands().isEmpty(), "Brands list should not be empty");

        Set<Integer> uniqueIds = new HashSet<>();
        for (Brand b : responseBrands.getBrands()) {
            softAssert.assertTrue(uniqueIds.add(b.getId()),
                    "Duplicate brandID found: " + b.getBrand() + " (ID: " + b.getId() + ")");
        }
        softAssert.assertAll();
    }

    @Test
    public void verifyNoDuplicateBrandNames() {
        ResponseBrands responseBrands = getBrandsResponse();
        SoftAssert softAssert = new SoftAssert();

        Set<String> uniqueBrands = new HashSet<>();
        for (Brand brand : responseBrands.getBrands()) {
            softAssert.assertTrue(uniqueBrands.add(brand.getBrand()),
                    "Duplicate brand found: " + brand.getBrand() + " (ID: " + brand.getId() + ")");
        }
        softAssert.assertAll();
    }

    @Test
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

    @Test (testName = "API 4: PUT To All Brands List")
    public void putToAllBrandsList() {
        Response brandsListResponse = given().log().all()
                .when().put(brandsApiPath)
                .then()
                .extract().response();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Utils.getValueFromJson(brandsListResponse, "responseCode"), "405");
        softAssert.assertEquals(Utils.getValueFromJson(brandsListResponse, "message"),
                "This request method is not supported.");

        softAssert.assertAll();
    }
}