package com.localhost.bestapiinfo;

import com.localhost.constant.EndPoints;
import com.localhost.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class FirstTest extends TestBase {
    @Test
    public void getAllProduct(){
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void getAllstores(){
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then()
                .log().all()
                .statusCode(200);
    }
}
