package TestLogic;

import ApiDefinition.Invitation.InvitationAPI;
import ApiDefinition.User.UserAPI;
import Core.CoreContext;
import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riza Nansuri
 * @version $Id: InvitationTestLogic.java, v 0.1 2021‐12‐27 15.53 Riza Nansuri Exp $$
 */
public class UserTestLogic {

    UserAPI userAPI = new UserAPI();

    /**
     * submitWishesTest
     * @param email, password
     * @return
     */

    public Response userLoginTest(CoreContext coreContext, String email, String password){
        JSONObject request = new JSONObject();
        request.put("email", email);
        request.put("password", password);

        return userAPI.loginAPI(coreContext, request);
    }

}
