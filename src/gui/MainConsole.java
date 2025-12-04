package gui;

import model.CardioWorkout;
import model.StrengthWorkout;
import model.User;

import java.util.Scanner;

public class MainConsole {

        private User user;
        private Scanner sc;

        public MainConsole() {
            sc = new Scanner(System.in);
            run();
        }

        private void run() {
            System.out.println("Welcome to Workout Tracker!");

            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter your age: ");
            int age = Integer.parseInt(sc.nextLine());

            user = new User(name, age);

            boolean running = true;
            while (running) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Cardio Workout");
                System.out.println("2. Add Strength Workout");
                System.out.println("3. View All Workouts");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addCardio();
                    case 2 -> addStrength();
                    case 3 -> viewWorkouts();
                    case 4 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        }

        private void addCardio() {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("Enter duration (minutes): ");
            int duration = Integer.parseInt(sc.nextLine());
            System.out.print("Enter notes: ");
            String notes = sc.nextLine();
            System.out.print("Enter distance (miles): ");
            double distance = Double.parseDouble(sc.nextLine());
            System.out.print("Enter pace (minutes/mile): ");
            double pace = Double.parseDouble(sc.nextLine());

            CardioWorkout cw = new CardioWorkout(date, duration, notes, distance, pace);
            user.addWorkout(cw);
            System.out.println("Cardio workout added!");
        }

        private void addStrength() {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("Enter duration (minutes): ");
            int duration = Integer.parseInt(sc.nextLine());
            System.out.print("Enter notes: ");
            String notes = sc.nextLine();
            System.out.print("Enter sets: ");
            int sets = Integer.parseInt(sc.nextLine());
            System.out.print("Enter reps: ");
            int reps = Integer.parseInt(sc.nextLine());
            System.out.print("Enter weight (lbs): ");
            double weight = Double.parseDouble(sc.nextLine());

            StrengthWorkout sw = new StrengthWorkout(date, duration, notes, sets, reps, weight);
            user.addWorkout(sw);
            System.out.println("Strength workout added!");
        }

        private void viewWorkouts() {
            System.out.println("\n--- All Workouts ---");
            System.out.println(user);
        }

        public static void main(String[] args) {
            new gui.MainConsole();
        }

}
