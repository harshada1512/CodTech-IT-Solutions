package com.mycompany.studentgradesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeSystem extends JFrame {
    private JTextField nameField, idField, mathField, scienceField, englishField, resultField;

    public StudentGradeSystem() {
        setTitle("Student Grade System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Math Grade:"));
        mathField = new JTextField();
        inputPanel.add(mathField);

        inputPanel.add(new JLabel("Science Grade:"));
        scienceField = new JTextField();
        inputPanel.add(scienceField);

        inputPanel.add(new JLabel("English Grade:"));
        englishField = new JTextField();
        inputPanel.add(englishField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        buttonPanel.add(calculateButton);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        resultPanel.add(new JLabel("Overall Grade:"));
        resultField = new JTextField(10);
        resultField.setEditable(false);
        resultPanel.add(resultField);

        add(resultPanel, BorderLayout.SOUTH);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String id = idField.getText();
            double mathGrade = Double.parseDouble(mathField.getText());
            double scienceGrade = Double.parseDouble(scienceField.getText());
            double englishGrade = Double.parseDouble(englishField.getText());

            double overallGrade = (mathGrade + scienceGrade + englishGrade) / 3;

            String result = String.format("%.2f", overallGrade);

            resultField.setText(result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeSystem::new);
    }
}
