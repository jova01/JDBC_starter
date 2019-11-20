package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class DeserializingJson {



    @BeforeClass
    public static void setUp(){



        RestAssured.baseURI= ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port=Integer.valueOf(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath=ConfigurationReader.getProperty("spartan.base_path");


    }

    @Test
    public void DeseriaLizeAnJsonToObject_Test(){
        //first way
        Spartan sp1= get("/spartans/10").//prettyPeek().
                     jsonPath().
                     getObject("",Spartan.class);

        System.out.println(sp1);

        //second way


        Spartan sp2=get("/spartans/15").//prettyPeek().
                as(Spartan.class);


        System.out.println(sp2);

    }

    @Test
    public void POJO_Add_Spartan_With_Map(){



        Spartan spartan=new Spartan("Myensulu", "Female", 1234512345);


        given().log().all().contentType(ContentType.JSON).body(spartan).
                when().
                post("/spartans/").
                then().
                statusCode(201)
                ;


    }
}
