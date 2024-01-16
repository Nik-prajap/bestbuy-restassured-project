package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest {
    static ValidatableResponse response;
    static int storeId;
    static String name = TestUtils.getRandomValue() + "Prime";
    static String UpdatedName = TestUtils.getRandomValue() + "UpdatedName";
    static String type = "Testing";
    static String address = "101-Shanti-Bhawan,";

    static String address2 = "Matajina Garba Party Plot";
    static String city = "Ahmedabad";

    static String state = "Gujarat";
    static String zip = "380002";


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }

    @Test
    public void test001() {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post("/stores");
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        storeId = jsonPath.getInt("id");
    }

    @Test
    public void test002() {
        System.out.println(storeId);
        response = given()
                .when()
                .get("/stores/" + storeId)
                .then().statusCode(200);
    }

    @Test
    public void test003() {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(UpdatedName);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .patch("/stores/" + storeId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004() {

        given()
                .pathParam("id", storeId)
                .when()
                .delete("/stores/{id}")
                .then()
                .statusCode(200);
    }
}
