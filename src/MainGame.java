
import java.io.FileNotFoundException;


public class MainGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        GameTimer gameTimer = new GameTimer();
        gameTimer.timerStart();
        Player n = new Player("Erik Deluxe");
        n.displayName();
        Game swag_city = new Game(n);
        swag_city.play();
    }
      
}
