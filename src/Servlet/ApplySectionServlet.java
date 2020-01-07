package Servlet;

import Dao.ApplySectionDao;
import Dao.Dao;
import Dao.SystemStatusDao;

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

@WebServlet("/applyServlet")
public class ApplySectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        HttpSession session = req.getSession();
        String student_id = (String) session.getAttribute("user_id");
        String course_id = req.getParameter("course_id");
        String section_id = req.getParameter("section_id");
        String reason = req.getParameter("apply_reason");
        String error = applySection(student_id,course_id,section_id,reason);
        req.setAttribute("error",error);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/ApplySelect.jsp");
        requestDispatcher.forward(req,resp);
    }

    public String applySection(String student_id,String course_id,String section_id,String reason){
        boolean update = true;
        boolean status = SystemStatusDao.checkStatus();
        String error = "";
        if(status) {
            if (checkType(student_id, course_id, section_id)) {
                if (ApplySectionDao.checkCourse(section_id, course_id)) {
                    if (!ApplySectionDao.checkDrop(student_id, section_id, course_id)) {
                        update = false;
                        error = "你已经退过该课程，不能进行申请。";
                    } else if (!ApplySectionDao.checkMaxNumber(section_id, course_id)) {
                        update = false;
                        error = "该课程的人数已经达教室最大人数。";
                    } else if (!checkAllSection(course_id)) {
                        update = false;
                        error = "该课程或者其同类课程尚有余量。";
                    } else if (!ApplySectionDao.checkSameTaken(student_id, section_id, course_id)) {
                        update = false;
                        error = "你已经选上该课程的同类课程。";
                    } else if (!ApplySectionDao.checkTaken(student_id, section_id, course_id)) {
                        update = false;
                        error = "你已经选上该课程。";
                    } else if (!ApplySectionDao.checkClassTime(student_id, section_id, course_id)) {
                        update = false;
                        error = "你所申请的课程和你的已选课程上课时间存在冲突。";
                    } else if (!ApplySectionDao.checkExamTime(student_id, section_id, course_id)) {
                        update = false;
                        error = "你所申请的课程和你的已选课程的考试时间存在冲突。";
                    } else if (!ApplySectionDao.checkSelectStatus(student_id, course_id)) {
                        update = false;
                        error = "你对该课程已提交的申请尚未处理，请勿重复申请。";
                    }
                } else {
                    update = false;
                    error = "该课程不存在或者本学期不开课";
                }
            } else {
                update = false;
                error = "你输入的内容存在格式问题。";
            }
        }else {
            update = false;
            error = "不处于选课阶段，您无法进行选课申请。";
        }
        if(update)
            ApplySectionDao.updateApplySection(student_id,course_id,section_id,reason);
        return error;
    }

    private boolean checkAllSection(String course_id){
        int year=0,semester=0;
        try {
            ResultSet resultSet = Dao.selectReturnSet("select year,semester from systemstatus");
            if(resultSet.next()){
                year = resultSet.getInt(1);
                semester = resultSet.getInt(2);
            }
            ResultSet resultSet1 = Dao.selectReturnSet("select section_id from section where course_id=? and year=? and semester=?",course_id,year,semester);
            while (resultSet1.next()){
                int section_id = resultSet1.getInt(1);
                if(!ApplySectionDao.checkNumber(section_id+"",course_id)){
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkType(String student_id , String course_id , String section_id){
        if(student_id == null || course_id == null || section_id == null || student_id.isEmpty() || course_id.isEmpty() || section_id.isEmpty() )
            return false;
        if(student_id.charAt(0) != 'S' || !isNumeric(student_id.substring(1)) || !isNumeric(section_id))
            return false;
        return true;
    }
    private boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
