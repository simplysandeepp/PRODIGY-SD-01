import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
 *                                      Task: 1 
 * 
 *                            <<-- Sandeep Prajapati -->>
 * 
 *                                    Description:
 
 *     This Java program provides a user-friendly GUI to convert temperatures between 
 *     Celsius, Fahrenheit, and Kelvin. The user enters a temperature value and selects 
 *     its unit, and the program displays the equivalent values in the other two units.
  
 *                                  Technologies Used:
 * 
 *     Java Swing (JFrame, JPanel, JTextField, JLabel, JComboBox, JButton):
 *     Used to build the graphical interface. JFrame creates the main window, 
 *     while other Swing components handle user input and display results.

 */


public class task1 {

    @SuppressWarnings("Convert2Lambda")
    public static void main(String[] args) {
        // Create the main frame for the GUI
        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window on screen
        frame.setLayout(new BorderLayout(0, 20));

        // Set a custom background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

        // Header Panel for the greeting
        JPanel headerPanel = new JPanel();
        JLabel greetingLabel = new JLabel("Welcome to the Temperature Converter!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        greetingLabel.setForeground(new Color(0, 102, 204)); // Blue color
        headerPanel.add(greetingLabel);
        headerPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        frame.add(headerPanel, BorderLayout.NORTH);

        // Center Panel for form input and conversion button
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical Box layout
        centerPanel.setBackground(new Color(240, 248, 255));

        // Input label and field
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(240, 248, 255));

        JLabel tempLabel = new JLabel("Enter temperature:");
        tempLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField tempField = new JTextField(15);
        tempField.setFont(new Font("Arial", Font.PLAIN, 16));
        tempField.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));

        inputPanel.add(tempLabel);
        inputPanel.add(tempField);
        centerPanel.add(inputPanel);

        // Unit selection combo box
        JPanel unitPanel = new JPanel(new FlowLayout());
        unitPanel.setBackground(new Color(240, 248, 255));

        JLabel unitLabel = new JLabel("Choose unit:");
        unitLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JComboBox<String> unitComboBox = new JComboBox<>(new String[] {"Celsius (C)", "Fahrenheit (F)", "Kelvin (K)"});
        unitComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        unitComboBox.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));

        unitPanel.add(unitLabel);
        unitPanel.add(unitComboBox);
        centerPanel.add(unitPanel);

        // Create a custom rounded border for the button
        Border roundedBorder = BorderFactory.createLineBorder(new Color(0, 102, 204), 2); // Border color
        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(new Color(0, 153, 255)); // Blue button color
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(10, 20, 10, 20))); // Rounded edges
        convertButton.setPreferredSize(new Dimension(150, 40));
        convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add a smooth hover effect to the button
        convertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                convertButton.setBackground(new Color(0, 122, 204)); // Darker blue on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                convertButton.setBackground(new Color(0, 153, 255)); // Reset to original color
            }
        });

        centerPanel.add(convertButton);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Output area
        JTextArea outputArea = new JTextArea(8, 30);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(new Color(255, 255, 255)); // White background for output
        outputArea.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        outputArea.setForeground(new Color(0, 102, 204)); // Blue text color
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Action Listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("ConvertToStringSwitch")
            public void actionPerformed(ActionEvent e) {
                try {
                    double temp = Double.parseDouble(tempField.getText());
                    String selectedUnit = unitComboBox.getSelectedItem().toString().substring(0, 1);
                    double celsius = 0;
                    StringBuilder result = new StringBuilder();

                    // Conversion logic
                    if (selectedUnit.equals("C")) {
                        celsius = temp;
                        double f = (temp * 9 / 5) + 32;
                        double k = temp + 273.15;
                        result.append(String.format("%.2f °C = %.2f °F\n", temp, f));
                        result.append(String.format("%.2f °C = %.2f K\n", temp, k));

                    } else if (selectedUnit.equals("F")) {
                        celsius = (temp - 32) * 5 / 9;
                        double k = celsius + 273.15;
                        result.append(String.format("%.2f °F = %.2f °C\n", temp, celsius));
                        result.append(String.format("%.2f °F = %.2f K\n", temp, k));

                    } else if (selectedUnit.equals("K")) {
                        if (temp < 0) {
                            result.append("⚠️ Invalid Kelvin value. Kelvin cannot be negative.");
                        } else {
                            celsius = temp - 273.15;
                            double f = (celsius * 9 / 5) + 32;
                            result.append(String.format("%.2f K = %.2f °C\n", temp, celsius));
                            result.append(String.format("%.2f K = %.2f °F\n", temp, f));
                        }
                    }

                    // Health suggestion based on body temperature
                    if (celsius < 36.5) {
                        result.append("Temperature is below normal.\n");
                        result.append("Please take rest or consult a doctor if needed.\n");
                        result.append("Stay warm and get well soon!");
                    } else if (celsius > 37.5) {
                        result.append(" Temperature is above normal.\n");
                        result.append("You have a fever. Kindly visit for Medical Checkup...\n");
                        result.append("Take care and get well soon!");
                    } else {
                        result.append("Your body temperature is in normal range. Stay healthy!");
                    }

                    outputArea.setText(result.toString());

                } catch (NumberFormatException ex) {
                    outputArea.setText("-> Invalid input! Please enter a valid temperature.");
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
