package Core;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Riza Nansuri
 * @version $Id: CoreFunction.java, v 0.1 2021‐12‐28 13.03 Riza Nansuri Exp $$
 */
public class CoreFunction {

    public void initConfig(CoreContext coreContext){
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {

            Properties props = new Properties();

            // load a properties file
            props.load(input);
            RestAssured.baseURI = props.getProperty("gateway_url");
            coreContext.setAttribute("email", props.getProperty("email"));
            coreContext.setAttribute("password", props.getProperty("password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
