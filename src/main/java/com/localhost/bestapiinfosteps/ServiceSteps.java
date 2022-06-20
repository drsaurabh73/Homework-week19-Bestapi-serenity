package com.localhost.bestapiinfosteps;

import com.localhost.constant.EndPoints;
import com.localhost.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServiceSteps {

    @Step
    public ValidatableResponse createServices(String name) {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post(EndPoints.CREATE_SERVICES_BY_ID)
                .then();
    }

    @Step("Getting the Services information with name: {0}")
    public HashMap<String, Object> getServiceInfoByFirstname(int serviceID) {

        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("serviceID", serviceID)
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;
    }

    @Step
    public ValidatableResponse updateServices(int serviceID, String name) {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("serviceID", serviceID)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();
    }

    @Step("Deleting service information with storeId: {0}")
    public ValidatableResponse deleteServices(int serviceID) {
        return SerenityRest.given().log().all()
                .pathParam("serviceID", serviceID)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();
    }

    @Step("Getting service information with studentId: {0}")
    public ValidatableResponse getServicestById(int serviceID) {
        return SerenityRest.given().log().all()
                .pathParam("serviceID", serviceID)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then();
    }

    @Step("Getting All services")
    public ValidatableResponse getAllServices() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then();
    }
}