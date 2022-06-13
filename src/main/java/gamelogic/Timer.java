package gamelogic;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Timer {



    private boolean running;

    TimerTask timerTask;
    public boolean isRunning() {
        return running;
    }
    public Timer(){
    }
    public void start(int time){
        new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                running = false;
            }
        }).start();
    }

}
