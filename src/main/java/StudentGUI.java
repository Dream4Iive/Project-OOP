import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGUI extends JFrame {
    private JButton addButton, updateButton, deleteButton;
    private JLabel nameLabel, ageLabel, majorLabel;
    private JTextField nameField, ageField, majorField;
    private JTextArea displayArea;
    private JPanel inputPanel, buttonPanel;

    public StudentGUI() {
        setTitle("Student Database");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Inputs
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        majorLabel = new JLabel("Major:");
        nameField = new JTextField(10);
        ageField = new JTextField(10);
        majorField = new JTextField(10);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(majorLabel);
        inputPanel.add(majorField);

        //Buttons
        buttonPanel = new JPanel();
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        //Displays
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        displayStudents();
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();
                String major = majorField.getText();

                try {
                    int age = Integer.parseInt(ageText);
                    StudentDatabase.addStudent(name, age, major);
                    displayStudents();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentGUI.this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String ageText = ageField.getText();
                String major = majorField.getText();

                try {
                    int age = Integer.parseInt(ageText);
                    StudentDatabase.deleteStudent(name, age, major);
                    displayStudents();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentGUI.this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String ageText = ageField.getText();
                String major = majorField.getText();

                try {
                    int age = Integer.parseInt(ageText);

                    String newName = "NEW";
                    int newAge = 0;
                    String newMajor = "NEW";

                    StudentDatabase.updateStudent(name, age, major, newName, newAge, newMajor);
                    displayStudents();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentGUI.this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void displayStudents() {
        displayArea.setText("");
        String students = StudentDatabase.getAllStudents();
        displayArea.append(students);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGUI::new);
    }
}