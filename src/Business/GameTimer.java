package Business;

import Business.Timer.MyTimer;
import Business.Timer.MyTimerTask;
import java.io.Serializable;

public class GameTimer implements Serializable {

    private int timeRemaining = 120;

    MyTimer timer = new MyTimer();
    MyTimerTask task = new MyTimerTask() {
                @Override
        public void run() {
            timeRemaining--;
            if (getTimeRemaining() <= 0) {
                timer.cancel();
                System.exit(0);
            }
        }
    };

    public void timerStart() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void timerStop() {
        timer.cancel();
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void addTime(int time) {
        timeRemaining += time;
    }

    public void setTime(int savedTime) {
        timeRemaining = savedTime;
    }
}
