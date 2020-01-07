package Servlet;

import Dao.Dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          HttpSession session = req.getSession();
          String id = req.getParameter("id");
          String password = req.getParameter("password");
          String sql = "select * from account where id = ? and password = ?";
          boolean isRight = true;
          if(!id.equals("Root") && id.charAt(0) != 'S' && id.charAt(0) != 'T'){
              isRight = false;
          }else {
              try{
                  ResultSet resultSet = Dao.selectReturnSet(sql,id,password);
                  //System.out.println(resultSet.isClosed());
                  if(!resultSet.next()){
                      isRight = false;
                  }
                  Dao.close();
              }catch(SQLException ex){
                  ex.printStackTrace();
                  System.out.println("error");
                  isRight = false;
              }
          }
          if(isRight){
              sql = "select status from systemstatus ";
              int status;
              try{
                ResultSet resultSet1 = Dao.selectReturnSet(sql);
                resultSet1.next();
                status = resultSet1.getInt(1);
                session.setAttribute("status",status);
              }catch (SQLException ex){
                  ex.printStackTrace();
              }finally {
                  Dao.close();
              }
              char firstChar  = id.charAt(0);
              String name = "";
              int i=0;
              try {
                  if (firstChar == 'S') {
                      i = 1;
                      sql = "select student_name from student where student_id = ?";
                      ResultSet resultSet = Dao.selectReturnSet(sql, id);
                      resultSet.next();
                      name = resultSet.getString(1);
                      Dao.close();
                  } else if (firstChar == 'T') {
                      i = 2;
                      sql = "select teacher_name from teacher where teacher_id = ?";
                      ResultSet resultSet = Dao.selectReturnSet(sql, id);
                      resultSet.next();
                      name = resultSet.getString(1);
                      Dao.close();
                  } else if(id.equals("Root")){
                      i = 3;
                      name = "Root";
                  }
              }catch (SQLException ex){
                  ex.printStackTrace();
              }

              session.setAttribute("user_id",id);
              session.setAttribute("user_name",name);
              session.setAttribute("user_type",i);
              resp.sendRedirect("/JSP/index.jsp");
          }else{
              resp.sendRedirect("/JSP/Login.jsp?error=1");
          }
    }

    private static void writeResult(String type){
        ResultSet resultSet = null;
        String filename = "";
        File file;
        OutputStreamWriter writer;
        try{
            switch(type) {
                case "account":
                    resultSet = Dao.selectReturnSet("select * from account");
                    filename="./account.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "student":
                    resultSet = Dao.selectReturnSet("select * from student");
                    filename="./student.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "teacher":
                    resultSet = Dao.selectReturnSet("select * from teacher");
                    filename="./teacher.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "classroom":
                    resultSet = Dao.selectReturnSet("select * from classroom");
                    filename="./classroom.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getInt(2)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "course":
                    resultSet = Dao.selectReturnSet("select * from course");
                    filename="./course.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getInt(3)+","+resultSet.getString(4)+","+resultSet.getInt(5)+","+resultSet.getInt(6)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "section":
                    resultSet = Dao.selectReturnSet("select * from section");
                    filename="./section.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getInt(2)+","+resultSet.getInt(3)+","+resultSet.getInt(4)+","+resultSet.getInt(5)+","+resultSet.getInt(6)+","+resultSet.getString(7)+","+resultSet.getInt(8)+","+resultSet.getString(9)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "exam":
                    resultSet = Dao.selectReturnSet("select * from exam");
                    filename="./exam.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getInt(3)+","+resultSet.getString(4)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "timeslot":
                    resultSet = Dao.selectReturnSet("select * from timeslot");
                    filename="./timeslot.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getInt(1)+","+resultSet.getInt(2)+","+resultSet.getInt(3)+","+resultSet.getInt(4)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "selectsection":
                    resultSet = Dao.selectReturnSet("select * from selectsection");
                    filename="./selectsection.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getInt(3)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "dropsection":
                    resultSet = Dao.selectReturnSet("select * from dropsection");
                    filename="./dropsection.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getInt(3)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "applysection":
                    resultSet = Dao.selectReturnSet("select * from applysection");
                    filename="./applysection.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getInt(4)+","+resultSet.getInt(5)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
                    break;
                case "systemstatus":
                    resultSet = Dao.selectReturnSet("select * from systemstatus");
                    filename="./systemstatus.csv";
                    file = new File(filename);
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    while(resultSet.next()){
                        writer.write(resultSet.getInt(1)+","+resultSet.getInt(2)+","+resultSet.getInt(3)+"\n");
                    }
                    writer.flush();
                    writer.close();
                    Dao.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
