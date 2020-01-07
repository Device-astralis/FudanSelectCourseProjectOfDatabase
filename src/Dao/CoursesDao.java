package Dao;

import Entity.Classroom;
import Entity.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoursesDao {
    private static Courses  getCourseByRs(ResultSet resultSet) {
        Courses newOne = null;
        try {
            int id = resultSet.getInt(1);
            String name  = resultSet.getString(2);
            int credit = resultSet.getInt(3);
            String department  = resultSet.getString(4);
            int period = resultSet.getInt(5);
//            newOne = new Courses(id,name,credit,department,period);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
    public static ArrayList<Courses> getCourses(){
        return null;
    }
}
