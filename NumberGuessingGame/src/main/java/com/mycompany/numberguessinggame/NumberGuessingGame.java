package com.mycompany.numberguessinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberGuessingGame extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton guessButton, playAgainButton;
    private JLabel resultLabel;
    private int randomNumber;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Generate a random number between 1 and 100
        randomNumber = (int) (Math.random() * 100) + 1;

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        textField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");

        add(instructionLabel);
        add(textField);
        add(guessButton);
        add(resultLabel);

        guessButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        int guess = Integer.parseInt(textField.getText());
        if (guess < randomNumber) {
            resultLabel.setText("Too low!");
            resultLabel.setForeground(Color.RED);
        } else if (guess > randomNumber) {
            resultLabel.setText("Too high!");
            resultLabel.setForeground(Color.RED);
        } else {
            resultLabel.setText("Congratulations! You guessed it right!");
            resultLabel.setForeground(Color.BLUE);
            guessButton.setEnabled(false);
            playAgainButton = new JButton("Play Again");
            add(playAgainButton);
            playAgainButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resetGame();
                }
            });
        }
    }

    private void resetGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        textField.setText("");
        resultLabel.setText("");
        guessButton.setEnabled(true);
        remove(playAgainButton);
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
