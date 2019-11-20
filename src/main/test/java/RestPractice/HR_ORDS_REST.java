package RestPractice;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class HR_ORDS_REST {

    public static Response response;


    @BeforeClass
    public static void setUp(){


        baseURI="http://52.207.209.148";
        port=1000;
        basePath="/ords/hr/regions/";

        response=get("");
    }

    @Test
    public void test_regions(){

        //response.prettyPrint();

       // String first_regionName=get("1").path("region_name");

        String first_regionName=response.jsonPath().getString("items[0].region_name");
        System.out.println(first_regionName);


        List<String> all_regionName=response.jsonPath().getList("items.region_name");
        System.out.println(all_regionName);


        String href=response.jsonPath().getString("items[1].links[0].href");

        System.out.println(href);
        System.out.println(get("2").path("links[0].href").toString());

        assertEquals(200,response.statusCode());

    }


    @Test
    public void All_Region(){

        List<String> href=response.jsonPath().getList("items.links.href");
        System.out.println(href);

        String lastLinkRel=response.jsonPath().getString("links[3].rel");
        assertEquals("first",lastLinkRel);



    }

    @Test
    public void single_Region(){

        response=given().pathParam("my_id",1).get("{my_id}");
        response.prettyPrint();

        Map<String, Object> myJsonMap = response.jsonPath().getMap("");

        System.out.println(myJsonMap.get("links"));



    }

    @AfterClass
    public static void tearDown(){

        RestAssured.reset();
    }
}
