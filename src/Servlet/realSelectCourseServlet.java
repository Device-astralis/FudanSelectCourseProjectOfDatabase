package Servlet;

import Dao.SelectSectionDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/realSelectCourse")
public class realSelectCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        HttpSession session = req.getSession();
        String student_id = (String) session.getAttribute("user_id");
        String section_id = req.getParameter("section_id");
        String course_id = req.getParameter("course_id");
        String error = realSelect(student_id,course_id,section_id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/SelectCourse?error="+error);
        requestDispatcher.forward(req,resp);
    }

    public String realSelect(String student_id,String course_id,String section_id){
        String error = "";
        boolean select = true;
        if(!SelectSectionDao.checkTaken(student_id,section_id,course_id)){
            select = false;
            error = "你已经选过这个课程了";
        }else if(!SelectSectionDao.checkSameTaken(student_id,section_id,course_id)){
            select = false;
            error = "你已经选过该课程的同类课程了";
        }else if(!SelectSectionDao.checkExamTime(student_id,section_id,course_id)){
            select = false;
            error = "所选课程和本学期已选课程考试时间存在冲突";
        }else if(!SelectSectionDao.checkClassTime(student_id,section_id,course_id)){
            select = false;
            error = "所选课程和本学期已选课程上课时间存在冲突";
        }else if(!SelectSectionDao.checkNumber(section_id,course_id)){
            select = false;
            error = "该课程选课人数已满";
        }
        if(select)
            SelectSectionDao.updateSelectCourse(section_id,student_id,course_id);
        return error;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
