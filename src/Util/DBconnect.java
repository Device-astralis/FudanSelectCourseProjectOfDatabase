package Util;

import java.sql.*;

public class DBconnect {
    public static Connection getConnection() throws SQLException {
//        String jdbcUrl = "jdbc:mysql://localhost:3306/javaweb?serverTimezone=UTC";
//        String user = "root";
//        String password = "wangqidi";

        String jdbcUrl = "jdbc:mysql://localhost:3306/selectcoursesystem?serverTimezone=UTC";
        String user = "root";
        String password = "xyt112358";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  DriverManager.getConnection(jdbcUrl,user,password);
    }
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
