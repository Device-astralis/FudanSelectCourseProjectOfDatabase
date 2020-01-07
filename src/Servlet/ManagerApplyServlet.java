package Servlet;

import Dao.Dao;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/ManagerApply")
public class ManagerApplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        HttpSession session = req.getSession();
        String teacher_id = (String) session.getAttribute("user_id");
        Map<String, ArrayList<ArrayList<String>>> ApplyList = getApplyList(teacher_id);
        req.setAttribute("applyList",ApplyList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/manager.jsp");
        requestDispatcher.forward(req,resp);
    }
    private Map<String, ArrayList<ArrayList<String>>> getApplyList(String teacher_id){
        int year=0,semester=0;
        Map<String,ArrayList<ArrayList<String>>> applyList = new HashMap<>();
        try{
            ResultSet resultSet3 = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet3.next()){
                year = resultSet3.getInt(1);
                semester = resultSet3.getInt(2);
            }
            Dao.close();
            Map<String,Integer> map = new HashMap<>();
            ResultSet resultSet = Dao.selectReturnSet("select course_id,section_id from section natural join teacher natural join course where teacher_id=? and year=? and semester=?",teacher_id,year,semester);
            if(resultSet.next()){
                String course_id = resultSet.getString(1);
                int section_id = resultSet.getInt(2);
                map.put(course_id,section_id);
                while(resultSet.next()){
                    course_id = resultSet.getString(1);
                    section_id = resultSet.getInt(2);
                    map.put(course_id,section_id);
                }
            }else
                return null;
            Dao.close();
            Set<String> courseIDSet = map.keySet();
            for (String courseID : courseIDSet) {
                int sectionID = map.get(courseID);
                String course_name = "";
                ArrayList<ArrayList<String>> student_list = new ArrayList<>();
                ResultSet resultSet1 = Dao.selectReturnSet("select student_id,student_name,apply_reason from student natural join applysection where course_id=? and section_id=? and apply_status=?",courseID,sectionID,-1);
                ResultSet resultSet2 = Dao.selectReturnSet("select course_name from course where course_id=?",courseID);
                if(resultSet2.next())
                    course_name = resultSet2.getString(1);
                while(resultSet1.next()){
                    ArrayList<String> tmp = new ArrayList<>();
                    String student_id = resultSet1.getString(1);
                    String student_name = resultSet1.getString(2);
                    String apply_reason = resultSet1.getString(3);
                    tmp.add(student_id);
                    tmp.add(student_name);
                    tmp.add(apply_reason);
                    tmp.add(courseID);
                    tmp.add(sectionID+"");
                    student_list.add(tmp);
                }
                applyList.put(course_name,student_list);
            }
            Dao.close();
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return applyList;
    }
}
