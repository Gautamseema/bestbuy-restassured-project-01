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
//        response.log().all();
    }

    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2) Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3)Extract the name of 5th store
    @Test
    public void test003() {
        String store = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of store is : " + store);
        System.out.println("------------------End of Test---------------------------");
    }

    //4)Extract the names of all the store
    @Test
    public void test004() {
        List<String> allStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of store is : " + allStore);
        System.out.println("------------------End of Test---------------------------");

    }
    //5)Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer > allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of store is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }
    //6)Print the size of the data list
    @Test
    public void test006() {
        List<String> sizeOfData = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of store is : " + sizeOfData);
        System.out.println("------------------End of Test---------------------------");
    }
//    7)Get all the value of the store where store name = St Cloud
@Test
public void test007() {
    List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'St Cloud' are: " + values);
    System.out.println("------------------End of Test---------------------------");
}
// 8)Get the address of the store where store name = Rochester
@Test
public void test008() {
    List<String> values = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'St Cloud' are: " + values);
    System.out.println("------------------End of Test---------------------------");
}
// 9) Get all the services of 8th store
@Test
public void test009() {
    List<String> services = response.extract().path("data[7].services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'St Cloud' are: " + services);
    System.out.println("------------------End of Test---------------------------");
}

//10) Get storeservices of the store where service name = Windows Store
@Test
public void test010() {
    List<HashMap<String, ?>> storeservices = response.extract().path("data.service.findAll{it.name == 'Windows Store'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'St Cloud' are: " + storeservices);
    System.out.println("------------------End of Test---------------------------");
}
//11)Get all the storeId of all the store
@Test
public void test011() {
    List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of store is : " + allStoreId);
    System.out.println("------------------End of Test---------------------------");
}

//12)Get id of all the store
@Test
public void test012() {
    List<Integer> allStoreId = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of store is : " + allStoreId);
    System.out.println("------------------End of Test---------------------------");
}
//13. Find the store names Where state = ND
@Test
public void test013() {
    String storeNames = response.extract().path("data[7].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of limit is : " + storeNames);
    System.out.println("------------------End of Test---------------------------");
}

//14. Find the Total number of services for the store where store name = Rochester
@Test
public void test014() {
    List<Integer> storeName = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value is : " + storeName);
    System.out.println("------------------End of Test---------------------------");
}
//15. Find the createdAt for all services whose name = “Windows Store”
@Test
public void test015() {

        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Created At : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

//16. Find the name of all services Where store name = “Fargo”
@Test
public void test16() {
    List<HashMap<String, ?>> totalService = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The total Number of Services : " + totalService);
    System.out.println("------------------End of Test---------------------------");
}
//17. Find the zip of all the store
@Test
public void test17() {
    List<HashMap<String, ?>> zipCode = response.extract().path("data.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Zip Number of All Store : " + zipCode);
    System.out.println("------------------End of Test---------------------------");
}
//18. Find the zip of store name = Roseville
@Test
public void test18() {
    List<HashMap<String, ?>> zipStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Zip Number of All Store : " + zipStore);
    System.out.println("------------------End of Test---------------------------");
}
//19. Find the storeservices details of the service name = Magnolia Home Theater
@Test
public void test19() {
    List<HashMap<String, ?>> storeService = response.extract().path("data[2].services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Store Service of Mongolia Home Theater : " + storeService);
    System.out.println("------------------End of Test---------------------------");
}
//20. Find the lat of all the stores
@Test
public void test20() {
    List<HashMap<String , ?>> latStore = response.extract().path("data.lat");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The lat of All Store : " + latStore);
    System.out.println("------------------End of Test---------------------------");
}




}