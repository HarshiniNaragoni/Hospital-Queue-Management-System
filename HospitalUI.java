package myproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HospitalUI extends JFrame {

    private PatientQueue queue = new PatientQueue();
    private int tokenCounter = 1;

    private JTextField nameField;
    private JTextArea displayArea;

    public HospitalUI() {
        setTitle("BVRITH Hospital Patient Queue System");
        setSize(450, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(230, 245, 255)); // background

        // ⭐ Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);
        Font normalFont = new Font("Segoe UI", Font.PLAIN, 14);

        // ⭐ Top Panel – Add Patient
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Add Patient", 0, 0, titleFont, new Color(30, 90, 150)
        ));
        topPanel.setBackground(new Color(220, 235, 250));

        JLabel nameLabel = new JLabel("Enter Patient Name:");
        nameLabel.setFont(normalFont);
        nameLabel.setForeground(new Color(20, 50, 100));

        nameField = new JTextField();
        nameField.setFont(normalFont);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(120, 150, 200), 2));

        JButton addButton = new JButton("Add Patient");
        addButton.setFont(normalFont);
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        JButton nextButton = new JButton("Call Next Patient");
        nextButton.setFont(normalFont);
        nextButton.setBackground(new Color(46, 139, 87));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);

        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        // ⭐ Center Text Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(normalFont);
        displayArea.setBackground(new Color(245, 250, 255));
        displayArea.setForeground(new Color(20, 60, 90));
        displayArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                "Current Queue", 0, 0, titleFont, new Color(20, 70, 130)
        ));

        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // ⭐ Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(230, 245, 255));
        bottomPanel.add(nextButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // ⭐ Action Listeners
        addButton.addActionListener(e -> addPatient());
        nextButton.addActionListener(e -> callNextPatient());

        updateDisplay();
    }

    private void addPatient() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a valid name.");
            return;
        }

        Patient p = new Patient(tokenCounter++, name);
        queue.enqueue(p);
        nameField.setText("");

        updateDisplay();
    }

    private void callNextPatient() {
        if (queue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients in the queue.");
            return;
        }

        Patient next = queue.dequeue();
        JOptionPane.showMessageDialog(this,
                "Next Patient:\nToken: " + next.getTokenNumber() +
                        "\nName: " + next.getName());

        updateDisplay();
    }

    private void updateDisplay() {
        displayArea.setText(queue.displayQueue());
    }
}
