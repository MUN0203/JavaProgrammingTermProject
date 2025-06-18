import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class TimerPanel extends JPanel {
    private JLabel displayLabel;
    private JTextField hourField, minField, secField;
    JButton startButton, stopButton, resetButton;

    public TimerPanel() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new LineBorder(Color.black),"타이머"));

        displayLabel = new JLabel("00:00:00.000");
        displayLabel.setHorizontalAlignment(JLabel.CENTER);
        displayLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        add(displayLabel, BorderLayout.NORTH);

        JPanel setTimerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        setTimerPanel.add(new JLabel("시"));
        hourField = new JTextField("0", 2);
        setTimerPanel.add(hourField);

        setTimerPanel.add(new JLabel("분"));
        minField = new JTextField("0", 2);
        setTimerPanel.add(minField);

        setTimerPanel.add(new JLabel("초"));
        secField = new JTextField("0", 2);
        setTimerPanel.add(secField);

        add(setTimerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    public void updateTime(String t) {
        displayLabel.setText(t);
    }

    // GUI의 텍스트 필드에 입력한 '시, 분, 초' 값을 읽는다.
    public long getSetDurationMillis() {
        try {
            int hours = Integer.parseInt(hourField.getText());
            int mins = Integer.parseInt(minField.getText());
            int secs = Integer.parseInt(secField.getText());
            if (hours < 0 || mins < 0 || mins >= 60 || secs < 0 || secs >= 60) {
                JOptionPane.showMessageDialog(this, "잘못된 시간 값입니다. (Invalid time value)", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            return (hours * 3600L + mins * 60L + secs) * 1000L;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "숫자만 입력해주세요. (Please enter numbers only)", "입력 오류", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void setInputFieldsEnabled(boolean enabled) {
        hourField.setEnabled(enabled);
        minField.setEnabled(enabled);
        secField.setEnabled(enabled);
    }
}
