package com.localhost.bestapiinfo;

import com.localhost.bestapiinfosteps.CategorySteps;
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
public class CategoryCURDTestWithSteps extends TestBase {

    static String name = "Product" + TestUtils.getRandomValue();
    static String id = "10" + TestUtils.getRandomValue();
    static String categoryID;

    @Steps
    CategorySteps categorySteps;

    @Title("This will create new category")
    @Test
    public void test001() {
        ValidatableResponse response = categorySteps.createCategory(name, id);
        response.log().all().statusCode(201);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);

    }
    @Title("Verify if the category was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = categorySteps.getCategoryInfoByFirstname(categoryID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(categoryID);
    }
    @Title("This will update the category")
    @Test
    public void test003() {
        name = name  + "_updated";
        ValidatableResponse response = categorySteps.updateCategory(categoryID,name, id);
        response.log().all().statusCode(200);
        HashMap<String, Object> productMap = categorySteps.getCategoryInfoByFirstname(categoryID);
        Assert.assertThat(productMap, hasValue(name));
    }
    @Title("Delete the category and verify if the student is deleted!")
    @Test
    public void test004() {
        categorySteps.deleteCategory(categoryID).statusCode(200);
        categorySteps.deleteCategory(categoryID).statusCode(404);
    }
    @Title("Getting all the category")
    @Test
    public void test005(){
        categorySteps.getAllCategory();
    }
    }