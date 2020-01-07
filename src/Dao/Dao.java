package Dao;

import Entity.Teacher;
import Util.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public static ResultSet selectReturnSet(String sql, Object... args) throws SQLException {
//        try {
            connection = DBconnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]); // 为预编译sql设置参数
            }
            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Teacher newTeacher = getTeacherByRs(resultSet);
//                artworks.add(newTeacher);
//            }
//        } catch (SQLException e) {
//            System.out.println("数据库查询异常");
//            e.printStackTrace();
//        } finally {
//        }
        return resultSet;
    }
    public static void update(String sql, Object... args) throws SQLException{

//        try {
            connection = DBconnect.getConnection(); // 得到数据库连接
            preparedStatement = connection.prepareStatement(sql); // 得到PreparedStatement对象

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]); // 为预编译sql设置参数
            }
            preparedStatement.executeUpdate();
            DBconnect.closeAll(connection,preparedStatement,null);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//        }
    }

    public static void updateByConnection(String sql, Connection connection,Object... args) throws SQLException{

        preparedStatement = connection.prepareStatement(sql); // 得到PreparedStatement对象

        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]); // 为预编译sql设置参数
        }
        preparedStatement.executeUpdate();
    }

    public static void close(){DBconnect.closeAll(connection,preparedStatement,resultSet);}
}
