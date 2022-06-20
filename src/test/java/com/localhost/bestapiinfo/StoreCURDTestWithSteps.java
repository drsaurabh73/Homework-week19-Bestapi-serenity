package com.localhost.bestapiinfo;

import com.localhost.bestapiinfosteps.StoreSteps;
import com.localhost.testbase.TestBase;
import com.localhost.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCURDTestWithSteps extends TestBase {

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


    @Title("This will create a new store")
    @Test
    public void test001() {
        HashMap<Object, Object> services = new HashMap<>();
        services.put("Marks", "8");
        services.put("Gentleman", "10");
        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }
        @Title("Verify if the store was added to the application")
        @Test
        public void test002 () {
            HashMap<String, Object> storeMap = storeSteps.getStoreInfoByFirstname(storeID);
            Assert.assertThat(storeMap, hasValue(name));
            System.out.println(storeID);
        }
    @Title("Update the Proudct information and verify the updated information")
    @Test
    public void test003(){
        HashMap<Object, Object> services = new HashMap<>();
        services.put("Marks", "8");
        services.put("Gentleman", "10");
        name = name  + "_updated";
        ValidatableResponse response = storeSteps.updateStore(storeID,name, type, address, address2, city, state, zip, lat, lng, hours, services);
        HashMap<String, Object> studentMap = storeSteps.getStoreInfoByFirstname(storeID);
        Assert.assertThat(studentMap, hasValue(name));
    }
    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        storeSteps.deleteProduct(storeID).statusCode(200);
        storeSteps.getProductById(storeID).statusCode(404);
    }
    @Title("Get all Stores inforamtion")
    @Test
    public void test005() {
        storeSteps.getAllstores();
    }
    }
