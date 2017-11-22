/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Acquaintance.IBusiness;
import Data.HighscoreManager;

/**
 *
 * @author sigur
 */
public class BusinessFacade implements IBusiness{
    
    Game game = new Game(new Player("Erik"), new HighscoreManager());
    
    @Override
    public String printWelcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printHelp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String printInventory() {
        return game.printInventory();
    }

    @Override
    public void printPengepung() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
