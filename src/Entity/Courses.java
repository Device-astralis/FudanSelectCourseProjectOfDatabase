package Entity;

public class Courses {
    private String course_id;
    private String course_name;
    private int credit;
    private String department;
    private int period;
    private int course_status;

    public Courses(String course_id, String course_name, int credit, String department, int period) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.credit = credit;
        this.department = department;
        this.period = period;
        this.course_status = 1;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getCourse_status() {
        return course_status;
    }

    public void setCourse_status(int course_status) {
        this.course_status = course_status;
    }
}
