package Entity;

public class Student {
private String student_id;
private String student_name;
private String department;

    public Student(String student_id, String student_name, String department) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.department = department;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
