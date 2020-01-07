package Dao;

import Entity.Section;
import Util.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SectionDao {
    private static Section getSectionByRs(ResultSet resultSet) {
        Section newOne = null;
        try {
            String course_id = resultSet.getString(1);
            int section_id = resultSet.getInt(2);
            int year = resultSet.getInt(3);
            int semester = resultSet.getInt(4);
            int section_capacity = resultSet.getInt(5);
            int serial_number = resultSet.getInt(6);
            String teacher_id = resultSet.getString(7);
            int exam_id = resultSet.getInt(8);
            String classroom_id = resultSet.getString(9);

            newOne = new Section(course_id,section_id,year,semester,section_capacity,serial_number,teacher_id,exam_id,classroom_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }

    public static ArrayList<Section> getSection(){
        ArrayList<Section> sectionArrayList = new ArrayList<>();
        sectionArrayList = selectSection("Select * from section");
        return sectionArrayList;
    }

    private static ArrayList<Section> selectSection(String sql, Object... args) {
        ArrayList<Section> sectionArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBconnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]); // 为预编译sql设置参数
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Section section = getSectionByRs(resultSet);
                sectionArrayList.add(section);
            }
        } catch (Exception e) {
            System.out.println("数据库查询异常");
            e.printStackTrace();
        } finally {
            DBconnect.closeAll(connection, preparedStatement, resultSet);
        }
        return sectionArrayList;
    }
}
