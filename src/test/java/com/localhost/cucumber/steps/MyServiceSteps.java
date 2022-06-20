package com.localhost.cucumber.steps;

import com.localhost.bestapiinfosteps.ServiceSteps;
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

public class MyServiceSteps {
    static String name = "StoreServices" + TestUtils.getRandomValue();
    static int serviceID;
    static ValidatableResponse response;
    @Steps
    ServiceSteps serviceSteps;

    @Given("^I am on Homepage of application services$")
    public void iAmOnHomepageOfApplicationServices() {
    }

    @Then("^User can get back a valid status code (\\d+) of services$")
    public void userCanGetBackAValidStatusCodeOfServices(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }
    @When("^User send a GET Request to list endpoint services$")
    public void userSendAGETRequestToListEndpointServices() {
        response = serviceSteps.getAllServices();
    }

    @When("^User can create new services using POST method in services application$")
    public void userCanCreateNewServicesUsingPOSTMethodInServicesApplication() {
        response = serviceSteps.createServices(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }

    @When("^User can create new services using PUT method in services application$")
    public void userCanCreateNewServicesUsingPUTMethodInServicesApplication() {
        HashMap<Object, Object> services = new HashMap<>();
        services.put("Marks", "8");
        services.put("Gentleman", "10");
        name = name  + "_updated";
        response = serviceSteps.updateServices(serviceID,name);
        HashMap<String, Object> studentMap = serviceSteps.getServiceInfoByFirstname(serviceID);
        Assert.assertThat(studentMap, hasValue(name));
    }

    @When("^User can Delete new services using DELETE method in services application$")
    public void userCanDeleteNewServicesUsingDELETEMethodInServicesApplication() {
        response =  serviceSteps.deleteServices(serviceID);
        response.assertThat().statusCode(200);
    }

    @And("^User verify that the service is deleted successfully from services$")
    public void userVerifyThatTheServiceIsDeletedSuccessfullyFromServices() {
        response =  serviceSteps.getServicestById(serviceID);
        response.assertThat().statusCode(404);
    }
}
