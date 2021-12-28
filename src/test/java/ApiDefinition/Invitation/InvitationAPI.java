package ApiDefinition.Invitation;

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

    private String INVITATION_SUBMIT_WISHES = "/invitation/wishes/submit";
    private String INVITATION_QUERY_WISHES = "/invitation/wishes/query";

    Logger logger = Logger.getLogger(InvitationAPI.class.getName());


    /**
     * /invitation/wishes/submit API
     * @return
     */
    public Response submitWishesAPI(JSONObject request){

        logger.info("Request: \n" + JSON.toJSONString(request, true));

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer SLD6MIilZ9zEN3LOMGaoF4e9pXyiu")
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
    public Response queryWishesAPI(Map<String,String> queryParam){

        logger.info("Request: \n" + JSON.toJSONString(queryParam.toString(), true));

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer SLD6MIilZ9zEN3LOMGaoF4e9pXyiu")
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
