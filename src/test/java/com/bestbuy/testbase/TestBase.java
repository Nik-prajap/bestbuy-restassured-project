package com.bestbuy.testbase;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";


        response = given()
                .when()
                .get("/stores")
                .then();
    }
}
