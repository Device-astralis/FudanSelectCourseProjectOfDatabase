package Servlet;
import Dao.*;
import Entity.Systemstatus;
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
@WebServlet("/managerDeleteCourse")
public class managerDeleteCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String course_id = req.getParameter("course_id");
      Systemstatus systemstatus = SystemStatusDao.getSystemStatus();
      String sql;
        sql = "update course set course_status = 0 where course_id = ?";
        try{
            Dao.update(sql,course_id);
        }catch (SQLException ex){
            //删除出现错误，可能是无该课程
            ex.printStackTrace();
        }
      if(systemstatus.getStatus()!=0){
          //选课状态，删除该年的section，以及所有section有关的表中的项
          ArrayList<Integer> sections = new ArrayList<>();
          sql = "select section_id from section where course_id = ? and year = ? and semester = ?";
          try{
             ResultSet resultSet = Dao.selectReturnSet(sql,course_id,systemstatus.getYear(),systemstatus.getSemester());
             while(resultSet.next()){
                 sections.add(resultSet.getInt(0));
             }
          }catch (SQLException ex){
              //删除出现错误，可能是无该课程
              ex.printStackTrace();
          }
          if(sections.size()!=0){
              //该课程存在今年本学期开课
              //将选课，退课，选课申请关于这个开课的删除
              ArrayList<Integer> errorSections = new ArrayList<>();
              for(int section_id :sections) {
                boolean success =  deleteCourseTransaction(course_id,section_id);
                if(!success){
                    errorSections.add(section_id);
                }
              }
              if(errorSections.size()!=0){
//                  req.setAttribute("course_id",course_id);
                  req.setAttribute("section_ids",errorSections);

              }

          }
      }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/managerShow?type=course");
        requestDispatcher.forward(req,resp);
      //删除完毕,跳转
    }
    private boolean deleteCourseTransaction(String course_id,int section_id){
        Connection connection = null;
        try {
            String sql = "select student_id from selectsection where course_id = ? and section_id = ?";
            ResultSet resultSet = Dao.selectReturnSet(sql, course_id, section_id);
            ArrayList<String> student_ids = new ArrayList<>();
            while(resultSet.next()){
                student_ids.add(resultSet.getString(0));
            }
            Dao.close();
            sql = "select exam_id from section where course_id = ? and section_id = ?";
            ResultSet resultSet1 = Dao.selectReturnSet(sql,course_id,section_id);
            resultSet1.next();
            int exam_id = resultSet1.getInt(0);
            Dao.close();
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            //学生参加考试这个删除 to do
             sql = "delete from selectsection where course_id = ? and section_id = ?";
            Dao.updateByConnection(sql, connection,course_id,section_id);
            sql = "delete from dropsection where course_id = ? and section_id = ?";
            Dao.updateByConnection(sql,connection, course_id,section_id);
            sql = "delete from applysection where course_id = ? and section_id = ?";
            Dao.updateByConnection(sql,connection, course_id,section_id);
            for(String student_id:student_ids){
                sql = "delete from stu_takes_exam where student_id = ? and exam_id = ? ";
                Dao.updateByConnection(sql,connection,student_id,exam_id);
            }
            sql = "delete from section where course_id = ? and section_id = ?";
            Dao.updateByConnection(sql,connection, course_id,section_id);
            connection.commit();
        }catch (SQLException ex){
            //冲突
            try{
                if(connection != null)
                    connection.rollback();
            }catch (SQLException eq){
                eq.printStackTrace();
            }
            return false;
        }finally {
            try{
                if(connection!=null){
                    connection.close();
                }
            }catch (SQLException eq){
                eq.printStackTrace();
            }
        }
        return true;
    }

}
