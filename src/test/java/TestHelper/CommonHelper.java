package TestHelper;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

/**
 * @author Riza Nansuri
 * @version $Id: CommonHelper.java, v 0.1 2021‐12‐27 16.33 Riza Nansuri Exp $$
 */
public class CommonHelper {

    /**
     * responseCodeAssertion
     * @param response
     */
    public void responseCodeAssertion(Response response, int responseCode){
        Assertions.assertEquals(responseCode, response.statusCode());
    }

    /**
     * responseValuePicker
     * @param response
     */
    public String responseValuePicker(Response response, String value){
        return response.jsonPath().getString(value);
    }
}
