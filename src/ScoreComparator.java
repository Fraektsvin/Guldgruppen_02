/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Comparator;
/*
Denne klasse er blevet lavet til at sammenligne 2 objekter af denne samme type Score
Heraf ses der at -1 og +1 betyder mindre end eller større end den pågældende score
0 betyder derfor at det er lighed.
*/
public class ScoreComparator implements Comparator<Score> {
    public int compare(Score Score1, Score Score2) {
            int s1 = Score1.getScore();
            int s2 = Score2.getScore();
            
            if(s1 > s2) {
                return 1;
            }else if(s1 < s2) {
                return -1;
            }else {
                return 0;
           } 
        }
}