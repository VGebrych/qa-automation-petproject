package base.listeners;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;


public class ApiRequestResponseListener implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Execute request
        Response response = ctx.next(requestSpec, responseSpec);

        // Attach request details to Allure
        String reqDetails = requestSpec.getMethod() + " " + requestSpec.getURI() + "\nHeaders: "
                + requestSpec.getHeaders() + "\nBody: " + requestSpec.getBody();
        Allure.addAttachment("API Request", reqDetails);

        // Attach response details to Allure
        String respDetails = "Status: " + response.getStatusCode() + "\nHeaders: "
                + response.getHeaders() + "\nBody: " + response.getBody().asString();
        Allure.addAttachment("API Response", respDetails);

        return response;
    }
}