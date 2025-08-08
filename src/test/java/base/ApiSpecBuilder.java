package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.Utils;

public class ApiSpecBuilder {

    public RequestSpecification baseReq = new RequestSpecBuilder()
            .setBaseUri(Utils.getGlobalValue("baseURI"))
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build();

}