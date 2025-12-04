package model;

import java.util.ArrayList;

public class User {
    private String name;
    private int age;
    private ArrayList<Workout> workouts;

    public User() {
        this("None", 0);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.workouts = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public ArrayList<Workout> getWorkouts() { return workouts; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public void addWorkout(Workout w) {
        workouts.add(w);
    }

    @Override
    public String toString() {
        String result = "User: " + name + " (Age " + age + ")\nWorkouts:\n";
        for (Workout w : workouts) {
            result += " - " + w.toString() + "\n";
        }
        return result;
    }
}