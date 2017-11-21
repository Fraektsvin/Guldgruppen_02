package Acquaintance;

import java.util.Collection;

public interface IHighScore {

    Collection<IScore> getScores();

    void addScore(IScore newScore);
}
