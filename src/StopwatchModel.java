
public class StopwatchModel {
    private long startTime;
    private long elapsed; // 경과 시간
    private boolean running;
    
    public StopwatchModel() {
        reset();
    }

    public void start() {
        if(!running) { 
            running = true;
            startTime = System.currentTimeMillis() - elapsed;
        }
    }

    public void stop() {
        if(running) {
            running = false;
            // stop 버튼을 누른 시점까지의 경과 시간을 계산하여 elapsed 변수에 저장
            elapsed = System.currentTimeMillis() - startTime;
        }
    }

    public void reset() {
        running = false;
        startTime = 0;
        elapsed = 0;
    }

    public long getElapsed() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return elapsed;
    }

    public boolean isRunning() {
        return running;
    }
}
