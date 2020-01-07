package Dao;

import Entity.ApplySection;
import Entity.DropSection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DropSectionDao {
    private static DropSection getDropSectionByRs(ResultSet resultSet) {
        DropSection newOne = null;
        try {
            int student_id = resultSet.getInt(1);
            int course_id = resultSet.getInt(2);
            int section_id = resultSet.getInt(3);
            newOne = new DropSection(student_id, course_id,section_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
}
