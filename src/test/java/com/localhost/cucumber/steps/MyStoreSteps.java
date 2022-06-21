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

import static org.hamcrest.Matchers.*;

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
        name = name + "_updated";
        ValidatableResponse response = storeSteps.updateStore(storeID, name, type, address, address2, city, state, zip, lat, lng, hours, services);
        HashMap<String, Object> studentMap = storeSteps.getStoreInfoByFirstname(storeID);
        Assert.assertThat(studentMap, hasValue(name));
    }

    @When("^User can Delete new product using DELETE method in stores application$")
    public void userCanDeleteNewProductUsingDELETEMethodInStoresApplication() {
        response = storeSteps.deleteProduct(storeID);
        response.assertThat().statusCode(200);
    }

    @And("^User verify that the product is deleted successfully from stores$")
    public void userVerifyThatTheProductIsDeletedSuccessfullyFromStores() {
        response = storeSteps.deleteProduct(storeID);
        response.assertThat().statusCode(404);
    }

    @Then("^verify that if the total is equal to (\\d+)$")
    public void verifyThatIfTheTotalIsEqualTo(int expected) {
        response = storeSteps.getAllstores();
        int actual = response.extract().path("total");
        Assert.assertEquals(expected, actual);
    }

    @And("^verify that if the stores of limit is equal to (\\d+)$")
    public void verifyThatIfTheStoresOfLimitIsEqualTo(int expected) {
        response = storeSteps.getAllstores();
        int actual = response.extract().path("limit");
        Assert.assertEquals(expected, actual);
    }

    @And("^Check the multiple ‘Names’ in the ArrayList \\(Roseville, Burnsville, Maplewood\\)$")
    public void checkTheMultipleNamesInTheArrayListRosevilleBurnsvilleMaplewood() {
        response.body("data.name", hasItem("Roseville"))
                .body("data.name", hasItem("Burnsville"))
                .body("data.name", hasItem("Maplewood"));

    }

    @And("^check the single name in the Array list \\(Inver Grove Heights\\)$")
    public void checkTheSingleNameInTheArrayListInverGroveHeights() {
        response.body("data[1].name",equalTo("Inver Grove Heights"));
    }

    @And("^Verify the storied=(\\d+) inside storeservices of the third store of second services$")
    public void verifyTheStoriedInsideStoreservicesOfTheThirdStoreOfSecondServices(int expected) {
        response = storeSteps.getAllstores();
        int actual = response.extract().path("data[2].services[1].storeservices.storeId");
        Assert.assertEquals(expected, actual);
    }

    @And("^Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville$")
    public void checkHashMapValuesCreatedAtInsideStoreservicesMapWhereStoreNameRoseville() {
        response.body("data[2].services.storeservices.createdAt", hasItem("2016-11-17T17:57:09.417Z"));
    }

    @And("^Verify the serviceId = 4 for the 7th store of forth service$")
    public void verifyTheServiceIdForTheThStoreOfForthService(int expected) {
        response = storeSteps.getAllstores();
        int actual = response.extract().path("data[6].services[3].storeservices.serviceId");
        Assert.assertEquals(expected, actual);
    }

    @And("^Verify the state = MN of forth store$")
    public void verifyTheStateMNOfForthStore() {
        response.body("data[3].state",equalTo("MN"));
    }

    @And("^Verify the store name = Rochester of (\\d+)th store$")
    public void verifyTheStoreNameRochesterOfThStore(int arg0) {
        response.body("data[8].name",equalTo("Rochester"));
    }

    @And("^Verify the storeId = 11 for the 6th store$")
    public void verifyTheStoreIdForTheThStore() {
        response.body("data[5].services.storeservices.storeId", hasItems("11"));
    }
}
