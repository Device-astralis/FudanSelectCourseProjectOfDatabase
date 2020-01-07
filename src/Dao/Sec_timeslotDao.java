package Dao;

import Entity.Sec_timeslot;
import Entity.Section;
import Util.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sec_timeslotDao {
    private static Sec_timeslot getSecTimeSlotByRs(ResultSet resultSet) {
        Sec_timeslot newOne = null;
        try {
            int section_id = resultSet.getInt(1);
            int timeslot_id = resultSet.getInt(2);
            newOne = new Sec_timeslot(section_id,timeslot_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOne;

    }
    public static ArrayList<Sec_timeslot> getSecTimeSlot(int section_id){
        ArrayList<Sec_timeslot> sectionArrayList;
        sectionArrayList = selectSecTimeSlot("Select * from sec_timeslot where section_id=?",section_id);
        return sectionArrayList;
    }

    private static ArrayList<Sec_timeslot> selectSecTimeSlot(String sql, Object... args) {
        ArrayList<Sec_timeslot> secTimeslotArrayList = new ArrayList<>();
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
                Sec_timeslot section = getSecTimeSlotByRs(resultSet);
                secTimeslotArrayList.add(section);
            }
        } catch (Exception e) {
            System.out.println("数据库查询异常");
            e.printStackTrace();
        } finally {
            DBconnect.closeAll(connection, preparedStatement, resultSet);
        }
        return secTimeslotArrayList;
    }
}
