package JDBC;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DBType;
import utils.DBUtility;

import java.util.List;
import java.util.Map;

public class DB_Test {



    @BeforeClass
    public static void setup(){
        DBUtility.establishConnection(DBType.ORACLE);
    }

    @Test
    public void test1(){

        System.out.println("checking 3rd region is Asia");

        List<Map<String,Object>> lstOfMap= DBUtility.runSQLQuery("Select * from Regions");

        System.out.println(lstOfMap);

        Assert.assertEquals("Asia",lstOfMap.get(2).get("REGION_NAME"));


    }

    @AfterClass
    public static void teardown(){
        DBUtility.closeConnections();
    }
}
