package tests.api;

import base.BaseTestApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.products.brands.Brand;
import pageobjects.api.products.brands.ResponseBrands;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class BrandsTest extends BaseTestApi {
    @Test
    public void getAllBrandsList(){
        ResponseBrands responseBrands =  given().log().all()
                .when().get("api/brandsList")
                .then()
                .extract()
                .as(ResponseBrands.class);

        Assert.assertEquals(responseBrands.getResponseCode(), 200);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(responseBrands.getBrands().isEmpty(), "Brands list should not be empty");

        Set<Integer> uniqueIds = new HashSet<>();
        for( Brand b : responseBrands.getBrands()) {
            softAssert.assertTrue(uniqueIds.add(b.getId()),
                    "Duplicate brand found: " + b.getBrand() + " (ID: " + b.getId() + ")");
        }
        softAssert.assertAll();
    }
}