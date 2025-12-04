package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    public static void main(String[] args) {
        //Create frame
        JFrame frame = new JFrame("Workout Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        //Panel with grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        //Title
        JLabel title = new JLabel("Workout Tracker", SwingConstants.CENTER);
        panel.add(title);

        //Buttons
        JButton cardioButton = new JButton("Add Cardio Workout");
        JButton strengthButton = new JButton("Add Strength Workout");
        JButton viewButton = new JButton("View All Workouts");

        panel.add(cardioButton);
        panel.add(strengthButton);
        panel.add(viewButton);


        cardioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cardio workout added!");
            }
        });

        //Add panel
        frame.add(panel);
        frame.setVisible(true);
    }
}