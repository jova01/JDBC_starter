package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import  static io.restassured.RestAssured.*;



public class Spartan_Test {




    public static Response result;


    @BeforeClass
    public static void setUp(){


        RestAssured.baseURI="http://52.207.209.148";
        RestAssured.port=8000;
        RestAssured.basePath="/api/spartans/";

        result=get("");
    }


    @Test
    public void Spartan_All_Test(){


        assertEquals(200,result.statusCode());
        assertEquals("application/json;charset=UTF-8",result.contentType());
        assertEquals("application/json;charset=UTF-8",result.getContentType());
        assertEquals("application/json;charset=UTF-8",result.getHeader("content-type"));


        assertTrue(result.getHeaders().hasHeaderWithName("Date"));


    }

    @Test
    public void SingleSpartan_test(){
        //result=get("2");

        result=given().pathParam("id",2).get("{id}");

        System.out.println(result.asString());
        System.out.println(result.body().asString());


        result.prettyPrint();
        assertEquals(200,result.statusCode());
        assertEquals("application/json;charset=UTF-8",result.contentType());
        assertTrue(result.asString().contains("Fakha"));




    }

    @Test
    public void NoHeader(){
        //1st way
        //result=get("2000");

        //result=given().pathParam("id",2000).when().get("{id}");

        result=given().pathParam("my_id",2000).get("{my_id}");




        result.prettyPrint();

        assertEquals(404,result.statusCode());
        assertEquals("application/json;charset=UTF-8",result.getContentType());
        assertTrue(result.getContentType().contains("json"));

        assertTrue(result.asString().contains("Spartan Not Found"));



    }




    @Test
    public void ProvideHeader(){

       // result=given().header("accept","application/json").get("2");

       // result=given().accept("application/json").get("2");

        result=given().accept(ContentType.JSON).get("2");

        assertEquals("application/json;charset=UTF-8",result.getContentType());

    }


    @Test
    public void Status_code_406() {

        result=given().accept(ContentType.XML).get("2");

        assertEquals(406,result.statusCode());

    }


    @Test
    public void Search_By_Providing_Query_Parameter(){

       // result=given().accept(ContentType.JSON).queryParam("gender","male").get("search");
        result=given().accept(ContentType.JSON).param("gender","male").get("search");

        result.prettyPrint();

        assertEquals(200,result.statusCode());

        assertFalse(result.asString().contains("Female"));

        System.out.println(result.path("pageable.sort.sorted").toString());

    }

    @Test
    public void Single_Json_field() {


        result = given().pathParam("id", 50).get("{id}");

        result.prettyPrint();


        System.out.println(result.path("name").toString());
        System.out.println(result.path("phone").toString());


    }
}
