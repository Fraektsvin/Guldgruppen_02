package Business;

import Acquaintance.IScore;
import java.util.Comparator;

/*
Denne klasse er blevet lavet til at sammenligne 2 objekter af denne samme type Score
Heraf ses der at -1 og +1 betyder mindre end eller større end den pågældende score
0 betyder derfor at det er lighed.
 */
public class ScoreComparator implements Comparator<IScore> {

    @Override
    public int compare(IScore s1, IScore s2) {
        if (s1.getScore() > s2.getScore()) {
            return 1;
        } else if (s1.getScore() < s2.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}
