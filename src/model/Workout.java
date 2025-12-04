package model;

public abstract class Workout {
    private String date;
    private int duration;
    private String notes;

    public Workout() {
        this("None", 0, "None");
    }

    public Workout(String date, int duration, String notes) {
        this.date = date;
        this.duration = duration;
        this.notes = notes;
    }

    public String getDate() { return date; }
    public int getDuration() { return duration; }
    public String getNotes() { return notes; }

    public void setDate(String date) { this.date = date; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setNotes(String notes) { this.notes = notes; }

    public void setAll(String date, int duration, String notes) {
        this.date = date;
        this.duration = duration;
        this.notes = notes;
    }

    public boolean equals(Workout other) {
        return this.date.equals(other.date)
                && this.duration == other.duration
                && this.notes.equals(other.notes);
    }

    public abstract String toString();
}