package Servlet;

import Dao.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.io.*;

@WebServlet("/InputGrade")
public class InputGradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String teacher_id = (String) session.getAttribute("user_id");
        int section_id=0;
        String course_id="";
        String error = "";
        String errorData = "";

        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        req.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> list = upload.parseRequest(req);
            String content = list.get(0).getString("UTF-8");
            content = content.replaceAll("\r","");
            String fileName = list.get(0).getName();
            String course_id_and_section_id = fileName.substring(0,fileName.lastIndexOf('.'));
            section_id=Integer.parseInt(course_id_and_section_id.substring(course_id_and_section_id.length()-7));
            course_id = course_id_and_section_id.substring(0,course_id_and_section_id.length()-7);
            arrayList = stringSplit(content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(SystemStatusDao.checkStatus()){
            error="目前仍处于选课阶段，不能进行成绩导入。";
        } else if(!checkFileName(course_id,section_id,teacher_id)){
            error="文件名称存在问题。请检查格式以及名称是否正确。";
        }else if(arrayList.size() == 0)
            error="文件是空的。";
        else if(!checkContent(arrayList))
            error="文件内容存在问题。";
        else
            errorData = insertGrade(arrayList,course_id,section_id);
        req.setAttribute("error",error);
        req.setAttribute("errorData",errorData);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/JSP/inputGrade.jsp");
        requestDispatcher.forward(req,response);

    }

    private boolean checkFileName(String course_id,int section_id,String teacher_id){
        try{
            ResultSet resultSet = Dao.selectReturnSet("select * from section where course_id=? and section_id=? and teacher_id=? and year=2019 and semester=1",course_id,section_id,teacher_id);
            boolean result = resultSet.next();
            Dao.close();
            return result;
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return false;
    }

    private boolean checkContent(ArrayList<ArrayList<String>> arrayList){
        for (ArrayList<String> a:arrayList
        ) {
            if(a.size() != 2)
                return false;
            if(a.get(0).isEmpty() || a.get(0).length() == 0 || a.get(1).isEmpty() || a.get(1).length() == 0)
                return false;
            if(!isNumeric(a.get(1)))
                return false;
        }
        return true;
    }

    private String insertGrade(ArrayList<ArrayList<String>> arrayList,String course_id,int section_id){
        StringBuilder error = new StringBuilder();
        int exam_id = 0;
        ArrayList<String> student_id_list = new ArrayList<>();
        try{
            ResultSet resultSet = Dao.selectReturnSet("select student_id from section,exam natural join stu_takes_exam where section.exam_id=exam.exam_id and course_id=? and section_id=?",course_id,section_id);
            ResultSet resultSet1 = Dao.selectReturnSet("select exam_id from section where course_id=? and section_id=?",course_id,section_id);
            if(resultSet1.next())
                exam_id = resultSet1.getInt(1);
            while (resultSet.next()){
                student_id_list.add(resultSet.getString(1));
            }
            for (ArrayList<String> a:arrayList
                 ) {
                String student_id = a.get(0);
                int grade = Integer.parseInt(a.get(1));
                if(student_id_list.contains(student_id) && grade > 0 && grade < 5){
                    ResultSet resultSet2 = Dao.selectReturnSet("select grade from stu_takes_exam where student_id=? and exam_id=?",student_id,exam_id);
                    if(resultSet2.next() && resultSet2.getInt(1) == -1){
                        Dao.update("update stu_takes_exam set grade=? where student_id=? and exam_id=?",grade,student_id,exam_id);
                    }else {
                        String wrong = "行："+a.get(0)+","+a.get(1)+" 插入失败<br>";
                        error.append(wrong);
                    }
                }else{
                    String wrong = "行："+a.get(0)+","+a.get(1)+" 插入失败<br>";
                    error.append(wrong);
                }
            }
            Dao.close();
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return error.toString();
    }

    private ArrayList<ArrayList<String>> stringSplit(String content){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        String[] array = content.split("\n");
        for (String string:array
             ) {
            ArrayList<String> tmp = new ArrayList<>();
            String[] splitResult = string.split(",");
            Collections.addAll(tmp,splitResult);
            result.add(tmp);
        }
        return result;
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
