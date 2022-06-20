package com.localhost.cucumber.steps;

import com.localhost.bestapiinfosteps.ProductSteps;
import com.localhost.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepProduct {

    static ValidatableResponse response;
    static String name = "Product" + TestUtils.getRandomValue();
    static String type = "Product type" + TestUtils.getRandomValue();
    static int price =  100;
    static int shipping =  105;
    static String upc = " upc " + TestUtils.getRandomValue();
    static String description = "Product description" + TestUtils.getRandomValue();
    static String manufacturer = "Product manufacturer" + TestUtils.getRandomValue();
    static String model = "Product model" + TestUtils.getRandomValue();
    static String url = "Product url" + TestUtils.getRandomValue();
    static String image = "Product image" + TestUtils.getRandomValue();
    static int productID;
@Steps
    ProductSteps productSteps;
    @Given("^I am on Homepage of application products$")
    public void iAmOnHomepageOfApplicationProducts() {
    }

    @When("^User send a GET Request to list endpoint products$")
    public void userSendAGETRequestToListEndpointProducts() {
        response = productSteps.getAllProducts();
    }

    @Then("^User can get back a valid status code (\\d+) of product$")
    public void userMustGetBackAValidStatusCodeOfProduct(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }

    @When("^User can create new product using POST method in products application$")
    public void userCanCreateNewProductUsingPOSTMethodInProductsApplication() {
        response = productSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);
    }

    @When("^User can create new product using PUT method in products application$")
    public void userCanCreateNewProductUsingPUTMethodInProductsApplication() {
        response = productSteps.updateProduct(productID,name,type,price,shipping,upc,description,manufacturer,model,url,image);
        HashMap<String, Object> studentMap = productSteps.getStudentInfoByFirstname(productID);
        Assert.assertThat(studentMap, hasValue(name));
    }


    @When("^User can Delete new product using DELETE method in products application$")
    public void userCanCreateNewProductUsingDELETEMethodInProductsApplication() {
      response =  productSteps.deleteProduct(productID);
      response.assertThat().statusCode(200);
    }

    @And("^User verify that the product is deleted successfully from product$")
    public void userVerifyThatTheProductIsDeletedSuccessfullyFromProduct() {
        response = productSteps.getProductById(productID);
        response.assertThat().statusCode(404);
    }


}
