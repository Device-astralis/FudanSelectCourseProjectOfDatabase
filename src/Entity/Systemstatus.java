package Entity;

public class Systemstatus {
    private int year;
    private int semester;
    private int status;

    public Systemstatus(int year, int semester, int status) {
        this.year = year;
        this.semester = semester;
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
