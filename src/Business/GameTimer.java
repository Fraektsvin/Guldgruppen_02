package Business;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private int timeRemaining = 120;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
                @Override
        public void run() {
            timeRemaining--;
            if (getTimeRemaining() <= 0) {
                timer.cancel();
                System.out.println("\nTiden løb fra dig! Bedre held næste gang.");
                System.out.println("Tak fordi at du spillede med os, din stodder.");
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
