package JDBC;


public class EnumPractice {


    public static void main(String[] args) {

        Season s1=Season.SUMMER;

        takeSeasonAction(s1);
        takeSeasonAction(Season.WINTER);
    }


    static void takeSeasonAction(Season season){


        switch (season){
            case SUMMER:
                System.out.println("Go to The Beach");
                break;

            case FALL:
                System.out.println("Thanksgiving, Apple Picking");
                break;

            case SPRING:
                System.out.println("Celebrate Nawruz");
                break;

            case WINTER:
                System.out.println("Snowboarding, Hibernate");
                break;

             default:
                 System.out.println("Not A valid JDBC.Season");

        }
    }
}
