package Entity;

public class TimeSlot {
    private int timeslot_id;
    private int week;
    private int slot_start_time;
    private int slot_end_time;

    public TimeSlot(int timeslot_id, int week, int slot_start_time, int slot_end_time) {
        this.timeslot_id = timeslot_id;
        this.week = week;
        this.slot_start_time = slot_start_time;
        this.slot_end_time = slot_end_time;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(int timeslot_id) {
        this.timeslot_id = timeslot_id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSlot_start_time() {
        return slot_start_time;
    }

    public void setSlot_start_time(int slot_start_time) {
        this.slot_start_time = slot_start_time;
    }

    public int getSlot_end_time() {
        return slot_end_time;
    }

    public void setSlot_end_time(int slot_end_time) {
        this.slot_end_time = slot_end_time;
    }
}
