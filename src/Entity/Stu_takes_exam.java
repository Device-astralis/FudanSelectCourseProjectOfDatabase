package Entity;

public class Stu_takes_exam {
    private int student_id;
    private int exam_id;
    private int grade;

    public Stu_takes_exam(int student_id, int exam_id, int grade) {
        this.student_id = student_id;
        this.exam_id = exam_id;
        this.grade = grade;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
