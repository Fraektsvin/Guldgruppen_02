
import java.io.FileNotFoundException;

public class MainGame {

    public static void main(String[] args) throws FileNotFoundException {
        GameTimer gameTimer = new GameTimer();
        gameTimer.timerStart();
        Player n = new Player("Erik Deluxe");
        n.displayName();
        HighscoresManager saver = new HighscoresManager();
        Game swag_city = new Game(n, saver);
        swag_city.play();
    }

}
