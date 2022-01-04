package ApiDefinition.User;

import Core.CoreContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

/**
 * @author Riza Nansuri
 * @version $Id: InvitationAPI.java, v 0.1 2021‐12‐27 11.57 Riza Nansuri Exp $$
 */
public class UserAPI {

    private String USER_LOGIN = "/user/login";

    Logger logger = Logger.getLogger(UserAPI.class.getName());


    /**
     * /invitation/wishes/submit API
     * @return
     */
    public Response loginAPI(CoreContext coreContext, JSONObject request){

        logger.info("Request: \n" + JSON.toJSONString(request, true));

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(request.toJSONString())
                .when()
                .post(USER_LOGIN)
                .then()
                .extract().response();

        logger.info("Response: \n" + response.body().prettyPrint());

        return response;
    }

}
