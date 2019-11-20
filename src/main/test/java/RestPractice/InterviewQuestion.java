package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class InterviewQuestion {


    public static Response result;




    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://newsapi.org/v2/top-headlines";

    }



    @Test
    public void test(){

        System.out.println(getTitle("Mariella Moon"));

    }


    public String getTitle(String str){


        result=given().accept(ContentType.JSON).
                 queryParam("country", "us").
                 queryParam("category", "business")
                .queryParam("apiKey", "c0feb0bc81c74e9284814912f6ccaa4a").get("");
        List<String> titleList=result.jsonPath().getList("articles.title");
        List<String> author=result.jsonPath().getList("articles.author");

        Map<String, String> map=new HashMap<>();

        for (int i=0; i< titleList.size(); i++){
            if(author.get(i)!=null && author.get(i)!="")
                map.put(author.get(i), titleList.get(i));

        }

        //        map.forEach((key, value ) -> System.out.println(key+value));
//
//        for (Map.Entry<String,String> entry : map.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());


        return map.get(str);
    }
}
