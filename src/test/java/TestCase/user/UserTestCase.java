package TestCase.user;

import Core.CoreContext;
import Core.CoreFunction;
import TestHelper.CommonHelper;
import TestLogic.UserTestLogic;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;


/**
 * @author Riza Nansuri
 * @version $Id: InvitationTestCase.java, v 0.1 2021‐12‐27 11.54 Riza Nansuri Exp $$
 */
public class UserTestCase {

    public static CoreFunction coreFunction = new CoreFunction();
    private static CoreContext coreContext = new CoreContext();

    UserTestLogic userTestLogic = new UserTestLogic();
    CommonHelper commonHelper = new CommonHelper();

    @BeforeAll
    public static void setup() {
        coreFunction.initConfig(coreContext);
    }

    @Test
    @Tag("Invitation")
    @DisplayName("Verify login using valid email")
    public void userLoginValidTestCase(){

        String email = (String) coreContext.getAttribute("email");
        String password = (String) coreContext.getAttribute("password");

        // 1. Do Login
        Response response = userTestLogic.userLoginTest(coreContext,email, password);

        System.out.println("Hi "+coreContext.getAttribute("email"));

        // 2. Assert
        commonHelper.responseCodeAssertion(response, 200);
        Assertions.assertEquals("true", response.jsonPath().getString("success"));
        Assertions.assertNotEquals("", response.jsonPath().getString("access_token"));
    }

    @Test
    @Tag("Invitation")
    @DisplayName("Verify login using invalid email")
    public void userLoginInvalidTestCase(){

        // 1. Do Login
        Response response = userTestLogic.userLoginTest(coreContext,"this@wrong.email", "televisi");

        // 2. Assert
        commonHelper.responseCodeAssertion(response, 401);
        Assertions.assertEquals("false", response.jsonPath().getString("success"));
        Assertions.assertEquals("Wrong Email or Password", response.jsonPath().getString("error_details"));
        Assertions.assertEquals("", response.jsonPath().getString("access_token"));
    }
}
