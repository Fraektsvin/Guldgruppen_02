/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* den her klasse gør det muligt for os at lave et objekt i dette tilfælde
er det en arrayliste der skal laves, derudover er der blevet lavet serializable 
siden den sortere i arraylisten. 
*/ 
import java.io.Serializable;
/**
 *
 * @author Antonio
 */
public class Score {
    private int score; 
    private String name; 
    

public int getScore() {
    return score;
}
public String getName() {
        return name;
        
}
public Score(String name, int Score) {
    this.score = score;
    this.name = name;
                
}
}