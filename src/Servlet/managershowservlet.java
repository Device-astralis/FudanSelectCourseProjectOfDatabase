package Servlet;
import Dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet("/managerShow")
public class managershowservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type==null){
            type=(String)req.getAttribute("type");
        }
//        ArrayList<ArrayList<String>> errordatas = req

//        HttpSession session =req.getSession();
//        String user_id = (String)session.getAttribute("name");
        ArrayList<ArrayList<String>> returndatas = new ArrayList<>();
        ArrayList<String> head = new ArrayList<>();
        switch (type) {
            case "account":
                returndatas = showAccount();
                head.add("账户名");
                head.add("账户密码");
                break;
            case "student":
                //拿到所有学生的表
                returndatas = showStudent();
                head.add("学生学号");
                head.add("学生姓名");
                head.add("所属院系");
                break;
            case "teacher":
                returndatas = showTeacher();
                head.add("教师工号");
                head.add("教师姓名");
                head.add("所属院系");
                break;
            case "classroom":
                returndatas = showClassroom();
                head.add("教室编号");
                head.add("教室容量");
                break;
            case "course":
                returndatas = showCourse();
                head.add("课程代码");
                head.add("课程名称");
                head.add("学分");
                head.add("课程院系");
                head.add("课时");
                break;
            case "section":
                returndatas = showSection();
                head.add("课程代码");
                head.add("开课编码");
                head.add("开课学年");
                head.add("开课学期");
                head.add("开课人数");
                head.add("开课序号");
                head.add("教师工号");
                head.add("考试编号");
                head.add("教室编号");
                head.add("上课时间点编号");
                break;
            case "exam":
                returndatas = showExam();
                head.add("考试编号");
                head.add("考试类型");
                head.add("考试时间点编号");
                head.add("考试教室编号");
                break;
            case "timeslot":
                returndatas = showTimeslot();
                head.add("上课时间点编号");
                head.add("上课星期");
                head.add("上课开始时间");
                head.add("上课结束时间");
                break;
            case "selectsection":
                returndatas = showSelectsection();
                head.add("学生学号");
                head.add("课程代码");
                head.add("开课编码");
                break;
            case "dropsection":
                returndatas = showDropsection();
                head.add("学生学号");
                head.add("课程代码");
                head.add("开课编码");
                break;
            case "applysection":
                returndatas = showApplysection();
                head.add("学生学号");
                head.add("课程代码");
                head.add("开课编码");
                head.add("申请状态");
                head.add("申请理由");
                break;
            case "systemstatus":
                returndatas = showSystemStatus();
                head.add("系统年份");
                head.add("系统学期");
                head.add("系统状态");
        }
        req.setAttribute("type",type);
        req.setAttribute("head",head);
        req.setAttribute("returnData",returndatas);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/manager/manager.jsp");
        requestDispatcher.forward(req,resp);
    }

    private ArrayList<ArrayList<String>> showAccount() {
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from account ";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }

    private ArrayList<ArrayList<String>> showStudent(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from student ";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                 alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getString(3));
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showTeacher(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from teacher ";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getString(3));
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showClassroom(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from classroom";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getInt(2)+"");
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showCourse(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from course where course_status = 1";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getInt(3)+"");
                alines.add (resultSet.getString(4));
                alines.add (resultSet.getInt(5)+"");
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showSection(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from section ";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getInt(2)+"");
                alines.add (resultSet.getInt(3)+"");
                alines.add (resultSet.getInt(4)+"");
                alines.add (resultSet.getInt(5)+"");
                alines.add (resultSet.getInt(6)+"");
                alines.add (resultSet.getString(7));
                alines.add (resultSet.getInt(8)+"");
                alines.add (resultSet.getString(9));
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        //添加timeslot_ids
        try{
            for(ArrayList<String> rowdata:getdatas){
                String course_id = rowdata.get(0);
                String section_id = rowdata.get(1);

            String sql = "select timeslot_id from sec_timeslot where course_id = ? and section_id = ?";
            ResultSet resultSet = Dao.selectReturnSet(sql,course_id,section_id);
            String timeslot_ids = "";
            while(resultSet.next()){
                timeslot_ids+=resultSet.getInt(1);
            }
            rowdata.add(timeslot_ids);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showExam(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from exam";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getInt(1)+"");
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getInt(3)+"");
                alines.add (resultSet.getString(4));
                getdatas.add(alines);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showTimeslot() {
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from timeslot ";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getInt(1)+"");
                alines.add (resultSet.getInt(2)+"");
                alines.add (resultSet.getInt(3)+"");
                alines.add (resultSet.getInt(4)+"");
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showSelectsection(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from selectsection";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getInt(3)+"");
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showDropsection(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from dropsection";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getInt(3)+"");
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showApplysection(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from applysection";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getString(1));
                alines.add (resultSet.getString(2));
                alines.add (resultSet.getInt(3)+"");
                alines.add (resultSet.getInt(4)+"");
                alines.add (resultSet.getString(5));
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
    private ArrayList<ArrayList<String>> showSystemStatus(){
        ArrayList<ArrayList<String>> getdatas = new ArrayList<>();
        try {
            String sql = "select * from systemstatus";
            ResultSet resultSet = Dao.selectReturnSet(sql);
            while(resultSet.next()){
                ArrayList<String> alines = new ArrayList<>();
                alines.add (resultSet.getInt(1)+"");
                alines.add (resultSet.getInt(2)+"");
                alines.add (resultSet.getInt(3)+"");
                getdatas.add(alines);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return getdatas;
    }
}
