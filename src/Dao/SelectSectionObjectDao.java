package Dao;

import Entity.*;
import Util.DBconnect;
import Dao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectSectionObjectDao {
    private static Connection connection = null;

    public static ArrayList<ArrayList<String>> getSection(String student_id){
        ResultSet resultSet1;
        int year = 0,semester = 0;
        try {
            resultSet1 =Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet1.next()){
                year = resultSet1.getInt(1);
                semester = resultSet1.getInt(2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Dao.close();
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        try{
            ResultSet resultSet = null;
            if(student_id.equals("")) {
                resultSet = Dao.selectReturnSet("select course_name,course_id,section_id,classroom_id,section_capacity,exam_id,teacher_name,credit from course natural join section natural join teacher where year=? and semester=? and course_status=1", year, semester);
            }
            else {
                resultSet = Dao.selectReturnSet("select course_name,course_id,section_id,classroom_id,section_capacity,exam_id,teacher_name,credit from course natural join section natural join teacher natural join selectsection where year=? and semester=? and student_id=?", year, semester,student_id);
            }
            while(resultSet.next()){
                ArrayList<String> tmp = new ArrayList<>();
                String course_name = resultSet.getString(1);
                String course_id = resultSet.getString(2);
                String section_id = resultSet.getInt(3)+"";
                String classroom_id = resultSet.getString(4);
                String section_capacity = resultSet.getInt(5)+"";
                int exam_id = resultSet.getInt(6);
                String teacher_name = resultSet.getString(7);;
                String credit = resultSet.getInt(8)+"";
                String course_time = "";
                int take_number = 0;
                ResultSet resultSet2 = Dao.selectReturnSet("select week,slot_start_time,slot_end_time from section natural join sec_timeslot natural join timeslot where course_id=? and section_id=?",course_id,Integer.parseInt(section_id));
                while(resultSet2.next()){
                    course_time += "周"+resultSet2.getString(1) + " " + resultSet2.getInt(2)+"-"+resultSet2.getInt(3)+"<br>";
                }
                Dao.close();
                String exam_format ="";
                String exam_time = "";
                String exam_classroom = "";
                ResultSet resultSet3 = Dao.selectReturnSet("select format,week,slot_start_time,slot_end_time,classroom_id from exam natural join timeslot where exam_id=?",exam_id);
                if(resultSet3.next()){
                    exam_format = resultSet3.getString(1).equals("test")? "考试":"论文";
                    exam_time = resultSet3.getString(1).equals("test")? "周"+resultSet3.getString(2) + " " + resultSet3.getInt(3)+"-"+resultSet3.getInt(4)+"<br>":"无";
                    exam_classroom = resultSet3.getString(1).equals("test")? resultSet3.getString(5) : "无";
                }
                Dao.close();
                ResultSet resultSet4 = Dao.selectReturnSet("select count(distinct student_id) from section natural left outer join selectsection where section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
                if(resultSet4.next())
                    take_number = resultSet4.getInt(1);
                Dao.close();
                String number = take_number + "/" + section_capacity;
                tmp.add(course_name);
                tmp.add(section_id);
                tmp.add(teacher_name);
                tmp.add(credit);
                tmp.add(exam_format);
                tmp.add(exam_time);
                tmp.add(exam_classroom);
                tmp.add(classroom_id);
                tmp.add(course_time);
                tmp.add(number);
                tmp.add(course_id);
                arrayList.add(tmp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Dao.close();
        return arrayList;
    }
}
