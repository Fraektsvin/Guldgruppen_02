
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HighscoresManager {

    private ArrayList<Score> scores;

    private static final File HIGHSCORE_FILE = new File("Highscore.txt");

    FileWriter fileWriter = null;

    public HighscoresManager() {
        scores = new ArrayList<Score>();
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
         * Blevet lavet en FileWriter istedet for en Scanner metode siden, at det er bedre fordi det kalder en
         * objektinput og output stream er et objekt og dette er ikke et en objekt men derimod nogle tal vi skulle safe og printe ud til spilleren
         * rå bytes f.eks til billede data, men siden vi skal igennem tekst så bruger vi FileWriter siden at den tager teksten fremfor byteserne .
         * Scanner, generelt til at læse filer. 
         *
         */
    }

    public void saveScoreFile() {
        try {
            fileWriter = new FileWriter(HIGHSCORE_FILE);
            System.out.println("går ind i saveScore");
            fileWriter.append("Gooodauuuv");
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println("*** fejl ved FileWriter ***");
        }

    }
}
