package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

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
    public void test1TestExtractLimit() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("             The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void test2TestExtractTotal() {

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("         The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test3TestExtractNameOfFifthStore() {

        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("        The name of 5th Store is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void test4ExtractTheNamesOfAllTheStore() {

        List<String> storeNames = response.extract().path("data.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The names of all the stores are : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test5ExtractTheStoreIdOfAllTheStores() {
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" All the store Id's of all the Stores are : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test6SizeOfTheDataList() {
        List<Integer> data = response.extract().path("data");
        int size = data.size();

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("             The size of data is : " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test7GetAllValueOfTheStoreNames() {
        List<HashMap<String, Object>> StCloudStoreData = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the store where store name = St Cloud is : " + StCloudStoreData);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test8GetAllAddressOfTheStoreNames() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the store where store name = Rochester is : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test9GetAllServicesOfEightsStore() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of 8th store : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test10GetStoreServicesOfTheStoreName() {
        List<HashMap<String, ?>> storeServicesWindowsStore = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of the store where service name = Windows Store is : " + storeServicesWindowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test11GetAllTheStoreIdOfAllTheStore() {
        List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Get all storeId of all the store is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test12GetIdOfAllTheStore() {
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test13FindTheStoreNameWithStateND() {
        List<String> state = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state = ND is : " + state);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test14FindTheTotalNumberOfServiceWithNameRochestor() {
        List<Object> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name = Rochester is : " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test15FindTheCreatedAtForAllService() {
        List<String> allServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name = Windows Store : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test16FindTheNameOfAllService() {
        List<String> servicesName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = “Fargo” is : " + servicesName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test17FindTheTheZipOfAllTheStore() {
        List<Object> zipOfAllStore = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  zip of all the store = zip is : " + zipOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test18FindTheZipOfAllTheStore() {
        List<String> zipOfStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  zip of store name = Roseville is : " + zipOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test19FindTheStoresServicesDetailsOfTheServiceName() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater is : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test20FindTheLatOfAllTheStores() {
        List<Integer> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The latitude of all the stores is : " + lat);
        System.out.println("------------------End of Test---------------------------");

    }
}
