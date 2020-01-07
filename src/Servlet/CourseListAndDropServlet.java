package Servlet;

import Dao.ApplySectionDao;
import Dao.SelectSectionObjectDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/CourseList")
public class CourseListAndDropServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String student_id = (String) session.getAttribute("user_id");
        req.setCharacterEncoding("utf8");
        String error = req.getParameter("error");
        req.setAttribute("error",error);
        ArrayList<ArrayList<String>> selectedSectionArrayList;
        selectedSectionArrayList = SelectSectionObjectDao.getSection(student_id);
        req.setAttribute("selected",selectedSectionArrayList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/courseList.jsp");
        requestDispatcher.forward(req,resp);
    }
}
