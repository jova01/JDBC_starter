package JDBC;

import utils.DBType;
import utils.DBUtility;

import java.util.List;
import java.util.Map;

public class Util_Main {

    public static void main(String[] args) {

        DBUtility.establishConnection(DBType.ORACLE);
        System.out.println(DBUtility.getRowsCount("select * from employees"));

        List<Map<String,Object>> rsLst = DBUtility.runSQLQuery("select * from employees");


//        for (Map<String, Object> each: rsLst) {
//            System.out.println("Name: "+ each.get("FIRST_NAME").toString()+" "+
//                    "Salary: "+each.get("SALARY").toString());
//        }


      //  rsLst.forEach(System.out::println); // lambda// to print everything


        rsLst.forEach((each)->System.out.println(each.get("FIRST_NAME") +" | "+ each.get("SALARY"))); // for specific column




        DBUtility.closeConnections();

        //System.out.println(rsLst.toString());



    }


}