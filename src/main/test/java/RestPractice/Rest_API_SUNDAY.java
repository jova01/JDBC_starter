package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;





public class Rest_API_SUNDAY {

    public static Response result;


    @BeforeClass
    public static void setUp(){



        RestAssured.baseURI= ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port=Integer.valueOf(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath=ConfigurationReader.getProperty("spartan.base_path");


    }

    @Test
    public void All_Spartan_With_Size_And_Items_Test(){


        given().accept(ContentType.JSON).when().get("/spartans/").
                prettyPeek().then().
                statusCode(200).assertThat().
                body("size",is(121)).
                assertThat().body("",hasSize(121)).
                body("[2].name",equalTo("Tedmund")).
                body("[1].gender",is("Male")).
                header("Transfer-Encoding", "chunked").
                header("Date", notNullValue());

    }

    @Test
    public void Logging_RestAssured(){

        given().pathParam("my_id",3).when().
                log().all().
                when().
                        get("/spartans/{my_id}").
                then().
                        log().all().statusCode(200)
        ;

    }
}
