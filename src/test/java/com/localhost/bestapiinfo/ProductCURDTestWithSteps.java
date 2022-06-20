package com.localhost.bestapiinfo;

import com.localhost.bestapiinfosteps.ProductSteps;
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
public class ProductCURDTestWithSteps extends TestBase {

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

    @Title("This will create new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);
    }
    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productSteps.getStudentInfoByFirstname(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productID);
    }
    @Title("Update the Proudct information and verify the updated information")
    @Test
    public void test003(){

        name = name  + "_updated";
        ValidatableResponse response = productSteps.updateProduct(productID,name,type,price,shipping,upc,description,manufacturer,model,url,image);
        HashMap<String, Object> studentMap = productSteps.getStudentInfoByFirstname(productID);
        Assert.assertThat(studentMap, hasValue(name));
    }
    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        productSteps.deleteProduct(productID).statusCode(200);
        productSteps.getProductById(productID).statusCode(404);
    }
    @Title("Get the all the  Products ")
    @Test
    public void test005() {
        productSteps.getAllProducts();

    }
    }

