package com.localhost.bestapiinfo;

import com.localhost.bestapiinfosteps.UtilitySteps;
import com.localhost.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UtilityCURDWithSteps extends TestBase {

    static int utilityID;

    @Steps
    UtilitySteps utilitySteps;

    @Title("This will get all Healthcheck")
    @Test
    public void test001() {
        HashMap<Object, Object> HealthMap = utilitySteps.getHealthcheckInfoByname();
        Assert.assertThat(HealthMap, hasKey("uptime"));

    }
    @Title("This will get all Version")
    @Test
    public void test002() {
        HashMap<Object, Object> VersionMap = utilitySteps.getVersionInfoByname();
        Assert.assertThat(VersionMap, hasValue("1.1.0"));

    }

    }



