package Servlet;

import Dao.*;
import Dao.StudentDao;
import Entity.Student;
import Entity.Systemstatus;
import Util.DBconnect;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CORBA.ARG_IN;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@WebServlet("/addDataFromManagerServlet")
public class AddDataFromExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //手动添加
//        doPost(req,resp);
        String type = req.getParameter("type");
        String[] outputs = req.getParameter("output").split(":");
        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        ArrayList<String> aline = new ArrayList<>();
        for(int i=0;i<outputs.length;i++){
            aline.add(outputs[i]);
        }
        datas.add(aline);
        ArrayList<ArrayList<String>> errorDatas = addData(datas,type);
        req.setAttribute("errorData", errorDatas);
        req.setAttribute("type",type);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/managerShow");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自动添加
        //判断管理员添加的是哪个表中的数据
        String type = req.getParameter("type");
//         String path = req.getParameter("path");
//         ArrayList<ArrayList<String>> inputdata = (ArrayList<ArrayList<String>>) req.getAttribute("data");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
//        PrintWriter out = resp.getWriter();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        String dataString = "";
//        ExcelHelper helper = new ExcelHelper();
        String title="";
        try {
            List<FileItem> list = upload.parseRequest(req);
            for (int i = 0; i < list.size(); i++) {
                FileItem fileItem = list.get(i);
//                dataString =  item.getString();
//               String title =  new String((fileItem.getString("iso8859-1")).getBytes("iso8859-1"),"gbk");
                title = fileItem.getString("UTF-8");
//                System.out.println(title);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        String temp = title.replaceAll("\r","");
       String[] lines = temp.split("\n");
        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        for(int i=0;i<lines.length;i++){
            ArrayList<String> alineData = new ArrayList<>();
            String aline = lines[i];
            String[] linedata =aline.split(",");
            Collections.addAll(alineData,linedata);
            datas.add(alineData);
        }
        ArrayList<ArrayList<String>> errorDatas = addData(datas,type);
//        if(!dataString.equals("")){
////           String[]
//        }
        req.setAttribute("errorData", errorDatas);
        req.setAttribute("test",title);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/managerShow?type="+type);
        requestDispatcher.forward(req,resp);
    }
    public ArrayList<ArrayList<String>> addData(ArrayList<ArrayList<String>> datas,String type){
       if(datas != null) {
           ArrayList<ArrayList<String>> errorDatas = new ArrayList<>();
           switch (type) {
               case "systemstatus":
                   errorDatas = changeStatus(datas);
                   break;
               case "student":
                   //添加学生
                   errorDatas = addStudent(datas);
                   break;
               case "teacher":
                   errorDatas = addTeacher(datas);
                   break;
               case "classroom":
                   errorDatas = addClassroom(datas);
                   break;
               case "course":
                   errorDatas = addCourse(datas);
                   break;
               case "section":
                   errorDatas = addSection(datas);
                   break;
               case "exam":
                   errorDatas = addExam(datas);
                   break;
               case "timeslot":
                   errorDatas = addTimeSlot(datas);
                   break;
               case "selectsection":
                   errorDatas = addSelectsection(datas);
                   break;
               case "applysection":
                   errorDatas = addApplysection(datas);
                   break;
           }
           return errorDatas;
       }
       return null;
    }

    private ArrayList<ArrayList<String>> changeStatus(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errordatas = new ArrayList<>();
        ArrayList<String> aline = datas.get(0);
        Connection connection = null;
        try {
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            String sql = "delete from systemstatus";
            Dao.updateByConnection(sql,connection);
            sql = "insert into systemstatus(year,semester,status) values (?,?,?)";
            Dao.updateByConnection(sql,connection, aline.get(0),aline.get(1),aline.get(2));
            connection.commit();
        }catch (SQLException ex){
            //冲突
            try{
                if(connection != null)
                    connection.rollback();
            }catch (SQLException eq){
                eq.printStackTrace();
            }
            errordatas.add(aline);
            return errordatas;
        }finally {
            try{
                if(connection!=null){
                    connection.close();
                }
            }catch (SQLException eq){
                eq.printStackTrace();
            }
        }
        return errordatas;
    }
    private ArrayList<ArrayList<String>> getExcelData(String path){
        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
            for(int i=0;i<rows;i++){
                ArrayList<String> rowDatas = new ArrayList<>();
                for(int j =0;j<cols;j++){
                    rowDatas.add(sheet.getCell(j,i).getContents());
                }
                datas.add(rowDatas);
            }
            return datas;
        }catch(Exception ex){
            //读取出现异常，路径名异常等
            ex.printStackTrace();
        }
        return null;
    }
    private ArrayList<ArrayList<String>> addStudent(ArrayList<ArrayList<String>> datas){
        //返回值为插入过程中出现冲突的数据，之后要将这些数据返回给界面告知冲突
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata: datas){
            String id = rowdata.get(0);
            String name = rowdata.get(1);
            String department =rowdata.get(2);

            String num = id.substring(1);
            String identity = id.charAt(0)+"";
            if(!(identity.equals("S")&&isNumericzidai(num)&&!isNumericzidai(name)&&!isNumericzidai(department))){
                //id首字母是S，并且其余的是数字，并且名字,院系不能是数字
                errorDatas.add(rowdata);
                continue;
            }
            boolean success = studentTransaction(id,name,department);
            if(!success){
                errorDatas.add(rowdata);
            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addTeacher(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata:datas){
            String id = rowdata.get(0);
            String name = rowdata.get(1);
            String department =rowdata.get(2);

            String num = id.substring(1);
            String identity = id.charAt(0)+"";
            if(!(identity.equals("T")&&isNumericzidai(num)&&!isNumericzidai(name)&&!isNumericzidai(department))){
                //id首字母是S，并且其余的是数字，并且名字,院系不能是数字
                errorDatas.add(rowdata);
                continue;
            }
            boolean success = teacherTransaction(id,name,department);
            if(!success){
                errorDatas.add(rowdata);
            }
//            String sql = "insert into teacher ( teacher_id,teacher_name,department) values (?,?,?)";
//            try {
//                Dao.update(sql, id, name, department);
//            }catch (SQLException ex){
//                //冲突
//                errorDatas.add(rowdata);
//                continue;
//            }
//            sql = "insert into account(id,password) values (?,000000)";
//            try {
//                Dao.update(sql, id);
//            }catch (SQLException ex){
//                //插入出现异常，有冲突
//                errorDatas.add(rowdata);
//            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addClassroom(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata:datas){
            String id = rowdata.get(0);
            String capacity = rowdata.get(1);

            if(!(isNumericzidai(capacity)&&Integer.parseInt(capacity)>0)){
                //容量为数字,>0
                errorDatas.add(rowdata);
                continue;
            }
            String sql = "insert into classroom ( classroom_id,class_capacity) values (?,?)";
            try {
                Dao.update(sql, id, Integer.parseInt(capacity));
            }catch(SQLException ex){
                errorDatas.add(rowdata);
            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addCourse(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata:datas){
            try {
            String sql = "select course_id from course where course_id = ? and course_status =0";
            ResultSet resultSet =  Dao.selectReturnSet(sql,rowdata.get(0));
            boolean exist = resultSet.next();
            Dao.close();
            if(exist){
                sql = "update course set course_status = 1 where course_id = ?";
                Dao.update(sql,rowdata.get(0));
                continue;
            }

            sql = "insert into course ( course_id,course_name,credit,department,period,course_status) values (?,?,?,?,?,?)";

                Dao.update(sql, rowdata.get(0), rowdata.get(1),rowdata.get(2),rowdata.get(3),rowdata.get(4),1);
            }catch (SQLException ex){
                errorDatas.add(rowdata);
            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addSection(ArrayList<ArrayList<String>> datas){
        //分别应判断教师是否冲突，上课教室是否冲突，考试教室是否冲突
        //datas应当有 course_id,section_id,year,semester,capacity,serial_number,teacher_name,exam_id,classroom_id,timeslot_id
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        ab: for(ArrayList<String> rowdata:datas){
            String course_id = rowdata.get(0);
            String section_id = rowdata.get(1);
            String year = rowdata.get(2);
            String semester = rowdata.get(3);
            String capacity = rowdata.get(4);
            String serial_number = rowdata.get(5);
            String teacher_name = rowdata.get(6);
//            String day = rowdata.get(7);

//            String[] timeslot = rowdata.get(8).split("-");
//            String startTime = timeslot[0];
//            String endTime = timeslot[1];
            String exam_id = rowdata.get(7);
            String classroom_id = rowdata.get(8);
            String[] timeslot_ids = rowdata.get(9).split(",");
            String teacher_id = "";
            String sql;
            try{
                //似乎数据库具有对外键的自行监测能力，所以可能也就不需要在此处对外键的存在性进行检测
                sql = "select * from course where course_id = ? and course_status = 1";
                //判断是否有这个课程
                ResultSet resultSet = Dao.selectReturnSet(sql,course_id);
                if(!resultSet.next()){
                    errorDatas.add(rowdata);
                    continue;
                }
                Dao.close();
                sql = "select teacher_id from teacher where teacher_name = ?";
                //判断是否有这个教师
                resultSet = Dao.selectReturnSet(sql,teacher_name);
                if(!resultSet.next()){
                    errorDatas.add(rowdata);
                    continue;
                }else{
                    teacher_id = resultSet.getString(1);
                }
                Dao.close();
//                sql = "select timeslot_id from timeslot " +
//                        "where week = ? and slot_start_time = ? and slot_end_time = ? ";
//                //判断是否有这个时间点
//                resultSet = Dao.selectReturnSet(sql,day,startTime,endTime);
//                if(!resultSet.next()){
//                    errorDatas.add(rowdata);
//                    continue;
//                }else{
//                    timeslot_id = resultSet.getString(0);
//                }
//                Dao.close();
                for(String timeslot_id:timeslot_ids) {
                    //判断教师是否冲突
                    if (isConflictInTeacher(teacher_id, timeslot_id, year, semester)) {
                        errorDatas.add(rowdata);
                        continue ab;
                    }
                    //判断上课教室是否冲突
                    if (isConflictInSecClassroom(classroom_id, timeslot_id, year, semester)) {
                        errorDatas.add(rowdata);
                        continue ab;
                    }
                }
                //判断考试教室是否冲突
                if(isConflictInExamClassroom(classroom_id,exam_id,year,semester)){
                    errorDatas.add(rowdata);
                    continue;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            //合法插入
            //datas应当有 course_id,section_id,year,semester,capacity,serial_number,teacher_name,day,timeslot,exam_id,classroom_id
           boolean success = sectionTransaction(course_id,section_id,year,semester,capacity,serial_number,teacher_id,exam_id,classroom_id,timeslot_ids);
            if(!success){
                errorDatas.add(rowdata);
            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addExam(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata:datas){
            String sql = "insert into exam (exam_id,format,timeslot_id,classroom_id) values (?,?,?,?)";
            try {
                Dao.update(sql, rowdata.get(0), rowdata.get(1),rowdata.get(2),rowdata.get(3));
            }catch (SQLException ex){
                errorDatas.add(rowdata);
            }
        }

        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addTimeSlot(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String>rowdata:datas){
            String sql = "insert into timeslot (timeslot_id,week,slot_start_time,slot_end_time) values(?,?,?,?)";
            try{
                Dao.update(sql,rowdata.get(0),rowdata.get(1),rowdata.get(2),rowdata.get(3));
            }catch(SQLException ex){
                 errorDatas.add(rowdata);
            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addSelectsection(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata: datas){
            String student_id = rowdata.get(0);
            String course_id = rowdata.get(1);
            String section_id = rowdata.get(2);
            String error = realSelect(student_id,course_id,section_id);
            if(!error.equals("")){
                errorDatas.add(rowdata);
            }
        }
        return errorDatas;
    }
    private ArrayList<ArrayList<String>> addApplysection(ArrayList<ArrayList<String>> datas){
        ArrayList<ArrayList<String>> errorDatas =new ArrayList<>();
        for(ArrayList<String> rowdata: datas){
           String student_id = rowdata.get(0);
           String course_id = rowdata.get(1);
           String section_id = rowdata.get(2);
           String apply_reason = rowdata.get(3);
           String error =  applySection(student_id,course_id,section_id,apply_reason);
           if(!error.equals("")){
               errorDatas.add(rowdata);
           }
        }
        return errorDatas;
    }
    public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    private boolean isConflictInTeacher(String teacher_id,String timeslot_id,String year,String semester){
        //判断教师是否冲突

        try {
            //得到所有这个老师该年该学期上的课的课程
            String sql = "select course_id,section_id from section where teacher_id = ? and year = ? and semester = ?  ";
            ResultSet resultSet = Dao.selectReturnSet(sql, teacher_id,year,semester);
            ArrayList<String> course_ids = new ArrayList<>();
            ArrayList<Integer> section_ids = new ArrayList<>();
            while(resultSet.next()) {
                //判断每个课程的每个区间是否有冲突
                String course_id = resultSet.getString(1);
                int section_id = resultSet.getInt(2);
                course_ids.add(course_id);
                section_ids.add(section_id);
            }
            Dao.close();
            for(int i=0;i<course_ids.size();i++) {
                String course_id = course_ids.get(i);
                int section_id = section_ids.get(i);
                sql = "select timeslot_id from sec_timeslot " +
                        "where course_id = ? and section_id = ?";
                ResultSet resultSet2 = Dao.selectReturnSet(sql, course_id, section_id);
                while (resultSet2.next()) {
                    //判断一个课程的每个区间是否有冲突
                    int timeslot_id2 = resultSet2.getInt(1);
                    if (isTimeslotConfilct(timeslot_id, timeslot_id2 + "")) {
                        return true;
                    }
                }
                Dao.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    private boolean isConflictInSecClassroom(String classroom_id,String timeslot_id,String year,String semester){
        //判断教室是否冲突
        try {
            //得到所有这个教室上的课的课程
            String sql = "select course_id,section_id from section where classroom_id = ? and year = ? and semester = ? ";
            ResultSet resultSet = Dao.selectReturnSet(sql,classroom_id,year,semester);
            ArrayList<String> course_ids = new ArrayList<>();
            ArrayList<Integer> section_ids = new ArrayList<>();
            while(resultSet.next()) {
                //判断每个课程的每个区间是否有冲突
                String course_id = resultSet.getString(1);
                int section_id = resultSet.getInt(2);
                course_ids.add(course_id);
                section_ids.add(section_id);
            }
            Dao.close();
            for(int i=0;i<course_ids.size();i++) {
                String course_id = course_ids.get(i);
                int section_id = section_ids.get(i);
                sql = "select timeslot_id from sec_timeslot " +
                        "where course_id = ? and section_id = ?";
                ResultSet resultSet2 = Dao.selectReturnSet(sql, course_id, section_id);
                while (resultSet2.next()) {
                    //判断一个课程的每个区间是否有冲突
                    int timeslot_id2 = resultSet2.getInt(1);
                    if (isTimeslotConfilct(timeslot_id, timeslot_id2 + "")) {
                        return true;
                    }
                }
                Dao.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    private boolean isConflictInExamClassroom(String classroom_id,String exam_id,String year,String semester){
        //检测开课的考试时间内是否该教室有其他开课考试，并且考试教室人数应大于等于开课教室人数
        try{
            //考试教室人数应大于等于开课教室人数
            String sql = "select classroom_id from exam where exam_id = ?";
            ResultSet resultSet = Dao.selectReturnSet(sql,exam_id);
            String exam_classroom_id =  resultSet.getString(1);
            Dao.close();
            sql = "select classroom_capacity from classroom where classroom_id = ?";
            resultSet = Dao.selectReturnSet(sql,classroom_id);
            resultSet.next();
            int sec_capacity = resultSet.getInt(1);
            Dao.close();
            resultSet = Dao.selectReturnSet(sql,exam_classroom_id);
            resultSet.next();
            int exam_capacity = resultSet.getInt(1);
            Dao.close();
            if(sec_capacity>exam_capacity){
                return true;
            }

            //时间冲突
            ArrayList<Integer> exam_ids = new ArrayList<>();
            sql ="select distinct exam_id from section where year = ? and semester = ?";
            resultSet = Dao.selectReturnSet(sql,year,semester);
            while(resultSet.next()){
                exam_ids.add(resultSet.getInt(1));
            }
            Dao.close();
            sql = "select timeslot_id from exam where exam_id = ?";
            resultSet = Dao.selectReturnSet(sql,exam_id);
            resultSet.next();
            String timeslot_id1 = resultSet.getInt(1)+"";
            Dao.close();
            for(int another_exam_id:exam_ids){
                resultSet = Dao.selectReturnSet(sql,another_exam_id);
                resultSet.next();
                String timeslot_id2 = resultSet.getInt(1)+"";
                Dao.close();
                if(isTimeslotConfilct(timeslot_id1,timeslot_id2)){
                    return true;
                }
            }

        }catch(Exception ex){

           ex.printStackTrace();
        }finally {
            Dao.close();
        }
        return false;
    }
    private boolean isTimeslotConfilct(String timeslot_id1,String timeslot_id2){
        try {
            String sql = "select * from timeslot where timeslot_id = ?";
            ResultSet newSet = Dao.selectReturnSet(sql, timeslot_id1);
            newSet.next();
            int day1 = newSet.getInt(2);
            int starttime1 = newSet.getInt(3);
            int endtime1 = newSet.getInt(4);
            newSet = Dao.selectReturnSet(sql,timeslot_id2);
            int day2 = newSet.getInt(2);
            int starttime2 = newSet.getInt(3);
            int endtime2 = newSet.getInt(4);
            Dao.close();
            return day1 == day2 && !(starttime1 > endtime2 || starttime2 > endtime1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
    private boolean studentTransaction(String id,String name,String department){
        Connection connection = null;
        try {
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into student( student_id,student_name,department) values (?,?,?)";
            Dao.updateByConnection(sql,connection, id, name, department);
            sql = "insert into account(id,password) values (?,000000)";
            Dao.updateByConnection(sql,connection, id);
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
    private boolean teacherTransaction(String id,String name,String department){
        Connection connection = null;
        try {
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into teacher ( teacher_id,teacher_name,department) values (?,?,?)";
            Dao.updateByConnection(sql,connection, id, name, department);
            sql = "insert into account(id,password) values (?,000000)";
            Dao.updateByConnection(sql,connection, id);
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
    private boolean sectionTransaction(String course_id,String section_id,String year,String semester,String capacity,String serial_number,String teacher_id, String exam_id,String classroom_id,String[] timeslot_ids){
        Connection connection = null;
        try {
            connection = DBconnect.getConnection();
            connection.setAutoCommit(false);

              String sql = "insert into section ( course_id,section_id,year,semester," +
                        "section_capacity,serial_number,teacher_id,exam_id,classroom_id) values (?,?,?,?,?,?,?,?,?)";
                Dao.updateByConnection(sql,connection, course_id, section_id,year,semester,capacity,serial_number,teacher_id,exam_id,classroom_id);
                for(String timeslot_id:timeslot_ids) {
                    sql = "insert into sec_timeslot(course_id,section_id,timeslot_id) values(?,?,?)";
                    Dao.updateByConnection(sql, connection, course_id, section_id, timeslot_id);
                }
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
                    } else if (!ApplySectionDao.checkNumber(section_id, course_id)) {
                        update = false;
                        error = "该课程尚有余量。";
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
