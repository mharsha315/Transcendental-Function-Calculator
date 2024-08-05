package com.example.f5;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WelcomePage class that creates a welcome GUI and the calculator GUI.
 */
public class WelcomePage {
    /** The width of the frame. */
    private static final int FRAME_WIDTH = 600;
    /** The height of the frame. */
    private static final int FRAME_HEIGHT = 300;
    /** The font size for labels. */
    private static final int FONT_SIZE = 18;
    /** The number of rows in the grid layout. */
    private static final int GRID_ROWS = 4;
    /** The number of columns in the grid layout. */
    private static final int GRID_COLUMNS = 2;
    /** The horizontal gap in the grid layout. */
    private static final int GRID_HGAP = 10;
    /** The vertical gap in the grid layout. */
    private static final int GRID_VGAP = 10;
    /** The maximum value for a and b. */
    private static final double MAX_A_B = 1e150;
    /** The minimum value for b. */
    private static final double MIN_B = -1000;
    /** The maximum value for b. */
    private static final double MAX_B = 1000;
    /** The minimum value for x. */
    private static final double MIN_X = -100;
    /** The maximum value for x. */
    private static final double MAX_X = 100;
    /** The magic number constant for seconds. */
    private static final double NANO_TO_SEC = 1_000_000_000.0;
    /** The magic number constant for microseconds. */
    private static final double NANO_TO_MICRO = 1_000.0;
    /** Font size for error labels. */
    private static final int ERROR_FONT_SIZE = 16;
    /** Regular expression for evaluating expressions. */
    private static final String REGEX_EXPRESSION = "(-?\\d+(\\.\\d+)?)([*/])(-?\\d+(\\.\\d+)?)";
    /** Error message for invalid number format. */
    private static final String INVALID_NUMBER_FORMAT = "Invalid number format.";
    /** Error message for value out of range. */
    private static final String VALUE_OUT_OF_RANGE = "Value out of range.";
    /** Error message for division by zero. */
    private static final String DIVISION_BY_ZERO = "Division by zero.";
    /** Regular expression to remove spaces. */
    private static final String REMOVE_SPACES_REGEX = "\\s";
    /** Replacement for removing spaces. */
    private static final String EMPTY_STRING = "";
    /** Group number for the first operand in the expression. */
    private static final int OPERAND1_GROUP = 1;
    /** Group number for the second operand in the expression. */
    private static final int OPERAND2_GROUP = 4;
    /** Group number for the operator in the expression. */
    private static final int OPERATOR_GROUP = 3;

    /**
     * Main method to run the WelcomePage.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        System.out.println("Eternity – Transcendental Function Calculator Version: " + Version.getVersion());
        SwingUtilities.invokeLater(() -> new WelcomePage().createAndShowGUI());
    }

    /**
     * Creates and shows the welcome GUI.
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame(
                "Welcome to Eternity – Transcendental Function Calculator."
        );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "<html><div style='text-align: center;'>"
                        + "Welcome to Transcendental Function Calculator.<br/>"
                        + "Version: " + Version.getVersion() + "</div></html>",
                JLabel.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        pane.add(welcomeLabel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();  // Close the welcome frame
                SwingUtilities.invokeLater(() -> createAndShowCalculatorGUI());  // Open the calculator GUI
            }
        });
        pane.add(continueButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Creates and shows the calculator GUI.
     */
    private void createAndShowCalculatorGUI() {
        JFrame frame = new JFrame("Function F5: ab^x.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(
                GRID_ROWS, GRID_COLUMNS,
                GRID_HGAP, GRID_VGAP));
        JPanel resultPanel = new JPanel(new BorderLayout());

        JTextField textFieldA = new JTextField();
        JLabel errorA = createErrorLabel();
        addDocumentListener(textFieldA, errorA, -MAX_A_B, MAX_A_B);
        JTextField textFieldB = new JTextField();
        JLabel errorB = createErrorLabel();
        addDocumentListener(textFieldB, errorB, MIN_B, MAX_B);
        JTextField textFieldX = new JTextField();
        JLabel errorX = createErrorLabel();
        addDocumentListener(textFieldX, errorX, MIN_X, MAX_X);

        JTextField resultField = new JTextField();
        resultField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        calculateButton.addActionListener(e -> calculateResult(
                textFieldA, textFieldB, textFieldX, resultField, frame));

        addComponentsToPane(inputPanel,
                "Enter value for a (limit: -10^150 to 10^150): ",
                textFieldA, errorA);
        addComponentsToPane(inputPanel,
                "Enter value for b (limit: -1000 to 1000): ",
                textFieldB, errorB);
        addComponentsToPane(inputPanel,
                "Enter value for x (limit: -100 to 100): ",
                textFieldX, errorX);
        inputPanel.add(calculateButton);
        inputPanel.add(new JLabel(""));

