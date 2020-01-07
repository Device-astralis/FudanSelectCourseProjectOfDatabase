package Dao;

import Entity.Systemstatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemStatusDao {
    public static Systemstatus getSystemStatus(){
        Systemstatus systemstatus =null;
        String sql = "select * from systemstatus";
        try{
            ResultSet resultSet = Dao.selectReturnSet(sql);
            resultSet.next();
            int year =resultSet.getInt(1);
            int semester = resultSet.getInt(2);
            int status = resultSet.getInt(3);
            systemstatus = new Systemstatus(year,semester,status);
            Dao.close();
        }catch (SQLException ex){

        }
        return systemstatus;
    }
    public static boolean checkStatus(){
        int status=0;
        try{
            ResultSet resultSet = Dao.selectReturnSet("select status from systemstatus");
            if(resultSet.next())
                status = resultSet.getInt(1);
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return status == 1;
    }
}
