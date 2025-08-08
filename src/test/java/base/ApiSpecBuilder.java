package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Utils;

public class ApiSpecBuilder {

    public RequestSpecification baseReq = new RequestSpecBuilder()
            .setBaseUri(Utils.getGlobalValue("baseURI"))
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build();

    public ResponseSpecification baseResp = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

}