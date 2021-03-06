package Entity;

public class Teacher {
    private String teacher_id;
    private String teacher_name;
    private String department;

    public Teacher(String teacher_id, String teacher_name, String department) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.department = department;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
