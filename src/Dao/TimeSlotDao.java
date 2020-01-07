package Dao;

import Entity.Section;
import Entity.TimeSlot;
import Util.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimeSlotDao {
    private static TimeSlot getTimeSlotByRs(ResultSet resultSet) {
       TimeSlot  newOne = null;
        try {
            int id = resultSet.getInt(1);
            int weeek = resultSet.getInt(2);
            int start_time = resultSet.getInt(3);
            int end_time = resultSet.getInt(4);

            newOne = new TimeSlot(id,weeek,start_time,end_time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
    public static ArrayList<TimeSlot> getSection(int time_slot_id){
        ArrayList<TimeSlot> sectionArrayList;
        sectionArrayList = selectSection("Select * from timeslot where timeslot_id=?",time_slot_id);
        return sectionArrayList;
    }

    private static ArrayList<TimeSlot> selectSection(String sql, Object... args) {
        ArrayList<TimeSlot> sectionArrayList = new ArrayList<>();
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
                TimeSlot section = getTimeSlotByRs(resultSet);
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
