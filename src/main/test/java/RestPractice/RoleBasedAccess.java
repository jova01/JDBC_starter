package RestPractice;

import io.restassured.http.ContentType;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RoleBasedAccess extends TestBase{





//    @BeforeClass
//    public static void setUp(){
//
//
//
//        //// Akbars  url and its locked
//        RestAssured.baseURI="http://54.145.11.232:8000";
//        RestAssured.port=8000;
//        RestAssured.basePath="/api/spartans/";
//    }


    @Test
    public void testUserRoleCan(){


        given().
                auth().basic("admin","admin").
                log().all()
                .accept(ContentType.JSON)
        .when()
                .get("/spartans/5").then().
                log().all().statusCode(200);



    }

    @Test
    public void AuthenticatedUser_with_USERRoleCanViewData_Test() {
        String username = ConfigurationReader.getProperty("spartan.user_role.name");
        String password = ConfigurationReader.getProperty("spartan.user_role.password");
        given()
                // this is how we do basic auth authentication in RestAssured
                .auth().basic(username,password)
                .log().all()
                .pathParam("spartan_id",117)
                .contentType(ContentType.JSON)
                .body(new Spartan("Asim","Male",987654321)).
                when()
                .put("/spartans/{spartan_id}").
                then()
                .statusCode(403)
                .body("error",is("Forbidden"))
        ;




    }

    @Test
    public void AuthenticatedUser_with_Role_USER_CanNotUpdateData_Test(){
        String username = ConfigurationReader.getProperty("spartan.admin_role.name");
        String password = ConfigurationReader.getProperty("spartan.admin_role.password");
        given()
                // this is how we do basic auth authentication in RestAssured

                .log().all()
                .pathParam("spartan_id",117)
                .contentType(ContentType.JSON)
                .body(new Spartan("Asim","Male",987654321)).
                when()
                .put("/spartans/{spartan_id}").
                then()
                .log().all()
                .statusCode(204)
                .body( blankOrNullString() )
        ;
    }




}
