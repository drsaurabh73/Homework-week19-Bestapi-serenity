package com.localhost.cucumber.steps;

import com.localhost.bestapiinfosteps.UtilitySteps;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

public class MyUtilitySteps {
    static int utilityID;

    @Steps
    UtilitySteps utilitySteps;

    @When("^User send a GET Request to list endpoint of Healthcheck$")
    public void userSendAGETRequestToListEndpointOfHealthcheck() {
        HashMap<Object, Object> HealthMap = utilitySteps.getHealthcheckInfoByname();
        Assert.assertThat(HealthMap, hasKey("uptime"));
    }

    @When("^User send a GET Request to list endpoint of Versions$")
    public void userSendAGETRequestToListEndpointOfVersions() {
        HashMap<Object, Object> VersionMap = utilitySteps.getVersionInfoByname();
        Assert.assertThat(VersionMap, hasValue("1.1.0"));
    }
}
