package com.localhost.cucumber.steps;

import com.localhost.bestapiinfosteps.StoreSteps;
import com.localhost.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStoreSteps {

    static ValidatableResponse response;
    static String name = "Store" + TestUtils.getRandomValue();
    static String type = "Store type" + TestUtils.getRandomValue();
    static String address = "Store address" + TestUtils.getRandomValue();
    static String address2 = "Store address2" + TestUtils.getRandomValue();
    static String city = "city" + TestUtils.getRandomValue();
    static String state = "state" + TestUtils.getRandomValue();
    static String zip = "post code" + TestUtils.getRandomValue();
    static int lat = 150;
    static int lng = 120;
    static String hours = "00:00" + TestUtils.getRandomValue();
    static int storeID;

    @Steps
    StoreSteps storeSteps;


    @When("^User send a GET Request to list endpoint stores$")
    public void userSendAGETRequestToListEndpointStores() {
        response = storeSteps.getAllstores();

    }

    @Then("^User can get back a valid status code (\\d+) of stores$")
    public void userCanGetBackAValidStatusCodeOfStores(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }


    @When("^User can create new store using POST method in stores application$")
    public void userCanCreateNewStoreUsingPOSTMethodInStoresApplication() {
        HashMap<Object, Object> services = new HashMap<>();
        services.put("Marks", "8");
        services.put("Gentleman", "10");
        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @When("^User can create new store using PUT method in stores application$")
    public void userCanCreateNewStoreUsingPUTMethodInStoresApplication() {
        HashMap<Object, Object> services = new HashMap<>();
        services.put("Marks", "8");
        services.put("Gentleman", "10");
        name = name  + "_updated";
        ValidatableResponse response = storeSteps.updateStore(storeID,name, type, address, address2, city, state, zip, lat, lng, hours, services);
        HashMap<String, Object> studentMap = storeSteps.getStoreInfoByFirstname(storeID);
        Assert.assertThat(studentMap, hasValue(name));
    }

    @When("^User can Delete new product using DELETE method in stores application$")
    public void userCanDeleteNewProductUsingDELETEMethodInStoresApplication() {
        response =  storeSteps.deleteProduct(storeID);
        response.assertThat().statusCode(200);
    }

    @And("^User verify that the product is deleted successfully from stores$")
    public void userVerifyThatTheProductIsDeletedSuccessfullyFromStores() {
        response =  storeSteps.deleteProduct(storeID);
        response.assertThat().statusCode(404);
    }
}
