package pageobjects.api.client;

import pageobjects.api.brands.ResponseBrands;

public class BrandsApiClient extends BaseApiClient{
    private final String brandsApiPath = "brandsList";

    public String getBrandsApiPath() {
        return brandsApiPath;
    }

    public ResponseBrands getAllBrands() {
        return get(brandsApiPath).as(ResponseBrands.class);
    }

}