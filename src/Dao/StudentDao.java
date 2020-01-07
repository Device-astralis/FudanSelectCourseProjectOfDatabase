package Dao;

import Entity.Student;
import Util.DBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {
    private static Student getStudentByRs(ResultSet resultSet) {
        Student newStudent = null;
        try {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
           String department = resultSet.getString(3);
            //     Date uploadingTime = new Date();
            newStudent = new Student(id, name,department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newStudent;

    }
}
