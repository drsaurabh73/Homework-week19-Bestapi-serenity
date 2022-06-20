package com.localhost.cucumber.steps;

import com.localhost.bestapiinfosteps.CategorySteps;
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

public class MyCategorySteps {

    static String name = "Product" + TestUtils.getRandomValue();
    static String id = "10" + TestUtils.getRandomValue();
    static String categoryID;
    static ValidatableResponse response;
    @Steps
    CategorySteps categorySteps;

    @Given("^I am on Homepage of application category$")
    public void iAmOnHomepageOfApplicationCategory() {

    }

    @Then("^User can get back a valid status code (\\d+) of category$")
    public void userCanGetBackAValidStatusCodeOfCategory(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }

    @When("^User send a GET Request to list endpoint category$")
    public void userSendAGETRequestToListEndpointCategory() {
       response = categorySteps.getAllCategory();
    }


    @When("^User can create new category using POST method in category application$")
    public void userCanCreateNewCategoryUsingPOSTMethodInCategoryApplication() {
        response = categorySteps.createCategory(name, id);
        response.log().all().statusCode(201);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);
    }

    @When("^User can create new category using PUT method in category application$")
    public void userCanCreateNewCategoryUsingPUTMethodInCategoryApplication() {
        name = name  + "_updated";
        response = categorySteps.updateCategory(categoryID,name, id);
        response.log().all().statusCode(200);
        HashMap<String, Object> productMap = categorySteps.getCategoryInfoByFirstname(categoryID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @When("^User can Delete new category using DELETE method in category application$")
    public void userCanDeleteNewCategoryUsingDELETEMethodInCategoryApplication() {
        response =  categorySteps.deleteCategory(categoryID);
        response.assertThat().statusCode(200);
    }

    @And("^User verify that the category is deleted successfully from category$")
    public void userVerifyThatTheCategoryIsDeletedSuccessfullyFromCategory() {
        response =  categorySteps.deleteCategory(categoryID);
        response.assertThat().statusCode(404);
    }
}
