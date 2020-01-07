package Dao;

import Entity.Stu_takes_exam;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stu_takes_examDao {
    private static Stu_takes_exam getStuTakeExamByRs(ResultSet resultSet) {
        Stu_takes_exam newOne = null;
        try {
            int stu_id = resultSet.getInt(1);
            int ex_id = resultSet.getInt(2);
            int grade = resultSet.getInt(3);
            newOne = new Stu_takes_exam(stu_id,ex_id,grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
}
