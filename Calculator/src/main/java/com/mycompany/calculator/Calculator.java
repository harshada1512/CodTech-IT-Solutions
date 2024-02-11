package com.mycompany.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;

    public Calculator() {
        setTitle("Creative Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] buttons = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "C", "0", "=", "/"};
        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(new ButtonClickListener());
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.CENTER);

        setSize(300, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("C")) {
                display.setText("");
            } else if (command.equals("=")) {
                String expression = display.getText();
                try {
                    String result = evaluateExpression(expression);
                    display.setText(result);
                } catch (ArithmeticException ex) {
                    display.setText("Error");
                }
            } else {
                display.setText(display.getText() + command);
            }
        }
    }

    private String evaluateExpression(String expression) {
        String[] tokens = expression.split("(?=[-+*/=])|(?<=[-+*/=])");
        if (tokens.length < 3)
            throw new IllegalArgumentException("Invalid expression");

        double operand1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double operand2 = Double.parseDouble(tokens[2]);

        switch (operator) {
            case "+":
                return String.valueOf(operand1 + operand2);
            case "-":
                return String.valueOf(operand1 - operand2);
            case "*":
                return String.valueOf(operand1 * operand2);
            case "/":
                if (operand2 == 0)
                    throw new ArithmeticException("Division by zero");
                return String.valueOf(operand1 / operand2);
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
