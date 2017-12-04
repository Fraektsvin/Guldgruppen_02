package Business;

import Acquaintance.IScore;

public class Score implements IScore, Comparable<Score> {

    private Player player;
    private final int score = player.getScore();
    private final String name = player.getName();

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Score o) {
        if (this.score > o.getScore()) {
            return 1;
        } else if (this.score < o.getScore()) {
            return -1;
        }
        return 0;
    }
}
