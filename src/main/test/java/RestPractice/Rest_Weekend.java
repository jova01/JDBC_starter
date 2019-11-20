package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Rest_Weekend {


    public static Response result;


    @BeforeClass
    public static void setUp(){


        RestAssured.baseURI="http://52.207.209.148";
        RestAssured.port=8000;
        RestAssured.basePath="/api/spartans/";

        result=get("");
    }


    @Test
    public void test1(){

        result=given().accept(ContentType.JSON).when().get("2");

        result.prettyPrint();

        System.out.println(result.path("gender").toString());

        assertEquals("Male",result.path("gender").toString());


    }

    @Test
    public void Search_By_Providing_Query_Parameter(){

        // result=given().accept(ContentType.JSON).queryParam("gender","male").get("search");
        result=given().accept(ContentType.JSON).param("gender","male").get("search");

      //  result.prettyPrint();

        assertEquals(200,result.statusCode());

        assertFalse(result.asString().contains("Female"));

        System.out.println(result.path("pageable.sort.empty").toString());


        boolean isEmpty = result.jsonPath().getBoolean("pageable.sort.empty");

        assertTrue("Assertion is not equal", isEmpty);



        int totalElement = result.jsonPath().getInt("totalElements");
       // int totalElement=result.path("totalElements");
        int numberOfElements=result.path("numberOfElements");


        assertEquals(totalElement,20);

        System.out.println(totalElement);
        System.out.println(numberOfElements);


    }

    @Test
    public void Json_path_As_Array(){

         System.out.println(get("2").path("phone").toString());

        result=given().accept(ContentType.JSON).queryParam("gender","Male").when().get("search");

        System.out.println("First guys phone number: "+result.jsonPath().getLong("content[0].phone"));

        List<String> phoneList=result.jsonPath().getList("content.phone");

        System.out.println("Everyones phone number: "+phoneList);
    }


    @Test
    public void getNames(){



        result=given().accept(ContentType.JSON).queryParam("gender","Male").get("search");
        List<String> nameList=result.jsonPath().getList("content.name");

        System.out.println("Everyones name: "+nameList);


        List<Map<String,Object>> name = result.jsonPath().getList("content");

        name.forEach((each)-> System.out.println(each.get("name")));

    }


    @Test
    public void Spartan(){

        result=given().accept(ContentType.JSON).pathParams("id",2).get("{id}");


        Map<String,Object> single_Spartan=result.jsonPath().getMap("");


        single_Spartan.forEach((key, value)-> { if(key.equals("name"))System.out.println(key+": "+value); });


        System.out.println(single_Spartan.get("phone"));

    }

    @Test
    public void Spartan_Map(){

        List<Map<String,Object>> allSpartans = result.jsonPath().getList("");

        allSpartans.forEach((each)-> System.out.println(each));
    }


    @Test
    public void All_Spartan_Map(){

        result=given().accept(ContentType.JSON).param("gender","male").get("search");


        List<Map<String,Object>> allSpartans = result.jsonPath().getList("content");

        allSpartans.forEach((each)-> System.out.println(each));



    }

}
