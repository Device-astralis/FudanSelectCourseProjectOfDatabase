package Dao;

import Entity.Teacher;
import Util.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TeacherDao {
    private static Teacher getTeacherByRs(ResultSet resultSet) {
        Teacher newOne = null;
        try {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String department = resultSet.getString(3);
            //     Date uploadingTime = new Date();
            newOne = new Teacher(id, name,department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
}
