package Business;

import Acquaintance.IBusiness;
import Data.HighscoreManager;

public class BusinessFacade implements IBusiness {

    Game game;
    Player player;

    public BusinessFacade() {
        player = new Player("Erik Deluxe");
        game = new Game(player, new HighscoreManager());
    }

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

    @Override
    public String goToDirection(String direction) { //ingen exception handling, dvs. ingen tjek for east, west, north, south
        if (player.getCurrentRoom().isLocked(direction)) {
            if (player.getInventory().size() > 3) {
                game.diskotekets_dør.lockExit("north", false);
                return "Swaggen oser ud af dig! Du er nu klar til diskoteket\n";
            } else {
                return "Du skal have mere swag for at komme igennem!\n";
            }
        }
        if (player.getCurrentRoom() == game.hall_fame) {
            int Score = (player.getInventory().size() * 100) + (player.getWallet().size() * 25);
            return "Du er officielt den mest swagste person!\n"
                    + "Byen er deres o'høje Erik Deluxe.\n"
                    + "Din score er " + Score + " points.\n"
                    + "Du havde " + game.gameTimer.getTimeRemaining() + " sekunder tilbage.\n";
        }
        Command c = new Command(CommandWord.GO, direction);
        game.processCommand(c, "");
        return game.getRoomDescription();
    }

    @Override
    public String interactWith(String npc, String textInput) {
        Command c = new Command(CommandWord.INTERACT, npc);
        return game.processCommand(c, textInput);
    }

    @Override
    public String getCoin(String coin) {
        Command c = new Command(CommandWord.GET, coin);
        game.processCommand(c, "");
        return "Samlede pengene op\n";
    }

    @Override
    public String whichNPC() {
        String output = "";
        if (player.getCurrentRoom() == game.johnny_bravo) {
            output += "johnny bravo";
        } else if (player.getCurrentRoom() == game.randers) {
            output += "beatrice";
        } else if (player.getCurrentRoom() == game.michael_jackson) {
            output += "michael jackson";
        } else if (player.getCurrentRoom() == game.gulddreng) {
            output += "gulddreng";
        } else if (player.getCurrentRoom() == game.randers) {
            output += "mokai dealer";
        } else if (player.getCurrentRoom() == game.bjarne_riis) {
            output += "bjarne riis";
        } else if (player.getCurrentRoom() == game.swag_city) {
            output += "epo dealer";
        } else if (player.getCurrentRoom() == game.ole_henriksen) {
            output += "ole henriksen";
        } else if (player.getCurrentRoom() == game.diskotekets_dør) {
            output += "doermand";
        } else if (player.getCurrentRoom() == game.mors_hus) {
            output += "mor";
        } else if (player.getCurrentRoom() == game.randers) {
            output += "biver";
        } else if (player.getCurrentRoom() == game.swag_city) {
            output += "info dealer";
        }
        return output;
    }
}
