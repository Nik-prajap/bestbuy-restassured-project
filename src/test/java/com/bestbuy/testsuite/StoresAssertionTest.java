package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    @Test
    public void verifyTheTotal() {
        response.body("total", equalTo(1558));
    }

    @Test
    public void verifyTheStoreOfLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
    public void checkTheSingleNameInTheArrayList() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @Test
    public void checkTheMultipleNamesInTheArrayList() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Oakdale"));

    }

    @Test
    public void verifyTheStoreIdInsideStoreService() {
        response.body("data[1].services[2].storeservices.storeId", equalTo(7));
    }

    @Test
    public void checkHashMapValuesInCreatedAtInsideStoreServices() {
        response.body("data.name", hasItem("Roseville"))
                .body("data[0].services[0].storeservices", hasKey("createdAt"));
    }

    @Test
    public void verifyTheState() {
        response.body("data[0].state", equalTo("MN"));
    }

    @Test
    public void verifyTheStoreName() {
        response.body("data[5].name", equalTo("Rochester"));
    }

    @Test
    public void verifyTheStoreId() {
        response.body("data[3].id", equalTo(12));
    }

    @Test
    public void verifyTheServiceId() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }
}
