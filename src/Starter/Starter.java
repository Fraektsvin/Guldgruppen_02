package Starter;

import Acquaintance.IBusiness;
import Acquaintance.IGame;
import Business.BusinessFacade;
import GUI.GameGUI;

public class Starter {

    public static void main(String[] args) {
        IBusiness business = new BusinessFacade();
        IGame game = new GameGUI();
        game.setBusiness(business);
        game.startApplication(args);
    }
}
