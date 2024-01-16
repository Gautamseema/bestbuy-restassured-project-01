package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

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
//        response.log().all();
    }
// 21. Extract the limit
@Test
public void extractLimit() {
    int limit = response.extract().path("limit");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of limit is : " + limit);
    System.out.println("------------------End of Test---------------------------");
}
//22. Extract the total
@Test
public void extracttotal() {
    int total = response.extract().path("total");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of total is : " + total);
    System.out.println("------------------End of Test---------------------------");
}
//23. Extract the name of 5th product
@Test
public void extractproduct() {
     int name = response.extract().path("data[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of total is : " + name);
    System.out.println("------------------End of Test---------------------------");
}
//24. Extract the names of all the products
@Test
public void extractnamesOfAllproducts() {
    List<String> names = response.extract().path("data.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of total is : " + names);
    System.out.println("------------------End of Test---------------------------");
}
//25. Extract the productId of all the products
@Test
public void extractProductIdOfAllProducts() {
    List<Integer> ids = response.extract().path("data.ids");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of total is : " + ids);
    System.out.println("------------------End of Test---------------------------");
}
//26. Print the size of the data list
@Test
public void sizeOfDataList() {
    int size = response.extract().path("data.size()");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of total is : " + size);
    System.out.println("------------------End of Test---------------------------");
}

//27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
//Pack)
@Test
public void getAllTheValuesOfTheProduct() {
//    response.body("data.name", hasItem("Energizer - MAX Batteries AA (4-pack)"));
    List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-pack)'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-pack)' are: " + value);
    System.out.println("------------------End of Test---------------------------");
}
//28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
//Pack)
@Test
public void getAllTheModelOfTheProduct() {
    List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-pack)'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'Energizer - N Cell E90 Batteries (2-pack))' are: " + value);
    System.out.println("------------------End of Test---------------------------");
}
//29. Get all the categories of 8th products
@Test
public void getAllTheCategories() {
    List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'Energizer - Max Batteries AA (4-pack))' are: " + categories);
    System.out.println("------------------End of Test---------------------------");
}
//30. Get categories of the store where product id = 150115
@Test
public void getOfCategoriesStore() {
    List<HashMap<String, ?>> categories = response.extract().path("data[3].categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The categories of store are: " + categories);
    System.out.println("------------------End of Test---------------------------");
}
//31. Get all the descriptions of all the products
@Test
public void getDescriptionOfProduct() {
    List<String> descriptions = response.extract().path("data.descriptions");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The descriptions of produt are: " + descriptions);
    System.out.println("------------------End of Test---------------------------");
}

//32. Get id of all the all categories of all the products
@Test
public void getAllCategoriesOfProduct() {
    List<String> id = response.extract().path("data.categories.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The descriptions of produt are: " + id );
    System.out.println("------------------End of Test---------------------------");
}
//33. Find the product names Where type = HardGood
@Test
public void productNames() {
    List<String> values = response.extract().path("data.findAll{it.name == 'HardGood'}.type");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for product name 'HardGood' are: " + values);
    System.out.println("------------------End of Test---------------------------");
}
//34. Find the Total number of categories for the product where product name = Duracell - AA
//1.5V CopperTop Batteries (4-Pack)
@Test
public void TotalNumberOfCategories () {
    List<String> values = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The number of categories are: " + values);
    System.out.println("------------------End of Test---------------------------");
}
//35. Find the createdAt for all products whose price < 5.49
@Test
public void createdAt () {
    List<String> values = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The number of categories are: " + values);
    System.out.println("------------------End of Test---------------------------");
}
//36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
//Pack)”
@Test
public void allCategories () {
    List<String> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack'}.categories.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The number of categories are: " + values);
    System.out.println("------------------End of Test---------------------------");
}
//37. Find the manufacturer of all the products
@Test
public void findManufactureOfAllProducts() {
    List<String> manufacturer = response.extract().path("data.manufacturer");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The manufacturer of product are: " + manufacturer );
    System.out.println("------------------End of Test---------------------------");
}
//38. Find the image of products whose manufacturer is = Energizer
@Test
public void findTheImage() {
    String image = response.extract().path("data[8].image");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The image of products whose manufacturer is = Energizer : " + image);
    System.out.println("------------------End of Test---------------------------");
}
//39. Find the createdAt for all categories products whose price > 5.99
public void findCreatedAt () {
    List<String> findCreatedAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The number of categories are: " + findCreatedAt);
    System.out.println("------------------End of Test---------------------------");
}
//40. Find the uri of all the products
@Test
public void findTheUri(){
    List<String> url = response.extract().path("data.url");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The url of all the products are: " + url);
    System.out.println("------------------End of Test---------------------------");

}

}
