package Entity;

public class DropSection {
    private int student_id;
    private int course_id;
    private int section_id;

    public DropSection(int student_id, int course_id, int section_id) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.section_id = section_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }
}
