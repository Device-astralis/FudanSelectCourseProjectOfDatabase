package Dao;

import Entity.Exam_classroom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Exam_classroomDao {
    private static Exam_classroom getExamClassroomByRs(ResultSet resultSet) {
        Exam_classroom newOne = null;
        try {
            int exam_id = resultSet.getInt(1);
            int class_id = resultSet.getInt(2);
            newOne = new Exam_classroom(exam_id,class_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
}
