import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class StopwatchPanel extends JPanel {
    private JLabel displayLabel;
    JButton startButton, stopButton, resetButton, recordButton;
    JTextArea recordArea;

    public StopwatchPanel() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new LineBorder(Color.black),"스톱워치"));

        displayLabel = new JLabel("00:00:00.000");
        displayLabel.setHorizontalAlignment(JLabel.CENTER);
        displayLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        add(displayLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");
        recordButton = new JButton("Record");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(recordButton);
        add(buttonPanel, BorderLayout.CENTER);

        recordArea = new JTextArea(5, 20);
        recordArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recordArea);
        add(scrollPane, BorderLayout.SOUTH);

        stopButton.setEnabled(false);
        recordButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    public void updateTime(String t) {
        displayLabel.setText(t);
    }

    public void addRecord(String record) {
        recordArea.append(record + "\n");
    }

    public void clearText() {
        recordArea.setText("");
    }
}
