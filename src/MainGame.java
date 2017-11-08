/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author sigur
 */
public class MainGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        GameTimer gameTimer = new GameTimer();
        gameTimer.timerStart();
        player n = new player("Erik Deluxe");
        n.displayName();
        Game swag_city = new Game();
        swag_city.play();  
        
    }
      
}