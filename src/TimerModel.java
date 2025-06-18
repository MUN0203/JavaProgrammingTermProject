public class TimerModel {
    private long initialDuration;
    private long remainingTime;
    private long lastTickTime;
    private boolean running;

    public TimerModel() {
        reset();
    }

    // 타이머가 카운트다운할 총 시간을 설정
    public void setDuration (long durationMillis) {
        this.initialDuration = durationMillis;  // 타이머의 '초기 설정 시간'을 저장
        this.remainingTime = durationMillis;    // '남은 시간'도 초기 설정 시간으로
        if (!running) {
            this.lastTickTime = System.currentTimeMillis();
        }
    }

    public void start() {
        if (!running && remainingTime > 0) {
            running = true;
            lastTickTime = System.currentTimeMillis();
        }
    }

    public void stop() {
        if (running) {
            running = false;
        }
    }

    public void reset() {
        running = false;
        remainingTime = initialDuration;
    }

    // javax.swing.Timer에 의해 주기적으로 호출 (TimerController에 의해 약 30ms마다)
    public void tick() {
        if (running && remainingTime > 0) {
            long now = System.currentTimeMillis();

            // 실제 경과 시간 계산: '현재 시간'에서 '마지막으로 tick이 호출됐던 시간(lastTickTime)'을 뺀다.
            long elapsedSinceLastTick = now - lastTickTime;

            // '마지막 호출 시간' 갱신: 다음 계산을 위해 '마지막 호출 시간'을 '현재 시간'으로 업데이트
            lastTickTime = now;

            // 남은 시간 차감: 전체 '남은 시간'에서 방금 계산한 '실제 경과 시간'을 뺀다.
            remainingTime -= elapsedSinceLastTick;
            if (remainingTime < 0) {
                remainingTime = 0;
            }
        }
        if (remainingTime <= 0) {
            running = false;
        }
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isFinished() {
        return remainingTime <= 0;
    }

    public long getInitialDuration() {
        return initialDuration;
    }
}