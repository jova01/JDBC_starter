package RestPractice;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import utils.ConfigurationReader;

public class TestBase {

    @BeforeClass
    public static void setUp(){



        RestAssured.baseURI= ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port=Integer.valueOf(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath=ConfigurationReader.getProperty("spartan.base_path");


    }
}
