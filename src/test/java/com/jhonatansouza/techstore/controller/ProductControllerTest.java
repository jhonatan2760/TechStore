package com.jhonatansouza.techstore.controller;

import com.jhonatansouza.techstore.BaseTest;
import org.junit.Test;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;


public class ProductControllerTest extends BaseTest {

    @Test
    public void insertProduct() throws IOException {
        this.postSample("fixtures/product-request.json", 201);
    }

    @Test
    public void shouldnottInserProduct() throws IOException {
        this.postSample("fixtures/invalid-product-request.json", 400);
    }

    @Test
    public void shouldNotInsertMoreThan100Products() throws IOException {
        this.postSample("fixtures/product-request-invalid-quantity.json", 400);
    }

    @Test
    public void shouldUpdateProduct() throws IOException {

        String id = given().log().everything()
                .body(resource("fixtures/product-request.json"))
                .headers("content-type","application/json")
                .when()
                .post(address().concat("products/"))
                .then()
                .log()
                .everything()
                .extract().header("location").split("/")[3];


        System.out.println(id);

        this.updateSample("fixtures/product-update-request.json", 200, id);
    }

    private void postSample(String jsonPath, int expectedStatus) throws IOException {
        given().log().everything()
                .body(resource(jsonPath))
                .headers("content-type","application/json")
                .when()
                .post(address().concat("products/"))
                .then()
                .log()
                .everything().assertThat().statusCode(expectedStatus);
    }

    private void updateSample(String jsonPath, int expectdStatus, String id) throws IOException {
        given().log().everything()
                .body(resource(jsonPath))
                .headers("content-type","application/json")
                .when()
                .put(address().concat("products/").concat(id))
                .then()
                .log()
                .everything().assertThat().statusCode(expectdStatus);
    }


}
