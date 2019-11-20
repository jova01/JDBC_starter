package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SendingPostRequest {

    public static Response result;


    @BeforeClass
    public static void setUp(){



        RestAssured.baseURI= ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port=Integer.valueOf(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath=ConfigurationReader.getProperty("spartan.base_path");

        result=get("");
    }

    @Test
    public void Logging_RestAssured(){

        given().pathParam("my_id",3).when().
                log().all().
                when().
                get("/spartans/{my_id}").
                then().
                log().ifValidationFails().statusCode(200)
        ;

    }

    @Test
    public void Add_NewSpartan_Test(){

        given().contentType(ContentType.JSON).body(" {\n" +
                "            \"name\": \"Fakha\",\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"phone\": 9563452345\n" +
                "        },").
                when().
                        post("/spartans/").
                then().log().all().
                        statusCode(201).
                        body("success", is("A Spartan is Born!")).
                        body("data.name",equalToIgnoringCase("Fakha")).
                        body("data.phone", hasToString("9563452345"));

    }
    @Test
    public void Add_NewSpartan_With_Map(){

        Map<String,Object> bodyMap=new HashMap<>();
        bodyMap.put("name","Rais");
        bodyMap.put("gender","Male");
        bodyMap.put("phone","6563452345");


        given().contentType(ContentType.JSON).body(bodyMap).
                when().
                post("/spartans/").
                then().log().all().
                statusCode(201).
                contentType(ContentType.JSON).
                body("success", is("A Spartan is Born!")).
                body("data.name",equalToIgnoringCase("Rais")).
                body("data.phone", hasToString("6563452345"));

    }

    @Test
    public void POJO_Add_Spartan_With_Map(){



        Spartan spartan=new Spartan("Myensulu", "Female", 1234512345);


        given().contentType(ContentType.JSON).body(spartan).
                when().
                post("/spartans/").
                then().log().all().
                statusCode(201).
                contentType(ContentType.JSON).
                body("success", is("A Spartan is Born!")).
                body("data.name",equalToIgnoringCase("Myensulu")).
                body("data.phone", hasToString("1234512345"));

    }

    @Test
    public void Add_Spartan_Negative_Test(){



        Spartan spartan=new Spartan("M", "Female", 1234512345);


        given().contentType(ContentType.JSON).body(spartan).
                when().
                post("/spartans/").
                then().log().all().
                statusCode(400).
                body("error", is("Bad Request")).
                body("errors[0].defaultMessage",is("name should be at least 2 character and max 15 character"))
               ;

    }


    @Test
    public void Add_All_Negative_(){



        Spartan spartan=new Spartan("M", "F", 123);


        given().contentType(ContentType.JSON).body(spartan).
                when().
                post("/spartans/").
                then().log().all().
                statusCode(400).
                body("error", is("Bad Request")).
//                body("errors.defaultMessage",hasItem("name should be at least 2 character and max 15 character")).
//                body("errors.defaultMessage", hasItem("Gender should be either Male or Female"))
                body("errors.defaultMessage",
                hasItems("name should be at least 2 character and max 15 character",
                            "Gender should be either Male or Female"),
                   "Phone number should be at least 10 digit").
                body("message", containsString("Error count: 3"))
                        ;

    }



}
