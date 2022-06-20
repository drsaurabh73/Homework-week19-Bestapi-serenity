package com.localhost.bestapiinfosteps;

import com.localhost.constant.EndPoints;
import com.localhost.model.CategoriesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategorySteps {

    @Step
    public ValidatableResponse createCategory(String name, String id) {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post(EndPoints.CREATE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Getting the product information with name: {0}")
    public HashMap<String, Object> getCategoryInfoByFirstname(String categoryID) {

        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("categoryID", categoryID)
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;
    }

    @Step
    public ValidatableResponse updateCategory(String categoryID, String name, String id) {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("categoryID", categoryID)
                .body(categoriesPojo)
                .when()
                .put(EndPoints.UPDATE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Deleting Category information with storeId: {0}")
    public ValidatableResponse deleteCategory(String categoryID) {
        return SerenityRest.given().log().all()
                .pathParam("categoryID", categoryID)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Getting Category information with studentId: {0}")
    public ValidatableResponse getProductById(String categoryID) {
        return SerenityRest.given().log().all()
                .pathParam("categoryID", categoryID)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Getting All Category information")
    public ValidatableResponse getAllCategory() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then();
    }
}