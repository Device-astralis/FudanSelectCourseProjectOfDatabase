package Servlet;

import Dao.Dao;
import Util.DBconnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/DropSection")
public class DropSectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String student_id = (String) session.getAttribute("user_id");
        String section_id = req.getParameter("section_id");
        String course_id = req.getParameter("course_id");
        String error = deleteSection(student_id,section_id,course_id);
        req.setCharacterEncoding("utf8");
        req.setAttribute("error",error);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/CourseList");
        requestDispatcher.forward(req,resp);
    }

    private String deleteSection(String student_id,String section_id,String course_id){
        Connection connection = null;
        int exam_id = -1;
        try{
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            Dao.updateByConnection("delete from selectsection where student_id=? and section_id=? and course_id=?",connection,student_id,Integer.parseInt(section_id),course_id);
            ResultSet resultSet = Dao.selectReturnSet("select exam_id from selectsection natural join section where student_id=? and section_id=? and course_id=?",student_id,Integer.parseInt(section_id),course_id);
            if(resultSet.next())
                exam_id = resultSet.getInt(1);
            Dao.updateByConnection("delete from stu_takes_exam where student_id=? and exam_id=?",connection,student_id,exam_id);
            ResultSet resultSet1 = Dao.selectReturnSet("select * from dropsection where student_id=? and course_id=? and section_id=?",student_id,course_id,Integer.parseInt(section_id));
            if(!resultSet1.next())
                Dao.updateByConnection("insert into dropsection(student_id,course_id,section_id) values(?,?,?)",connection,student_id,course_id,Integer.parseInt(section_id));
            if(exam_id == -1){
                connection.rollback();
                return "退课失败。";
            }else
                connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            try{
                if(connection != null)
                    connection.rollback();
            }catch (SQLException sq){
                sq.printStackTrace();
            }
        }finally {
            try{
                if(connection != null)
                    connection.close();
            }catch (SQLException sq){
                sq.printStackTrace();
            }
        }
        return "退课成功";
    }
}
