package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    @Test
    public void verifyTheTotalOfProducts() {
        response.body("total", equalTo(51965));
    }

    @Test
    public void verifyTheStoresOfLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
    public void checkTheSingleNameInArrayList() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    @Test
    public void checkMultipleNamesInTheArrayList() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Test
    public void verifyTheProductId() {
        response.body("data[3].categories[2].name", equalTo("Household Batteries"));
    }

    @Test
    public void verifyTheCategoriesSecondName() {
        response.body("data[8].categories[1].name", equalTo("Housewares"));
    }

    @Test
    public void verifyThePriceOfFourthProduct() {

        response.body("data[3].price", Matchers.equalTo(4.99f));
    }

    @Test
    public void verifyTheProductNameOf6thProduct() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));

    }

    @Test
    public void verifyTheProductIdFor9thProduct() {
        response.body("data[8].id", equalTo(333179));

    }

    @Test
    public void verifyTheProductId346575Have5Categories() {
        response.body("data[9].categories", hasSize(5));
    }
}
