package Business;

import Acquaintance.IHighScore;
import Acquaintance.IScore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HighScore implements IHighScore {

    private List<IScore> currentHighScore;
    private ScoreComparator sc;

    public HighScore() {
        currentHighScore = new ArrayList<>();
        sc = new ScoreComparator();
    }

    @Override
    public Collection<IScore> getScores() { //vil blive kaldt af facade og sendt ned til highscore manager for at blive gemt
        return currentHighScore;
    }

    @Override
    public void addScore(IScore newScore) {
        for (int i = 0; i < currentHighScore.size(); i++) {
            if (sc.compare(newScore, currentHighScore.get(i)) == 1) {
                currentHighScore.add(i, newScore);

                if (currentHighScore.size() == 11) {
                    currentHighScore.remove(10);
                }
                return;
            }
        }
        if (currentHighScore.isEmpty()) {
            currentHighScore.add(newScore);
        }
    }

}
