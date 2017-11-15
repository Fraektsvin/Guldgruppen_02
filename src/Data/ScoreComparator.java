package Data;

import java.util.Comparator;

/*
Denne klasse er blevet lavet til at sammenligne 2 objekter af denne samme type Score
Heraf ses der at -1 og +1 betyder mindre end eller større end den pågældende score
0 betyder derfor at det er lighed.
 */
public class ScoreComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer s1, Integer s2) {
        if (s1 > s2) {
            return 1;
        } else if (s1 < s2) {
            return -1;
        } else {
            return 0;
        }
    }
}
