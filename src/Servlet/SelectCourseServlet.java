package Servlet;

import Dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SelectCourse")
public class SelectCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        boolean couldSelect = SystemStatusDao.checkStatus();
        if(couldSelect){
            ArrayList<ArrayList<String>> sectionsArrayList;
            String error = req.getParameter("error");
            sectionsArrayList = SelectSectionObjectDao.getSection("");
            req.setAttribute("Section",sectionsArrayList);
            req.setAttribute("error",error);
            req.setAttribute("couldSelect","1");
        }else {
            req.setAttribute("couldSelect","0");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/selectCourse.jsp");
        requestDispatcher.forward(req,resp);
    }
}
