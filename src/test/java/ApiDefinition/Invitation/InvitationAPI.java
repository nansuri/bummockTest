package ApiDefinition.Invitation;

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
public class InvitationAPI {

    private String INVITATION_SUBMIT_WISHES = "/invitation/submit";
    private String INVITATION_QUERY_WISHES = "/invitation/wishes/query";

    Logger logger = Logger.getLogger(InvitationAPI.class.getName());


    /**
     * /invitation/wishes/submit API
     * @return
     */
    public Response submitWishesAPI(CoreContext coreContext, JSONObject request){

        logger.info("Request: \n" + JSON.toJSONString(request, true));
        String accessToken = (String) coreContext.getAttribute("accessToken");

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .and()
                .body(request.toJSONString())
                .when()
                .post(INVITATION_SUBMIT_WISHES)
                .then()
                .extract().response();

        logger.info("Response: \n" + response.body().prettyPrint());

        return response;
    }

    /**
     * /invitation/wishes/query API
     * @return
     */
    public Response queryWishesAPI(CoreContext coreContext, Map<String,String> queryParam){

        logger.info("Request: \n" + JSON.toJSONString(queryParam.toString(), true));
        String accessToken = (String) coreContext.getAttribute("accessToken");

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .and()
                .queryParams(queryParam)
                .when()
                .get(INVITATION_QUERY_WISHES)
                .then()
                .extract().response();

        logger.info("Response: \n" + response.body().prettyPrint());

        return response;
    }

}
