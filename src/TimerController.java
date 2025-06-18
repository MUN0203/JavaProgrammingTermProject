import javax.swing.*;

class TimerController {
    private TimerModel model;
    private TimerPanel view;
    private Timer swingTimer;

    public TimerController(TimerModel model, TimerPanel view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        // 버튼 누르면 이 클래스의 메서드 호출
        view.startButton.addActionListener(e -> start());
        view.stopButton.addActionListener(e -> stop());
        view.resetButton.addActionListener(e -> reset());

        // 30밀리초 단위로 람다 블록 반복
        swingTimer = new Timer(30, e -> {
            if (model.isRunning()) {
                model.tick(); // 모델에게 시간이 흘렀음을 알려 내부적으로 남은 시간을 줄이도록 한다.
                view.updateTime(TimeFormatter.format(model.getRemainingTime()));
                if (model.isFinished()) {
                    finish();
                }
            }
        });
        
        // 뷰의 시간 입력 필드에서 사용자가 설정한 시간을 가져온다.
        long initialDuration = view.getSetDurationMillis();
        if (initialDuration >= 0) {
            model.setDuration(initialDuration);
            view.updateTime(TimeFormatter.format(model.getRemainingTime()));
        } else {
             view.updateTime(TimeFormatter.format(0));
        }
    }

    public void start() {
        if (!model.isRunning()) {
            // 뷰의 입력 필드에서 시간을 가져와 모델의 타이머 시간을 설정
            if (model.getRemainingTime() == model.getInitialDuration() || model.getInitialDuration() == 0) {
                 long durationMillis = view.getSetDurationMillis();
                 if (durationMillis < 0) return;
                 if (durationMillis == 0) {
                     JOptionPane.showMessageDialog(view, "0초 타이머는 시작할 수 없습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
                     return;
                 }
                 model.setDuration(durationMillis);
            }
            
            if(model.getRemainingTime() <= 0) {
                 JOptionPane.showMessageDialog(view, "타이머가 이미 0입니다. 리셋 후 다시 설정해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
                 return;
            }

            model.start(); // 타이머 시작
            swingTimer.start();
            view.startButton.setEnabled(false);
            view.stopButton.setEnabled(true);
            view.resetButton.setEnabled(true);
            view.setInputFieldsEnabled(false);
        }
    }

     public void stop() {
        model.stop();
        swingTimer.stop();
        view.startButton.setEnabled(true);
        view.stopButton.setEnabled(false);
        view.setInputFieldsEnabled(true);
    }

    public void reset() {
        model.stop();
        swingTimer.stop();
        // view의 입력 필드에 있는 값으로 모델의 시간을 다시 설정
        long durationFromFields = view.getSetDurationMillis();
        if (durationFromFields >= 0) {
            model.setDuration(durationFromFields);
        } else {
            model.setDuration(model.getInitialDuration());
        }
        model.reset();
        view.updateTime(TimeFormatter.format(model.getRemainingTime()));

        view.startButton.setEnabled(true);
        view.stopButton.setEnabled(false);
        view.resetButton.setEnabled(model.getInitialDuration() > 0);
        view.setInputFieldsEnabled(true);
    }

    // swingTimer에 의해 남은 시간이 0이 되었을 때 자동으로 호출
    private void finish() {
        swingTimer.stop();
        model.stop();
        view.updateTime(TimeFormatter.format(0)); // 화면 시간을 정확히 0으로 표시
        JOptionPane.showMessageDialog(view, "타이머 종료! (Timer Finished!)", "알림 (Alert)", JOptionPane.INFORMATION_MESSAGE);
        
        view.startButton.setEnabled(true); 
        view.stopButton.setEnabled(false);
        view.resetButton.setEnabled(true);
        view.setInputFieldsEnabled(true);
    }
}