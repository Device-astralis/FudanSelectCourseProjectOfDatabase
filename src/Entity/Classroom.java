package Entity;

public class Classroom {
  private String classroom_id;
  private int classroom_caoacity;

    public Classroom(String classroom_id, int classroom_caoacity) {
        this.classroom_id = classroom_id;
        this.classroom_caoacity = classroom_caoacity;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(String classroom_id) {
        this.classroom_id = classroom_id;
    }

    public int getClassroom_caoacity() {
        return classroom_caoacity;
    }

    public void setClassroom_caoacity(int classroom_caoacity) {
        this.classroom_caoacity = classroom_caoacity;
    }
}
