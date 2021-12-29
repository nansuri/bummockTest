package Core;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Riza Nansuri
 * @version $Id: CoreFunction.java, v 0.1 2021‐12‐28 13.03 Riza Nansuri Exp $$
 */
public class CoreFunction {

    public void initConfig(){
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            RestAssured.baseURI = prop.getProperty("gateway_url");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
