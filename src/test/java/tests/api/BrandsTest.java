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

    @Test
    public void putToAllBrandsList(){
        Response brandsListResponse = given().log().all()
                .when().put("api/brandsList")
                .then()
                .extract().response();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Utils.getValueFromJson(brandsListResponse, "responseCode"), "405");
        softAssert.assertEquals(Utils.getValueFromJson(brandsListResponse, "message"),
                "This request method is not supported.");

        softAssert.assertAll();
    }
}