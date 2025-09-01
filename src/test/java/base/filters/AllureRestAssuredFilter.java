package base.filters;

import io.qameta.allure.Attachment;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureRestAssuredFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        // Attach request and response details to Allure
        saveRequest(requestSpec.getMethod() + " " + requestSpec.getURI(),
                requestSpec.getHeaders().toString(),
                requestSpec.getBody() == null ? "No body" : requestSpec.getBody().toString());

        saveResponse(response.getStatusLine(),
                response.getHeaders().toString(),
                response.asPrettyString());

        return response;
    }

    @Attachment(value = "API Request", type = "text/plain")
    private String saveRequest(String methodUri, String headers, String body) {
        return "Method & URI:\n" + methodUri + "\n\nHeaders:\n" + headers + "\n\nBody:\n" + body;
    }

    @Attachment(value = "API Response", type = "application/json")
    private String saveResponse(String status, String headers, String body) {
        return "Status:\n" + status + "\n\nHeaders:\n" + headers + "\n\nBody:\n" + body;
    }
}
