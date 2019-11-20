package JDBC;

import utils.ConfigurationReader;

import java.sql.*;

public class MetaData {

    public static void main(String[] args) throws SQLException {


        Connection conn =DriverManager.getConnection(ConfigurationReader.getProperty("oracledb.url"),
                ConfigurationReader.getProperty("oracledb.user"),
                ConfigurationReader.getProperty("oracledb.password"));

        String sql = "SELECT * FROM COUNTRIES " ;
        PreparedStatement stmt = conn.prepareStatement(sql , ResultSet.TYPE_SCROLL_INSENSITIVE
                , ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery();


        rs.last();

        System.out.println(rs.getRow());

        /*
        MeTaDAta is data about the data

        DatabaseMetaData


        ResultSetMetaData
         */


        DatabaseMetaData dbmd=conn.getMetaData();
        System.out.println("Product name: "+dbmd.getDatabaseProductName());
        System.out.println("Username: "+dbmd.getUserName());


        ResultSetMetaData rsmd=rs.getMetaData();
        System.out.println("Column Count: "+rsmd.getColumnCount());
        System.out.println("Column Name: "+ rsmd.getColumnName(2));


        System.out.println("------------");

        for (int i=1; i<=rsmd.getColumnCount(); i++){
            System.out.print(rsmd.getColumnName(i)+" | ");
        }
        //        rs.next();
//        System.out.println(rs.getObject("COUNTRY_ID")
//                + " "+ rs.getObject("COUNTRY_Name")
//                + " "+ rs.getObject("REGION_ID") );
//        rs.close();
//        stmt.close();
//        conn.close();

    }
}
