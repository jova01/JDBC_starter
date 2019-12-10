package ExperianProject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Experian {

    public static String name;

    public static void main(String[] args) {

        name = "Richard Lawler";

        Experian experian = new Experian();
       // System.out.println(experian.returningTitle(name));
      //  System.out.println(experian.returningAuthor());
         experian.UnitTestsForMethod1ANDMethod2();

        System.out.println(experian.getListOfHeadline());
        //System.out.println(experian.returningAuthor());

    }

    public List<Headline> getListOfHeadline(){
        Response result = given().accept(ContentType.JSON)
                .baseUri("https://newsapi.org/v2/top-headlines")
                .queryParam("country", "us")
                .queryParam("category", "business")
                .queryParam("pageSize", 100)
                .queryParam("apiKey", "c0feb0bc81c74e9284814912f6ccaa4a").get("");



        List<Headline> headlines = result.jsonPath().getList("articles", Headline.class);
        return headlines;
    }

    public List<String> returningTitle( String name){
        List<Headline> myList = getListOfHeadline();
        List<String> listOfTitles = new ArrayList<>();
        for(Headline headline : myList){
            if(headline.author!=null && headline.author.equals(name)){
                listOfTitles.add(headline.title);
            }
        }
        return listOfTitles;
    }

    //One method return all articles if  source[‘id’] is not null.
    public List<String> returningAuthor(){
        List<Headline> myList = getListOfHeadline();
        List<String> listOfAuthors = new ArrayList<>();
        for(Headline headline : myList){
            if(headline.sourceID!=null){
                listOfAuthors.add(headline.author);
            }
        }
        return listOfAuthors;
    }

    @Test
    public void returningTitleWithExceptionHandling(){
        List<Headline> myList = getListOfHeadline();
        try {
            for(Headline headline : myList){
                if(headline.author.equals("Nico Ylanan")){
                    System.out.println(headline.title);
                }
            }
        }catch (NullPointerException npe){
            System.out.println("App Doesn't Accept: "+npe.getMessage());
        }
    }

    //Unit Tests for Method1 and Method2
    public void UnitTestsForMethod1ANDMethod2(){
        List<String> listOfMethod1 = returningTitle(name);
        Assert.assertTrue("Method1 FAIL", listOfMethod1.contains("Elon Musk took Tesla's Cybertruck for a weekend drive around LA - Engadget"));

        List<String> listOfMethod2 = returningAuthor();
        Assert.assertTrue("Method2 FAIL", listOfMethod2.contains("Richard Lawler"));

    }




}
