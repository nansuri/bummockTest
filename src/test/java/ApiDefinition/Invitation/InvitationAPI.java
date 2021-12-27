package ApiDefinition.Invitation;

import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author Riza Nansuri
 * @version $Id: InvitationAPI.java, v 0.1 2021‐12‐27 11.57 Riza Nansuri Exp $$
 */
public class InvitationAPI {

    private String INVITATION_SUBMIT_WISHES = "/invitation/wishes/submit";
    private String INVITATION_QUERY_WISHES = "/invitation/wishes/query";

    /**
     * /invitation/wishes/submit API
     * @return
     */
    public Response submitWishesAPI(JSONObject request){

        System.out.println("Request: \n" + request);

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer SLD6MIilZ9zEN3LOMGaoF4e9pXyiu")
                .and()
                .body(request.toJSONString())
                .when()
                .post(INVITATION_SUBMIT_WISHES)
                .then()
                .extract().response();

        System.out.println("Response: \n" + response.body().prettyPrint());

        return response;
    }

    /**
     * /invitation/wishes/query API
     * @return
     */
    public Response queryWishesAPI(Map<String,String> queryParam){

        System.out.println("Request: \n" + queryParam.toString());

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer SLD6MIilZ9zEN3LOMGaoF4e9pXyiu")
                .and()
                .queryParams(queryParam)
                .when()
                .get(INVITATION_QUERY_WISHES)
                .then()
                .extract().response();

        System.out.println("Response: \n" + response.body().prettyPrint());

        return response;
    }

}
