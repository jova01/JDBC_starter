package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class HamCrestLibrary {


    public static Response result;


    @BeforeClass
    public static void setUp(){



        RestAssured.baseURI="http://52.207.209.148";
        RestAssured.port=8000;
        RestAssured.basePath="/api/spartans/";

        result=get("");
    }

    @Test
    public void Calculation_test(){
        int a=10, b=20;

        assertEquals(30,a+b);

        assertThat(30, equalTo(a+b));

        assertThat(40, greaterThan(a+b));
    }



    @Test
    public void Assertions(){

        given().pathParam("id",3).when().get("{id}").
                                            then().assertThat().body("id",equalTo(3))
                                            .body("gender",equalTo("Male")).statusCode(equalTo(200));







    }
}
