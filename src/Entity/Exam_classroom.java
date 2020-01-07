package Entity;

public class Exam_classroom {
    private int exam_id;
    private int classroom_id;

    public Exam_classroom(int exam_id, int classroom_id) {
        this.exam_id = exam_id;
        this.classroom_id = classroom_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }
}
