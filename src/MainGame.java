
import java.util.Scanner;


public class MainGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameTimer gameTimer = new GameTimer();
        gameTimer.timerStart();
        player n = new player("Erik Deluxe");
        n.displayName();
        Game swag_city = new Game();
        swag_city.play();
    }
      
}