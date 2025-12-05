package tests.api;

import assertions.api.BrandsAssertions;
import base.BaseTestApi;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.api.brands.Brand;
import pageobjects.api.brands.ResponseBrands;
import pageobjects.api.client.BrandsApiClient;

import java.util.Set;


public class BrandsTest extends BaseTestApi {
    BrandsApiClient brandsClient = new BrandsApiClient();

    @Test(testName = "API 3: Get All Brands List", groups = {"API"})
    public void getAllBrandsList() {
        ResponseBrands responseBrands = brandsClient.getAllBrands();

        BrandsAssertions.assertResponseCode(responseBrands.getResponseCode(), 200);
        BrandsAssertions.assertBrandsListNotEmpty(responseBrands);

        SoftAssert softAssert = new SoftAssert();
        BrandsAssertions.verifyDuplicateIds(responseBrands.getBrands(),
                Brand::getId, Brand::getBrand, softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"API"})
    public void verifyNoDuplicateBrandNamesIgnoreCaseStream() {
        ResponseBrands responseBrands = brandsClient.getAllBrands();
        SoftAssert softAssert = new SoftAssert();
        BrandsAssertions.assertNoDuplicateBrandNamesIgnoreCase(responseBrands, softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"API"})
    public void verifyBrandsListExactMatch() {

        Set<String> expectedBrands = Set.of(
                "Polo",
                "H&M",
                "Madame",
                "Mast & Harbour",
                "Babyhug",
                "Allen Solly Junior",
                "Kookie Kids",
                "Biba");

        ResponseBrands responseBrands = brandsClient.getAllBrands();
        SoftAssert softAssert = new SoftAssert();
        BrandsAssertions.assertBrandsExactMatch(responseBrands, expectedBrands, softAssert);
        softAssert.assertAll();
    }

    @Test(testName = "API 4: PUT To All Brands List", groups = {"API"})
    public void putToAllBrandsList() {

        BrandsAssertions.verifyMethodNotSupported(brandsClient.getBrandsApiPath(), "PUT", "405",
                "This request method is not supported.");

    }
}