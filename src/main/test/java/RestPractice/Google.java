package RestPractice;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Google {

    @Test
    public void getGoogle(){
        given().get("https://www.google.com/").prettyPrint();
    }
}
