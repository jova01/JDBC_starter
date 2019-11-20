package RestPractice;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class Full_Cycle_Test extends TestBase{



    @Test
    public void getSpartan_Test(){

        int spartanID = createRandomSpartan();
        System.out.println(spartanID);

        given()
                .log().all()
                .pathParam("id",spartanID).
                body(new Spartan("Zeynep", "Female", 1234567890)).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);

//
//        given()
//                .log().all().
//                when()
//                .get("/spartans/"+spartanID).
//                then()
//                .statusCode(200)
//                .body("id",is(spartanID))
//                .header("content-type",is("application/json;charset=UTF-8"))
//        ;
//        given()
//                .log().all().
//                when()
//                .delete("/spartans/"+spartanID).
//                then()
//                .log().all()
//                .statusCode(204)
//                // do not provide json path for empty body
//                .body(blankOrNullString())
//        ;
//        given()
//                .log().all().
//                when()
//                .get("/spartans/"+spartanID).
//                then()
//                .statusCode(404)
//        ;

    }


    public int createRandomSpartan(){

        Faker fake=new Faker();

        int randomIndex = fake.number().numberBetween(0,2);
        String[] genders = {"Male","Female"};
        String randomGender = genders[randomIndex];


        Spartan spartan=new Spartan(fake.name().firstName(), randomGender,
                Long.parseLong(fake.number().digits(11)));


        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
                        when()
                .post("/spartans/")
                .jsonPath()
                .getInt("data.id") ;
    }
}
