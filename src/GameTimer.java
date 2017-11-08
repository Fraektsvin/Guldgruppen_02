
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameTimer {
    static int timeRemaining = 120;
    
    ArrayList<Swag> inventory = Game.inventory;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            timeRemaining--;
            
            if (timeRemaining <= 0) {
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
}