        resultPanel.add(new JLabel("Result: "), BorderLayout.WEST);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        pane.add(inputPanel, BorderLayout.CENTER);
        pane.add(resultPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Adds a document listener to validate input in real-time.
     * @param textField The text field to add the listener to.
     * @param errorLabel The error label to display errors.
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     */
    private void addDocumentListener(
            final JTextField textField, final JLabel errorLabel,
            final double min, final double max) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(final DocumentEvent e) {
                validateInput();
            }
            public void removeUpdate(final DocumentEvent e) {
                validateInput();
            }
            public void changedUpdate(final DocumentEvent e) {
                validateInput();
            }
            private void validateInput() {
                try {
                    double value = evaluateExpression(textField.getText());
                    if (value < min || value > max) {
                        errorLabel.setText(VALUE_OUT_OF_RANGE);
                    } else {
                        errorLabel.setText(EMPTY_STRING);
                    }
                } catch (Exception ex) {
                    errorLabel.setText(INVALID_NUMBER_FORMAT);
                }
            }
        });
    }

    /**
     * Evaluates a simple mathematical expression.
     * @param expression The expression to evaluate.
     * @return The evaluated result.
     * @throws Exception If the expression is invalid.
     */
    private double evaluateExpression(final String expression) throws Exception {
        String cleanedExpression = expression.replaceAll(REMOVE_SPACES_REGEX, EMPTY_STRING);
        Pattern pattern = Pattern.compile(REGEX_EXPRESSION);
        Matcher matcher = pattern.matcher(cleanedExpression);
        if (matcher.matches()) {
            double operand1 = Double.parseDouble(matcher.group(OPERAND1_GROUP));
            double operand2 = Double.parseDouble(matcher.group(OPERAND2_GROUP));
            String operator = matcher.group(OPERATOR_GROUP);
            switch (operator) {
                case "*":
                    return operand1 * operand2;
                case "/":
                    if (operand2 == 0) {
                        throw new Exception(DIVISION_BY_ZERO);
                    }
                    return operand1 / operand2;
                default:
                    throw new Exception(INVALID_NUMBER_FORMAT);
            }
        } else {
            return Double.parseDouble(cleanedExpression);
        }
    }

    /**
     * Calculates and displays the result.
     * @param textFieldA TextField for value a.
     * @param textFieldB TextField for value b.
     * @param textFieldX TextField for value x.
     * @param resultField TextField to display the result.
     * @param frame The parent frame for displaying error messages.
     */
    private void calculateResult(
            final JTextField textFieldA, final JTextField textFieldB,
            final JTextField textFieldX, final JTextField resultField,
            final JFrame frame) {
        try {
            double a = getValidInput(textFieldA, -MAX_A_B, MAX_A_B);
            double b = getValidInput(textFieldB, MIN_B, MAX_B);
            double x = getValidInput(textFieldX, MIN_X, MAX_X);
            long startTime = System.nanoTime();
            double result = calculateF5(a, b, x);
            long endTime = System.nanoTime();
            double durationInSeconds = (endTime - startTime) / NANO_TO_SEC;
            double durationInMicroseconds = (endTime - startTime) / NANO_TO_MICRO;

            resultField.setText(formatResult(result) + String.format(
                    " (Calculated in: %.6f seconds / %.3f microseconds)",
                    durationInSeconds, durationInMicroseconds));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input: "
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Creates an error label.
     * @return The error label.
     */
    private JLabel createErrorLabel() {
        JLabel label = new JLabel();
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.PLAIN, ERROR_FONT_SIZE));
        return label;
    }

    /**
     * Adds components to the pane.
     * @param pane The container pane.
     * @param labelText The label text.
     * @param textField The text field.
     * @param errorLabel The error label.
     */
    private void addComponentsToPane(
            final Container pane, final String labelText,
            final JTextField textField, final JLabel errorLabel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));

        pane.add(label);
        pane.add(textField);
        pane.add(errorLabel);
    }

    /**
     * Gets a valid input from the text field.
     * @param textField The text field.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The valid input.
     * @throws Exception If the input is invalid.
     */
    private double getValidInput(
            final JTextField textField, final double min, final double max)
            throws Exception {
        try {
            double value = evaluateExpression(textField.getText());
            if (value < min || value > max) {
                throw new Exception(VALUE_OUT_OF_RANGE);
            }
            return value;
        } catch (Exception ex) {
            throw new Exception(INVALID_NUMBER_FORMAT);
        }
    }

    /**
     * Calculates the value of the function F5: ab^x.
     * @param a The value of a.
     * @param b The value of b.
     * @param x The value of x.
     * @return The result of the function.
     */
    double calculateF5(
            final double a, final double b, final double x) {
        return a * Math.pow(b, x);
    }

    /**
     * Formats the result to a string.
     * @param result The result.
     * @return The formatted string.
     */
    private String formatResult(final double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.3f", result);
        }
    }
}
