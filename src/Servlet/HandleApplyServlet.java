package Servlet;

import Dao.ApplySectionDao;
import Dao.Dao;
import Util.DBconnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/HandleApply")
public class HandleApplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String student_id = req.getParameter("student_id");
        String section_id = req.getParameter("section_id");
        String course_id = req.getParameter("course_id");
        String choose = req.getParameter("choose");
        if(choose.equals("0"))
            notPass(student_id,section_id,course_id);
        else if(choose.equals("1"))
            pass(student_id,section_id,course_id);
        req.setCharacterEncoding("utf8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ManagerApply");
        requestDispatcher.forward(req,resp);
    }

    private void notPass(String student_id, String section_id,String course_id){
        try{
            Dao.update("update applysection set apply_status=0 where student_id=? and section_id=? and course_id=?",student_id,Integer.parseInt(section_id),course_id);
            Dao.close();
        }catch (SQLException sq){
            sq.printStackTrace();
        }
    }

    private void pass(String student_id, String section_id,String course_id){
        Connection connection = null;
        int exam_id = 0;
        int room_capacity = 0;
        int student_number = 0;
        try{
            ResultSet resultSet = Dao.selectReturnSet("select exam_id from section where section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
            if(resultSet.next()){
                exam_id = resultSet.getInt(1);
            }
            Dao.close();
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            Dao.updateByConnection("update applysection set apply_status=1 where student_id=? and section_id=? and course_id=?",connection,student_id,Integer.parseInt(section_id),course_id);
            Dao.updateByConnection("insert into selectsection(student_id,course_id,section_id) values(?,?,?)",connection,student_id,course_id,Integer.parseInt(section_id));
            Dao.updateByConnection("insert into stu_takes_exam(student_id,exam_id,grade) values(?,?,-1)",connection,student_id,exam_id);
            ResultSet resultSet1 = Dao.selectReturnSet("select class_capacity from section natural join classroom where section_id=? and course_id=?",Integer.parseInt(section_id),course_id);
            if(resultSet1.next())
                room_capacity = resultSet1.getInt(1);
            ResultSet resultSet2 = Dao.selectReturnSet("select count(distinct student_id) from selectsection where course_id=? and section_id=? group by section_id,course_id",course_id,Integer.parseInt(section_id));
            if(resultSet2.next())
                student_number = resultSet2.getInt(1);
            if(room_capacity == (student_number + 1)){//clear other apply
                Dao.updateByConnection("update applysection set apply_status=0 where applys_status=-1 and section_id=? and course_id=? and student_id not in (select student_id from student where student_id=?);",connection,Integer.parseInt(section_id),course_id,student_id);
            }
            connection.commit();
        }catch (SQLException sq){
            try{
                if(connection != null)
                    connection.rollback();
            }catch (SQLException eq){
                eq.printStackTrace();
            }
            sq.printStackTrace();
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
