/**
 * Dana.id.
 * Copyright (c) 2018‐2021 All Rights Reserved.
 */
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
    public void responseCodeAssertion(Response response){
        Assertions.assertEquals(200, response.statusCode());
    }
}
