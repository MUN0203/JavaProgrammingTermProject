import javax.swing.*;

public class StopwatchController {
    private StopwatchModel model;
    private StopwatchPanel view;
    private Timer swingTimer;

    public StopwatchController(StopwatchModel model, StopwatchPanel view) {
        this.model = model;
        this.view = view;
        
        // 버튼 누르면 이 클래스의 메서드 호출
        view.startButton.addActionListener(e -> start());
        view.stopButton.addActionListener(e -> stop());
        view.resetButton.addActionListener(e -> reset());
        view.recordButton.addActionListener(e -> record());

        // 30밀리초 단위로 람다 블록 반복
        swingTimer = new Timer(30, e -> {
            if(model.isRunning()) {
                view.updateTime(TimeFormatter.format(model.getElapsed()));
            }
        });
    }


    public void start() {
        model.start();
        swingTimer.start();
        view.startButton.setEnabled(false);
        view.stopButton.setEnabled(true);
        view.resetButton.setEnabled(true);
        view.recordButton.setEnabled(true);
    }

    public void stop() {
        model.stop();
        swingTimer.stop();
        view.updateTime(TimeFormatter.format(model.getElapsed())); 
        view.startButton.setEnabled(true);
        view.stopButton.setEnabled(false);
        view.resetButton.setEnabled(true);
        view.recordButton.setEnabled(false);
    }

    public void reset() {
        model.reset();
        swingTimer.stop();
        view.updateTime(TimeFormatter.format(0));
        view.clearText();
        view.startButton.setEnabled(true);
        view.stopButton.setEnabled(false);
        view.resetButton.setEnabled(false);
        view.recordButton.setEnabled(false);
    }

    public void record() {
        if (model.isRunning()) {
            // 모델에서 현재 경과 시간을 가져와 포맷팅한 후, 화면의 기록 창에 새로운 기록을 추가하도록 view에 지시
            view.addRecord(TimeFormatter.format(model.getElapsed()));
        }
    }
}