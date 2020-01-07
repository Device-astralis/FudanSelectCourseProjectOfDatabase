package Entity;

public class Section {
    private String course_id;
    private int section_id;
    private int year;
    private int smester;
    private int section_capacity;
    private int serial_number;
    private String teacher_id;
    private int exam_id;
    private String classroom_id;

    public Section(String course_id, int section_id,
                   int year, int smester, int section_capacity,
                   int serial_number, String teacher_id,
                   int exam_id, String classroom_id) {
        this.course_id = course_id;
        this.section_id = section_id;
        this.year = year;
        this.smester = smester;
        this.section_capacity = section_capacity;
        this.serial_number = serial_number;
        this.teacher_id = teacher_id;
        this.exam_id = exam_id;
        this.classroom_id = classroom_id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSmester() {
        return smester;
    }

    public void setSmester(int smester) {
        this.smester = smester;
    }

    public int getSection_capacity() {
        return section_capacity;
    }

    public void setSection_capacity(int section_capacity) {
        this.section_capacity = section_capacity;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(String classroom_id) {
        this.classroom_id = classroom_id;
    }
}
