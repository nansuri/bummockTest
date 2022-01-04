package TestCase.Invitation;

import Core.CoreContext;
import Core.CoreFunction;
import TestHelper.CommonHelper;
import TestLogic.InvitationTestLogic;
import TestLogic.UserTestLogic;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * @author Riza Nansuri
 * @version $Id: InvitationTestCase.java, v 0.1 2021‐12‐27 11.54 Riza Nansuri Exp $$
 */
public class InvitationTestCase {

    public static CoreFunction coreFunction = new CoreFunction();
    static CoreContext coreContext = new CoreContext();

    InvitationTestLogic invitationTestLogic = new InvitationTestLogic();
    CommonHelper commonHelper = new CommonHelper();
    UserTestLogic userTestLogic = new UserTestLogic();

    @BeforeAll
    public static void setup() {
        coreFunction.initConfig(coreContext);
    }

    @DisplayName("Verify submit wishes with parameterized message")
    @ParameterizedTest
    @Tag("Invitation")
    @ValueSource(strings = { "test value normal", "", "@!#&*(@!"})
    public void submitWishesTestCase(String candidate){

        String email = (String) coreContext.getAttribute("email");
        String password = (String) coreContext.getAttribute("password");

        // 0. do Login
        Response loginResponse = userTestLogic.userLoginTest(coreContext, email, password);
        coreContext.setAttribute("accessToken", commonHelper.responseValuePicker(loginResponse,"access_token"));

        // 1. Do Submit new wishes
        Response response = invitationTestLogic.submitWishesTest(coreContext,"TEST0001", candidate);

        // Assert
        commonHelper.responseCodeAssertion(response, 200);
        Assertions.assertEquals("true", response.jsonPath().getString("success"));
        Assertions.assertNotEquals("", response.jsonPath().getString("messageId"));
    }

    @DisplayName("Verify submit attend with parameterized message")
    @ParameterizedTest
    @Tag("Invitation")
    @ValueSource(strings = { "test value normal", "", "@!#&*(@!"})
    public void submitAttendTestCase(String candidate){

        String email = (String) coreContext.getAttribute("email");
        String password = (String) coreContext.getAttribute("password");

        // 0. do Login
        Response loginResponse = userTestLogic.userLoginTest(coreContext, email,password);
        coreContext.setAttribute("accessToken", commonHelper.responseValuePicker(loginResponse,"access_token"));

        // 1. Do Submit new wishes
        Response response = invitationTestLogic.submitAttendTest(coreContext,"TEST0001", candidate);

        // Assert
        commonHelper.responseCodeAssertion(response, 200);
        Assertions.assertEquals("true", response.jsonPath().getString("success"));
        Assertions.assertNotEquals("", response.jsonPath().getString("attendeeId"));
    }

    @DisplayName("Verify submit wishes then query the result")
    @Test
    @Tag("Invitation")
    public void submitQueryTestCase(){

        String invitationId = "TEST_FRAMEWORK";
        String email = (String) coreContext.getAttribute("email");
        String password = (String) coreContext.getAttribute("password");

        // 0. do Login
        Response loginResponse = userTestLogic.userLoginTest(coreContext, email,password);
        coreContext.setAttribute("accessToken", commonHelper.responseValuePicker(loginResponse,"access_token"));

        // 1. Do Submit new wishes
        Response submitResponse = invitationTestLogic.submitWishesTest(coreContext,invitationId,"THIS IS A TEST");
        commonHelper.responseCodeAssertion(submitResponse, 200);
        Assertions.assertEquals("true", submitResponse.jsonPath().getString("success"));

        // 1A. Get the messageId value
        String messageId = commonHelper.responseValuePicker(submitResponse,"messageId");

        // 2. Do Query the wishes
        Response queryResponse = invitationTestLogic.queryWishesTest(coreContext,messageId);

        // Assert
        commonHelper.responseCodeAssertion(queryResponse, 200);
        Assertions.assertEquals("true", queryResponse.jsonPath().getString("success"));
        Assertions.assertEquals(messageId, queryResponse.jsonPath().getString("messages[0].messageId"));
        Assertions.assertEquals("THIS IS A TEST", queryResponse.jsonPath().getString("messages[0].message"));
    }
}
