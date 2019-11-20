package JDBC;

import utils.ConfigurationReader;

import java.sql.*;

public class JDBC_PreparedStatement {

    public static void main(String[] args)  {


        String connection_str= ConfigurationReader.getProperty("oracledb.url");

        String db_user= ConfigurationReader.getProperty("oracledb.user");

        String db_password= ConfigurationReader.getProperty("oracledb.password");


        try {
            Connection conn = DriverManager.getConnection(connection_str, db_user, db_password);

//          Statement stmt = conn.createStatement();
//          Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String sql="select * from countries where COUNTRY_ID = ? and region_id= ? ";

            PreparedStatement stmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            stmt.setString(1,"AR");
           // stmt.setString(2,"2");
            stmt.setInt(2,2);
            ResultSet rs = stmt.executeQuery();


           rs.next();
                System.out.println(rs.getObject("Country_id") + " " +
                        rs.getObject("Country_Name") + " " +
                        rs.getObject("Region_id"));

                rs.close();
                stmt.close();
                conn.close();


        }catch (Exception e){

        }
    }
}
