package JDBC;

import java.sql.*;


public class Main2 {

    public static void main(String[] args) throws SQLException {


        String connection_str = "jdbc:oracle:thin:@52.207.209.148:1521:xe"; // replace ip with your ip
        String db_user = "hr";
        String db_password = "hr";

        Connection conn = DriverManager.getConnection(connection_str, db_user, db_password);

//        Statement stmt = conn.createStatement();
          Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


        ResultSet rs = stmt.executeQuery("SELECT * FROM COUNTRies");


        while (rs.next()) {
            System.out.println(rs.getObject("Country_id") + " " +
                    rs.getObject("Country_Name") + " " +
                    rs.getObject("Region_id"));
        }

        System.out.println("--------");
       while (rs.previous()) {
           System.out.println(rs.getObject("Country_id") + " " +
                   rs.getObject("Country_Name") + " " +
                   rs.getObject("Region_id"));
       }

    }
}
