package com.localhost.bestapiinfo;

import com.localhost.bestapiinfosteps.ServiceSteps;
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
public class ServiceCURDTestWithSteps extends TestBase {

    static String name = "StoreServices" + TestUtils.getRandomValue();
    static int serviceID;

    @Steps
    ServiceSteps serviceSteps;

    @Title("This will create new Services")
    @Test
    public void test001() {
        ValidatableResponse response = serviceSteps.createServices(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }
    @Title("Verify if the Services was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = serviceSteps.getServiceInfoByFirstname(serviceID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(serviceID);
    }
    @Title("Update the Services information and verify the updated information")
    @Test
    public void test003(){
        HashMap<Object, Object> services = new HashMap<>();
        services.put("Marks", "8");
        services.put("Gentleman", "10");
        name = name  + "_updated";
        ValidatableResponse response = serviceSteps.updateServices(serviceID,name);
        HashMap<String, Object> studentMap = serviceSteps.getServiceInfoByFirstname(serviceID);
        Assert.assertThat(studentMap, hasValue(name));
    }

    @Title("Delete the Service and verify if the store is deleted!")
    @Test
    public void test004() {
        serviceSteps.deleteServices(serviceID).statusCode(200);
        serviceSteps.getServicestById(serviceID).statusCode(404);
    }
}
