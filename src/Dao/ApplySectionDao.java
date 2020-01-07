package Dao;

import Entity.ApplySection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplySectionDao {
    private static Connection connection = null;
//    private static ApplySection getApplySectionByRs(ResultSet resultSet) {
//        ApplySection newOne = null;
//        try {
//            int student_id = resultSet.getInt(1);
//            String course_id = resultSet.getString(2);
//            String apply_reason = resultSet.getString(3);
//            int section_id = resultSet.getInt(4);
//            int status = resultSet.getInt(5);
//            newOne = new ApplySection(student_id, course_id,apply_reason,section_id,status);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return newOne;
//
//    }

    public static ArrayList<ArrayList<String>> getApplySectionByStudent(String student_id){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int year=0,semester=0;
        try {
            ResultSet resultSet = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet.next()){
                year = resultSet.getInt(1);
                semester = resultSet.getInt(2);
            }
            ResultSet resultSet1 = Dao.selectReturnSet("select course_id,section_id,apply_reason,apply_status from applysection natural join section where year=? and semester=? and student_id=?",year,semester,student_id);
            while(resultSet1.next()){
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(resultSet1.getString(1));
                tmp.add(resultSet1.getInt(2)+"");
                tmp.add(resultSet1.getString(3));
                tmp.add(resultSet1.getInt(4)+"");
                result.add(tmp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkCourse(String section_id,String course_id){
        int year=0,semester=0,exist=0;
        try {
            ResultSet resultSet = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet.next()){
                year = resultSet.getInt(1);
                semester = resultSet.getInt(2);
            }
            ResultSet resultSet1 = Dao.selectReturnSet("select * from section where year=? and semester=? and section_id=? and course_id=?",year,semester,Integer.parseInt(section_id),course_id);
            if(resultSet1.next())
                exist++;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (exist == 1);
    }
    public static boolean checkNumber(String section_id,String course_id){
        return !SelectSectionDao.checkNumber(section_id,course_id);
    }//true if could apply
    public static boolean checkTaken(String student_id,String section_id,String course_id){
        return SelectSectionDao.checkTaken(student_id,section_id,course_id);
    }
    public static boolean checkSameTaken(String student_id,String section_id,String course_id){
        return SelectSectionDao.checkSameTaken(student_id,section_id,course_id);
    }
    public static boolean checkClassTime(String student_id,String section_id,String course_id){
        return SelectSectionDao.checkClassTime(student_id,section_id,course_id);
    }
    public static boolean checkExamTime(String student_id,String section_id,String course_id){
        return SelectSectionDao.checkExamTime(student_id,section_id,course_id);
    }
    public static boolean checkDrop(String student_id,String section_id,String course_id){
        int i = 0;
        try{
            ResultSet resultSet = Dao.selectReturnSet("select * from dropsection where student_id=? and section_id=? and course_id=?",student_id,Integer.parseInt(section_id),course_id);
            while(resultSet.next())
                i++;
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        Dao.close();
        return (i == 0);
    }
    public static boolean checkMaxNumber(String section_id,String course_id){
        int class_capacity = 0;
        int student_number = 0;
        try{
            ResultSet resultSet = Dao.selectReturnSet("select class_capacity from section natural join classroom where section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
            if(resultSet.next())
                class_capacity = resultSet.getInt(1);
            ResultSet resultSet1 = Dao.selectReturnSet("select count(student_id) from section natural left " +
                    "outer join (selectsection natural join student) where section_id=? and course_id=? group by section_id,course_id", Integer.parseInt(section_id),course_id);
            if(resultSet1.next())
                student_number = resultSet1.getInt(1);
            Dao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(class_capacity ==0)
            return false;
        return (student_number < class_capacity);
    }
    public static boolean checkSelectStatus(String student_id,String course_id){
        int year=0,semester=0;
        try{
            ResultSet resultSet1 = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet1.next()){
                year = resultSet1.getInt(1);
                semester = resultSet1.getInt(2);
            }
            ResultSet resultSet = Dao.selectReturnSet("select apply_status from section natural join applysection where year=? and semester=? and course_id=? and student_id=?",year,semester,course_id,student_id);
            while(resultSet.next()){
                if(resultSet.getInt(1) == -1)
                    return false;
            }
            Dao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static void updateApplySection(String student_id,String course_id,String section_id,String apply_reason){
        try{
            Dao.update("insert into applysection(student_id,course_id,apply_reason,section_id,apply_status) values(?,?,?,?,-1)",student_id,course_id,apply_reason,Integer.parseInt(section_id));
            Dao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
