package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ConfigReader;

public class ApiSpecBuilder {

    public RequestSpecification baseReq = new RequestSpecBuilder()
            .setBaseUri(ConfigReader.getGlobalValue("baseURI") + "/api/")
            .addHeader("Accept", "application/json")
            .build();

    public ResponseSpecification baseResp = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

}