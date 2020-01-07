package Dao;

import Entity.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomDao {
    private static Classroom getClassroomByRs(ResultSet resultSet) {
        Classroom newOne = null;
        try {
            String id = resultSet.getString(1);
            int capacity = resultSet.getInt(2);
            newOne = new Classroom(id,capacity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
}
