package TestLogic;

import ApiDefinition.Invitation.InvitationAPI;
import Core.CoreContext;
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

    public Response submitWishesTest(CoreContext coreContext, String invitationId, String message){
        JSONObject request = new JSONObject();
        request.put("invitationId", invitationId);
        request.put("fullName", "TestMe");
        request.put("phoneNumber", "08777222772");
        request.put("message", message);
        request.put("attend", true);

        return invitationAPI.submitWishesAPI(coreContext, request);
    }

    /**
     * submitAttendTest
     * @param message
     * @return
     */

    public Response submitAttendTest(CoreContext coreContext, String invitationId, String message){
        JSONObject request = new JSONObject();
        request.put("invitationId", invitationId);
        request.put("fullName", "TestMe");
        request.put("phoneNumber", "08777222772");
        request.put("attend", true);
        request.put("isAttendee", true);

        return invitationAPI.submitWishesAPI(coreContext, request);
    }

    /**
     * queryWishesTest
     * @param queryWishesTest
     * @return
     */

    public Response queryWishesTest(CoreContext coreContext, String messageId){
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("size","10");
        queryParam.put("messageid",messageId);

        return invitationAPI.queryWishesAPI(coreContext, queryParam);
    }
}
