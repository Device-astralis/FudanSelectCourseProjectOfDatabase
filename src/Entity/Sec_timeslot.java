package Entity;

public class Sec_timeslot {
    private int section_id;
    private int timeslot_id;

    public Sec_timeslot(int section_id, int timeslot_id) {
        this.section_id = section_id;
        this.timeslot_id = timeslot_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(int timeslot_id) {
        this.timeslot_id = timeslot_id;
    }
}
