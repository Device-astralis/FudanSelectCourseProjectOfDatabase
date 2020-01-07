package Servlet;

import Dao.ApplySectionDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ApplyStatus")
public class ApplyStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String student_id = (String) session.getAttribute("user_id");
        req.setCharacterEncoding("utf8");
        ArrayList<ArrayList<String>> applySectionArrayList;
        applySectionArrayList = ApplySectionDao.getApplySectionByStudent(student_id);
        req.setAttribute("apply",applySectionArrayList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/ApplyStatus.jsp");
        requestDispatcher.forward(req,resp);
    }
}
