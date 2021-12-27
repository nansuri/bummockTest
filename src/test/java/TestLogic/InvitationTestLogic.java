/**
 * Dana.id.
 * Copyright (c) 2018‐2021 All Rights Reserved.
 */
package TestLogic;

import ApiDefinition.Invitation.InvitationAPI;
import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riza Nansuri
 * @version $Id: InvitationTestLogic.java, v 0.1 2021‐12‐27 15.53 Riza Nansuri Exp $$
 */
public class InvitationTestLogic {

    InvitationAPI invitationAPI = new InvitationAPI();

    /**
     * submitWishesTest
     * @param message
     * @return
     */

    public Response submitWishesTest(String invitationId, String message){
        JSONObject request = new JSONObject();
        request.put("invitationId", invitationId);
        request.put("fullName", "TestMe");
        request.put("phoneNumber", "08777222772");
        request.put("message", message);
        request.put("attend", "true");

        return invitationAPI.submitWishesAPI(request);
    }

    /**
     * queryWishesTest
     * @param invitationId
     * @return
     */

    public Response queryWishesTest(String invitationId){
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("invitationid",invitationId);
        queryParam.put("size","10");

        return invitationAPI.queryWishesAPI(queryParam);
    }
}
