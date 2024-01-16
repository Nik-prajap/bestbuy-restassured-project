package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestUtils {

    static ValidatableResponse response;
    static String name = TestUtils.getRandomValue() + "PrimeUser";
    static String UpdatedName = TestUtils.getRandomValue() + "UpdatedName";
    static String type = "HardGood";
    static String upc = "0987" + TestUtils.getRandomValue();
    static double price = 5.99;
    static String description = "Creating New product of Alkaline batteries 5.5v";
    static String model = "New Battery Model";
    static int productId;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }

    // create product
    @Test
    public void test001() {

        ProductPojo projectPojo = new ProductPojo();
        projectPojo.setName(name);
        projectPojo.setType(type);
        projectPojo.setUpc(upc);
        projectPojo.setPrice(price);
        projectPojo.setDescription(description);
        projectPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .post("/products");
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        productId = jsonPath.getInt("id");
    }

    // read product
    @Test
    public void test002() {

        Response response =
                given()
                        .when()
                        .get();
        response.then().statusCode(200);

    }

    @Test
    public void test003() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(UpdatedName);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .patch("/products/" + productId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004() {

        given()
                .pathParam("id", productId)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200);
    }
}
