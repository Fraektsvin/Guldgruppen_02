package Business;

import Acquaintance.IBusiness;
import Data.HighscoreManager;

public class BusinessFacade implements IBusiness {

    private Game game;
    private Player player;
    private HighscoreManager highscoreManager;

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
    public void timerStart() {
        game.gameTimer = new GameTimer();
        game.gameTimer.timerStart();
    }

    @Override
    public void timerStop() {
        game.gameTimer.timerStop();
    }

    @Override
    public String goToDirection(String direction) {
        Command c = new Command(CommandWord.GO, direction);
        return game.goRoom(c);
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

    @Override
    public boolean questQuit() {
        boolean b = false;
        if (player.getCurrentRoom() == game.johnny_bravo) {
            b = game.getNpcs().get(game.johnny_bravo).isQuest();
        } else if (player.getCurrentRoom() == game.randers) {
            b = game.getNpcs().get(game.randers).isQuest();
        } else if (player.getCurrentRoom() == game.michael_jackson) {
            b = game.getNpcs().get(game.michael_jackson).isQuest();
        } else if (player.getCurrentRoom() == game.gulddreng) {
            b = game.getNpcs().get(game.gulddreng).isQuest();
        } else if (player.getCurrentRoom() == game.bjarne_riis) {
            b = game.getNpcs().get(game.bjarne_riis).isQuest();
        } else if (player.getCurrentRoom() == game.swag_city) {
            b = game.getNpcs().get(game.swag_city).isQuest();
        } else if (player.getCurrentRoom() == game.ole_henriksen) {
            b = game.getNpcs().get(game.ole_henriksen).isQuest();
        } else if (player.getCurrentRoom() == game.mors_hus) {
            b = game.getNpcs().get(game.mors_hus).isQuest();
        } else if (player.getCurrentRoom() == game.diskotekets_dør) {
            b = game.getNpcs().get(game.diskotekets_dør).isQuest();
        } 
        return b;
    }
    
    @Override
    public boolean winQuit() {
        boolean b = false;
        if (player.getCurrentRoom() == game.hall_fame) {
            b = true;
        }
        return b;
    }

    @Override
    public String savePlayer() {
        Command c = new Command(CommandWord.SAVE, "");
        game.processCommand(c, "");
        return "Spillet blev gemt.\n";
    }

    @Override
    public void loadPlayer() {
        highscoreManager.loadPlayer();
    }
}
