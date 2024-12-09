package controllers;

import static config.BrowserConfig.BASE_URL;
import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

public class Specification {

    public static RequestSpecification getRequestSpecification() {
        return given()
            .header("Content-Type", "application/json")
            .baseUri(BASE_URL)
            .when();
    }
}