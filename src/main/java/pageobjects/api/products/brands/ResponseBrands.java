package pageobjects.api.products.brands;

import java.util.List;

public class ResponseBrands {
    private int responseCode;
    private List<Brand> brands;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
