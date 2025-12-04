package model;

public class StrengthWorkout extends Workout {
    private int sets;
    private int reps;
    private double weight;

    public StrengthWorkout() {
        this("None", 0, "None", 0, 0, 0.0);
    }

    public StrengthWorkout(String date, int duration, String notes, int sets, int reps, double weight) {
        super(date, duration, notes);
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }

    public void setSets(int sets) { this.sets = sets; }
    public void setReps(int reps) { this.reps = reps; }
    public void setWeight(double weight) { this.weight = weight; }

    public void setAll(String date, int duration, String notes, int sets, int reps, double weight) {
        super.setAll(date, duration, notes);
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "StrengthWorkout: " + getDate() + ", Duration: " + getDuration() +
                ", Notes: " + getNotes() + ", Sets: " + sets +
                ", Reps: " + reps + ", Weight: " + weight;
    }
}