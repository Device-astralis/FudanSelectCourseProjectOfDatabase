package Dao;
import Entity.SelectSection;
import Util.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectSectionDao {
//    private static ResultSet resultSet = null;
    private static SelectSection getArrayList(ResultSet resultSet){
        SelectSection result = null;
        try{
            String student_id = resultSet.getString(1);
            String course_id = resultSet.getString(2);
            int section_id = resultSet.getInt(3);
            result = new SelectSection(student_id,course_id,section_id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkClassTime(String student_id,String section_id,String course_id){
        int year = 0,semester = 0;
        try{
        ResultSet resultSet1 = Dao.selectReturnSet("select year,semester from section where section_id=?",section_id);
        if(resultSet1.next()){
            year = resultSet1.getInt(1);
            semester = resultSet1.getInt(2);
        }
        Dao.close();
        ResultSet resultSet2 = Dao.selectReturnSet("select timeslot_id from (sec_timeslot natural join section) natural join selectsection where student_id=? and year=? and semester=?",student_id,year,semester);
        ArrayList<Integer> timelist = getArrayListInt(resultSet2);
        Dao.close();
        if(timelist.size() == 0)
            return true;
        ResultSet resultSet3 = Dao.selectReturnSet("select timeslot_id from sec_timeslot natural join section where section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
        while(resultSet3.next()){
            int timeslot_id = resultSet3.getInt(1);
            for (int a:timelist
                 ) {
                if(!compareTwoTimeSlot(a,timeslot_id))
                    return false;
            }
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Dao.close();
        return true;//true if could choose course.
    }

    public static boolean checkExamTime(String student_id,String section_id,String course_id){
        int year = 0,semester=0;
        try{
            ResultSet resultSetx = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSetx.next()){
                year = resultSetx.getInt(1);
                semester = resultSetx.getInt(2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Dao.close();
        try{
            int time_id = 0;
            ResultSet resultSet1 = Dao.selectReturnSet("select timeslot_id from section,exam where section.exam_id=exam.exam_id and section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
            if(resultSet1.next())
                time_id = resultSet1.getInt(1);
            Dao.close();
            ResultSet resultSet = Dao.selectReturnSet("select distinct timeslot_id from student natural join selectsection natural join section,exam where section.exam_id=exam.exam_id and year=? and semester=? and student_id=?",year,semester,student_id);
            while(resultSet.next()){
                if(!compareTwoTimeSlot(time_id,resultSet.getInt(1)))
                    return false;
            }
            Dao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
//        DBconnect.closeAll(connection, preparedStatement, resultSet);
        return true;//true if could choose course.
    }

    public static boolean checkNumber(String section_id,String course_id){
        int select_count=0,max_count = 0;
        try {
            ResultSet resultSet = Dao.selectReturnSet("select count(student_id),section_capacity from section natural left " +
                    "outer join (selectsection natural join student) where section_id=? and course_id=? group by section_id,section_capacity,course_id", Integer.parseInt(section_id),course_id);
            if(resultSet.next()){
                select_count = resultSet.getInt(1);
                max_count = resultSet.getInt(2);
            }
            Dao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (select_count < max_count);//true if could choose
    }

    public static boolean checkTaken(String student_id,String section_id,String course_id){
        ArrayList<SelectSection> arrayList = new ArrayList<>();
        arrayList = select("select * from selectsection where student_id=? and section_id=? and course_id=?",student_id,Integer.parseInt(section_id),course_id);
//        DBconnect.closeAll(connection, preparedStatement, resultSet);
        Dao.close();
        return arrayList.size()==0;//true if could choose
    }

    public static boolean checkSameTaken(String student_id,String section_id,String course_id){
        int year = 0,semester=0,i=0;
        try{
            ResultSet resultSet = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet.next()){
                year = resultSet.getInt(1);
                semester = resultSet.getInt(2);
            }
            Dao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            ResultSet resultSet1 = Dao.selectReturnSet("select section_id from student natural join selectsection natural join section where student_id=? and year=? and semester=? and course_id=?",student_id,year,semester,course_id);
            while(resultSet1.next()){
                i++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Dao.close();
        if(i == 1)
            return false;
        if(i > 1){
            System.exit(1);
        }
        return true;
    }

    private static ArrayList<SelectSection> select(String sql, Object... args) {
        Connection connection = null;
        ArrayList<SelectSection> arrayLists = new ArrayList<>();
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
                SelectSection arrayList = getArrayList(resultSet);
                arrayLists.add(arrayList);
            }
        } catch (Exception e) {
            DBconnect.closeAll(connection,preparedStatement,resultSet);
            System.out.println("数据库查询异常");
            e.printStackTrace();
        }
        return arrayLists;
    }

    private static ArrayList<Integer> getArrayListInt(ResultSet resultSet){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            while(resultSet.next()){
                int tmp = resultSet.getInt(1);
                result.add(tmp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private static boolean compareTwoTimeSlot(int time_slot_1,int time_slot_2){
        try {
            int week1=0,time_start1=0,time_end1=0,week2=0,time_start2=0,time_end2=0;
            ResultSet resultSet = Dao.selectReturnSet("select week,slot_start_time,slot_end_time from timeslot where timeslot_id=?",time_slot_1);
            ResultSet resultSet1 = Dao.selectReturnSet("select week,slot_start_time,slot_end_time from timeslot where timeslot_id=?",time_slot_2);
            if(resultSet.next()){
                week1 = resultSet.getInt(1);
                time_start1 = resultSet.getInt(2);
                time_end1 = resultSet.getInt(3);
            }
            if(resultSet1.next()){
                week2 = resultSet1.getInt(1);
                time_start2 = resultSet1.getInt(2);
                time_end2 = resultSet1.getInt(3);
            }
            if(week1 == week2){
                if(time_end2 >= time_start1 && time_start2 <= time_end1)
                    return false;
            }
        }catch (SQLException sq){
            sq.printStackTrace();
            return false;
        }
        return true;
    }

    public static void updateSelectCourse(String section_id,String student_id,String course_id){
        int exam_id = 0;
        Connection connection = null;
        try{
            ResultSet resultSet = Dao.selectReturnSet("select exam_id from section where section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
            if(resultSet.next()){
                exam_id = resultSet.getInt(1);
            }
            Dao.close();
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            Dao.updateByConnection("insert into selectsection(student_id,course_id,section_id) values(?,?,?)",connection,student_id,course_id,Integer.parseInt(section_id));
            Dao.updateByConnection("insert into stu_takes_exam(student_id,exam_id,grade) values(?,?,-1)",connection,student_id,exam_id);
            connection.commit();
        }catch (SQLException e){
            try{
                if(connection != null)
                    connection.rollback();
            }catch (SQLException eq){
                eq.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try{
                if(connection != null)
                    connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
