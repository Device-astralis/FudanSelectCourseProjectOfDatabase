package Entity;

public class SelectSection {
    private String student_id;
    private String course_id;
    private int section_id;

    public SelectSection(String student_id, String course_id, int section_id) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.section_id = section_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }
}
