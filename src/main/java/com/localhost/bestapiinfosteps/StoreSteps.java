package com.localhost.bestapiinfosteps;

import com.localhost.constant.EndPoints;
import com.localhost.model.StoresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps  {

    @Step
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state, String zip, int lat,
                                           int lng, String hours, HashMap<Object, Object> services) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_STORE_BY_ID)
                .then();

    }
    @Step("Getting the product information with name: {0}")
    public HashMap<String, Object> getStoreInfoByFirstname(int storeID) {

        HashMap<String, Object> storeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("storeID", storeID)
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return storeMap;
    }
    @Step
    public ValidatableResponse updateStore(int storeID,String name, String type, String address, String address2, String city, String state, String zip, int lat,
                                           int lng, String hours, HashMap<Object, Object> services) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeID)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }
    @Step("Deleting product information with storeId: {0}")
    public ValidatableResponse deleteProduct(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting product information with studentId: {0}")
    public ValidatableResponse getProductById(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then();
    }
    @Step("Getting all store information")
    public ValidatableResponse getAllstores(){
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then();

    }
}
