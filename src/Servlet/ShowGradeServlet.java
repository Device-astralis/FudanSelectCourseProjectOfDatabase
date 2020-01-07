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

@WebServlet("/ShowGrade")
public class ShowGradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        HttpSession session = req.getSession();
        String student_id = (String) session.getAttribute("user_id");
        int default_year = 2019;
        int default_semester = 1;
        int real_year,real_semester;
        String year = req.getParameter("year");
        String semester = req.getParameter("semester");
        if(year != null && semester != null){
            real_year = Integer.parseInt(year);
            real_semester = Integer.parseInt(semester);
        }else{
            real_year = default_year;
            real_semester = default_semester;
        }
        ArrayList<ArrayList<String>> gradeList = getGradeList(student_id,real_year,real_semester);
        req.setAttribute("gradeList",gradeList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/Grade.jsp");
        requestDispatcher.forward(req,resp);
    }

    private ArrayList<ArrayList<String>> getGradeList(String student_id,int year,int semester){
        ArrayList<ArrayList<String>> gradeList = new ArrayList<>();
        try{
            ResultSet resultSet = Dao.selectReturnSet("select course_name,course.course_id,section_id,credit,grade from course,section,stu_takes_exam where course.course_id = section.course_id and section.exam_id = stu_takes_exam.exam_id and year=? and semester=? and student_id=?",year,semester,student_id);
            while(resultSet.next()){
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(resultSet.getString(1));
                tmp.add(resultSet.getString(2));
                tmp.add(resultSet.getInt(3)+"");
                tmp.add(resultSet.getInt(4)+"");
                tmp.add(resultSet.getInt(5)+"");
                gradeList.add(tmp);
            }
            Dao.close();
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return gradeList;
    }
}
