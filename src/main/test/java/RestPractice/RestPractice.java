package RestPractice;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestPractice  {

    public static Response result;




    @BeforeClass
    public static void setUp(){


      RestAssured.baseURI="http://52.207.209.148";
      RestAssured.port=8000;
      RestAssured.basePath="/api";

      result = RestAssured.get("/hello");
    }

    @Test
    public void test(){


        System.out.println(result.statusCode());  // status code

        System.out.println(result.asString());  // string format
        System.out.println(result.getBody().asString());
        System.out.println(result.body().asString());


        System.out.println(result.getHeader("content-type"));
    }

    @Test
    public void Hello_EndPoint_Test(){
        assertEquals(200, result.statusCode());
        assertEquals("Hello from Sparta", result.asString());
        assertEquals("text/plain;charset=UTF-8",result.header("content-type"));

    }

    /*
Given no headers are provided
When User send request to /api/hello
Then Reponse status code should be 200
and  header should have content Type / text/plain
and header should contains Date
and body should be "Hello from Sparta"
and Content-Length should be 17
     */

    @Test
    public void headerContain(){


        System.out.println(result.getHeader("DaTe"));
        assertNotNull(result.header("daTE"));

        System.out.println(result.getHeaders());
        result.getHeaders().exist();


        assertTrue(result.getHeaders().hasHeaderWithName("date"));
        assertEquals(result.getHeader("content-length"),"17");
    }



}
