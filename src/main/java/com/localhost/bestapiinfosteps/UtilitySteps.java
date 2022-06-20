package com.localhost.bestapiinfosteps;

import com.localhost.constant.EndPoints;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UtilitySteps {

    @Step("Getting Utility information with utilityId: {0}")
    public HashMap<Object, Object> getHealthcheckInfoByname() {

        HashMap<Object, Object> HealthMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_HEALTHCHECK)
                .then()
                .extract()
                .path("");
        return HealthMap;
    }
    @Step("Getting version information with VersionId: {0}")
    public HashMap<Object, Object> getVersionInfoByname() {

        HashMap<Object, Object> VersionMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_VERSIONS)
                .then()
                .extract()
                .path("");
        return VersionMap;
    }
}
