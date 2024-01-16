package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

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

    // 21. Extract the limit
    @Test
    public void test1() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("            The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 22. Extract the total
    @Test
    public void test2() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("            The total product are : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 23. Extract the name of 5th product
    @Test
    public void test3() {
        String productName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("    Name of the 5th product is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 24.  Extract the names of all the products
    @Test
    public void test4() {
        List<String> allProduct = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("    Names of all the products are : " + allProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    // 25.  Extract the productId of all the products
    @Test
    public void test5() {
        List<Objects> productId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("    Product Id of all the products are : " + productId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 26. Print the size of the data list
    @Test
    public void test6() {
        List<HashMap<String, ?>> allProduct = response.extract().path("data.");
        int size = allProduct.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("    The Print size of the data list is : " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    // 27.  Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test7() {
        List<HashMap<String, ?>> valueOfProduct = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product where product name = Energizer - MAX Batteries AA (4-Pack) is : " + valueOfProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    // 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test8() {
        List<String> productName = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack) is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 29. Get all the categories of 8th products
    @Test
    public void test9() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th product is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 30. Get categories of the store where product id = 150115
    @Test
    public void test10() {
        List<String> categories = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store where product id = 15011 are : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 31. Get all the descriptions of all the products
    @Test
    public void test11() {
        List<String> allDescription = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products are : " + allDescription);
        System.out.println("------------------End of Test---------------------------");
    }

    // 32. Get id of all the all categories of all the products
    @Test
    public void test12() {
        List<String> allCategories = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products are : " + allCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 33. Find the product names Where type = HardGood
    @Test
    public void test13() {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products are : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test14() {
        List<Objects> numberOfCategories = response.extract().path("data.findAll{it.name =='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        int total = numberOfCategories.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) are : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test15() {
        List<String> productName = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 are : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test16() {
        List<String> productName = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)” are : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 37. Find the manufacturer of all the products
    @Test
    public void test17() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products are : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    // 38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test18() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer are = Energizer are : " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // 39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test19() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 are : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 40. Find the url of all the products
    @Test
    public void test20() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The url of all the product are : " + url);
        System.out.println("------------------End of Test---------------------------");
    }
}
