package Business;

import Acquaintance.IBusiness;
import Data.HighscoreManager;

public class BusinessFacade implements IBusiness {

    Game game = new Game(new Player("Erik"), new HighscoreManager());

    @Override
    public String printWelcome() {
        return game.printWelcome();
    }

    @Override
    public String printHelp() {
        return game.printHelp();
    }

    @Override
    public String printInventory() {
        return game.printInventory();
    }

    @Override
    public String printPengepung() {
        return game.printPengepung();
    }

    /*@Override
    public String getExitsFromCurrentRoom() {
        return game.getExitsCurrentRoom();
    }*/

    @Override
    public String goToDirection(String direction) { //ingen exception handling, dvs. ingen tjek for east, west, north, south
        Command c = new Command(CommandWord.GO, direction);
        game.processCommand(c);
        return game.getRoomDescription();
    }
   
}
