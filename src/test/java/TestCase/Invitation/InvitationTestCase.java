package TestCase.Invitation;

import Core.CoreFunction;
import TestHelper.CommonHelper;
import TestLogic.InvitationTestLogic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * @author Riza Nansuri
 * @version $Id: InvitationTestCase.java, v 0.1 2021‐12‐27 11.54 Riza Nansuri Exp $$
 */
public class InvitationTestCase {

    public static CoreFunction coreFunction = new CoreFunction();

    InvitationTestLogic invitationTestLogic = new InvitationTestLogic();
    CommonHelper commonHelper = new CommonHelper();

    @BeforeAll
    public static void setup() {
        coreFunction.initConfig();
    }

    @DisplayName("Verify submit wishes with parameterized message")
    @ParameterizedTest
    @ValueSource(strings = { "test value normal", "", "@!#&*(@!"})
    public void submitWishesTestCase(String candidate){

        // 1. Do Submit new wishes
        Response response = invitationTestLogic.submitWishesTest("TEST0001", candidate);

        // Assert
        commonHelper.responseCodeAssertion(response);
        Assertions.assertEquals("true", response.jsonPath().getString("success"));
    }

    @DisplayName("Verify submit wishes then query the result")
    @Test
    public void submitQueryTestCase(){

        String invitationId = "TEST_FRAMEWORK";

        // 1. Do Submit new wishes
        Response submitResponse = invitationTestLogic.submitWishesTest(invitationId,"THIS IS A TEST");
        commonHelper.responseCodeAssertion(submitResponse);
        Assertions.assertEquals("true", submitResponse.jsonPath().getString("success"));

        // 1A. Get the messageId value
        String messageId = commonHelper.responseValuePicker(submitResponse,"messageId");

        // 2. Do Query the wishes
        Response queryResponse = invitationTestLogic.queryWishesTest(messageId);

        // Assert
        commonHelper.responseCodeAssertion(queryResponse);
        Assertions.assertEquals("true", queryResponse.jsonPath().getString("success"));
        Assertions.assertEquals(messageId, queryResponse.jsonPath().getString("messages[0].messageId"));
        Assertions.assertEquals("THIS IS A TEST", queryResponse.jsonPath().getString("messages[0].message"));
    }
}
