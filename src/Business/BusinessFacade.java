package Business;

import Acquaintance.IBusiness;
import Data.HighscoreManager;

public class BusinessFacade implements IBusiness {
    
    Game game = new Game(new Player("Erik Deluxe"), new HighscoreManager());
    Player player = new Player("Erik Deluxe");

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
    public String printWallet() {
        return game.printWallet();
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

    @Override
    public String interactWith(String npc) {
        Command c = new Command(CommandWord.INTERACT, npc);
        game.processCommand(c);
        return game.interactJB();
    }

    @Override
    public String getCoin(String coin) {
        Command c = new Command(CommandWord.GET, coin);
        game.processCommand(c);
        return game.processCoin();
    }
    
    @Override
    public String whichNPC() {
        String output = "";
        if(player.getCurrentRoom() == game.johnny_bravo) {
            output += "johnny bravo";
        }
        else if (player.getCurrentRoom() == game.randers) {
            output += "beatrice";
        }
        else if (player.getCurrentRoom() == game.michael_jackson) {
            output += "michael jackson";
        }
        else if (player.getCurrentRoom() == game.gulddreng) {
            output += "gulddreng";
        }
        else if (player.getCurrentRoom() == game.randers) {
            output += "mokai dealer";
        }
        else if (player.getCurrentRoom() == game.bjarne_riis) {
            output += "bjarne riis";
        }
        else if (player.getCurrentRoom() == game.swag_city) {
            output += "epo dealer";
        }
        else if (player.getCurrentRoom() == game.ole_henriksen) {
            output += "ole henriksen";
        }
        else if (player.getCurrentRoom() == game.diskotekets_dør) {
            output += "doermand";
        }
        else if (player.getCurrentRoom() == game.mors_hus) {
            output += "mor";
        }
        else if (player.getCurrentRoom() == game.randers) {
            output += "biver";
        }
        else if (player.getCurrentRoom() == game.swag_city) {
            output += "info dealer";
        }
        return output;
    }
}
