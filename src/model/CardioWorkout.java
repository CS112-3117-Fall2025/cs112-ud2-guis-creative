package model;

public class CardioWorkout extends Workout {
    private double distance;
    private double pace;

    public CardioWorkout() {
        this("None", 0, "None", 0.0, 0.0);
    }

    public CardioWorkout(String date, int duration, String notes, double distance, double pace) {
        super(date, duration, notes);
        this.distance = distance;
        this.pace = pace;
    }

    public double getDistance() { return distance; }
    public double getPace() { return pace; }

    public void setDistance(double distance) { this.distance = distance; }
    public void setPace(double pace) { this.pace = pace; }

    public void setAll(String date, int duration, String notes, double distance, double pace) {
        super.setAll(date, duration, notes);
        this.distance = distance;
        this.pace = pace;
    }

    @Override
    public String toString() {
        return "CardioWorkout: " + getDate() + ", Duration: " + getDuration() +
                ", Notes: " + getNotes() + ", Distance: " + distance +
                ", Pace: " + pace;
    }
}