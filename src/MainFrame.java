import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private StopwatchPanel stopwatchPanel;
    private TimerPanel timerPanel;
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        setTitle("Stopwatch & Timer");
        setLayout(new GridLayout(1, 2));

        // 스톱워치
        StopwatchModel swModel = new StopwatchModel();
        stopwatchPanel = new StopwatchPanel();
        new StopwatchController(swModel, stopwatchPanel);
        add(stopwatchPanel);

        // 타이머
        TimerModel timerModel = new TimerModel();
        timerPanel = new TimerPanel();
        new TimerController(timerModel, timerPanel);
        add(timerPanel);

        pack();
        setMinimumSize(new Dimension(600, 400)); 
        setLocationRelativeTo(null); // 창 가운데
        setVisible(true);
    }
}
