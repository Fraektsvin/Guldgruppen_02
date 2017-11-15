
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighscoreManager {

    private final ArrayList<Score> scores;

    private static final File HIGHSCORE_FILE = new File("Highscore");
    private static final File PLAYER_FILE = new File("GameSaver");

    FileWriter fileWriter = null;

    public HighscoreManager() {
        scores = new ArrayList<>();
    }

    /*
  Denne funktion vil returnere en arrayliste med scoren, loadScoreFile og sort 
  vil sørget for at du får Highscore i sorteret ordning. 
     */
    public ArrayList<Score> getScores() throws FileNotFoundException {
        LoadScoreFile();
        Sort();
        return scores;
    }

    // metoden for sort, så at tingene kan blive sorteret, heraf har java.util alleredde Collection.sort implementeret 
    // Java.util vil derfor via Collection.sort sørge for at organisere scoren med hjælp fra en comparator. 
    private void Sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }

    public void LoadScoreFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(HIGHSCORE_FILE);
        System.out.println(scanner.nextLine());

        /**
         * Blevet lavet en FileWriter istedet for en Scanner metode siden, at
         * det er bedre fordi det kalder en objektinput og output stream er et
         * objekt og dette er ikke et en objekt men derimod nogle tal vi skulle
         * safe og printe ud til spilleren rå bytes f.eks til billede data, men
         * siden vi skal igennem tekst så bruger vi FileWriter siden at den
         * tager teksten fremfor byteserne . Scanner, generelt til at læse
         * filer.
         *
         */
    }

    public void saveScoreFile() {
        try {
            fileWriter = new FileWriter(HIGHSCORE_FILE);
            fileWriter.append("TEST");
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println("*** fejl ved FileWriter ***");
        }
    }

    public void savePlayer(Player player) {
        try {
            FileOutputStream outputStream = new FileOutputStream(PLAYER_FILE);
            ObjectOutputStream savePlayerStream = new ObjectOutputStream(outputStream);
            savePlayerStream.writeObject(player);
            System.out.println("Spillet blev gemt.\n");
            outputStream.close();
            savePlayerStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Player loadPlayer() {
        Player player = null;
        try {
            FileInputStream inputStream = new FileInputStream(PLAYER_FILE);
            ObjectInputStream loadPlayerStream = new ObjectInputStream(inputStream);
            player = (Player) loadPlayerStream.readObject();
            System.out.println("Det tidligere spil blev loaded.\n");
            inputStream.close();
            loadPlayerStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighscoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return player;
    }
}
