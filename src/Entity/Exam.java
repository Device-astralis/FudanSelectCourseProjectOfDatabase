package Entity;

public class Exam {
    private int exam_id;
    private String format;
    private int timeslot_id;
    private String classroom_id;

    public Exam(int exam_id, String format, int timeslot_id, String classroom_id) {
        this.exam_id = exam_id;
        this.format = format;
        this.timeslot_id = timeslot_id;
        this.classroom_id = classroom_id;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(String classroom_id) {
        this.classroom_id = classroom_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(int timeslot_id) {
        this.timeslot_id = timeslot_id;
    }
}