package Business;

import Acquaintance.ICoin;
import Acquaintance.IRoom;
import Business.NPCs.*;
import Data.HighscoreManager;
import java.util.HashMap;
import java.util.Map;

public class Game {

    private final Parser parser;
    private Player player;
    private final HighscoreManager HM;
    private final Map<IRoom, NPC> npcs;
    private boolean finished = false;
    NPC_MOR npc_mor = new NPC_MOR(this, player);

    public Room swag_city, randers, johnny_bravo, mors_hus, gulddreng,
            bjarne_riis, diskotekets_dør, diskoteket, sidney_lee, hall_fame,
            ole_henriksen, michael_jackson;

    public final GameTimer gameTimer = new GameTimer();

    public Game(Player player, HighscoreManager HM) {
        this.player = player;
        this.HM = HM;
        createGame();
        parser = new Parser();
        npcs = new HashMap<>();
    }

    public String getRoomDescription() {
        return player.getCurrentRoom().getLongDescription();
    }

    //Printer en intro til spillet når spillet startes.
    public String printWelcome() {
        StringBuilder welcomeString = new StringBuilder();
        welcomeString.append("Velkommen til Swag City!\n");
        welcomeString.append("Byen hvor drenge bliver til mænd... eller noget.\n");
        welcomeString.append("\n     ----- Introduktion til spillet -----   \n\n");
        welcomeString.append("Erik Deluxe er havnet i Swag City.\n");
        welcomeString.append("Han er på en mission for at finde byens mest swagste person.\n");
        welcomeString.append("På Eriks farefulde færd møder han diverse hjælpere og modstandere,\n");
        welcomeString.append("disse kan henholdsvis hjælpe med tips til at vinde over Sidney Lee,\n");
        welcomeString.append("som er byens swagster, eller frarøve dig dine swagting.\n");
        welcomeString.append("Swagtingene kan opnås fra forskellige bekendtheder som befinder sig i byen,\n");
        welcomeString.append("der skal mindst opnås 3 yderlige swagting for at få adgang til byens diskotek,\n");
        welcomeString.append("som er opholdsstedet for Sidney Lee.\n");
        welcomeString.append("Som start har Erik Deluxe hans verdensberømte swag håndtegn,\n");
        welcomeString.append("disse swagting bruges som spillets liv, frarøves alle swagtingene taber man spillet,\n");
        welcomeString.append("og for at vinde spillet skal man besejre Sidney Lee i en dancebattle,\n");
        welcomeString.append("for at blive byens nye mest swagste person.\n\n");
        return welcomeString.toString();
    }

    //Spillets kommandoer bliver herunder defineret
    public String processCommand(Command command, String textInput) {
        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case GO:
                boolean toQuitGo = goRoom(command);
                if (toQuitGo) {
                    finished = true;
                }
                break;
            case INVENTORY:
                printInventory();
                break;
            case WALLET:
                printWallet();
                break;
            case GET:
                getCoin(command);
                break;
            case INTERACT:
                String toReturn = interactNPC(command, textInput);
                questQuit();
                return toReturn;
            case SAVE:
                player.setSavedTime(gameTimer.getTimeRemaining());
                HM.savePlayer(player);
                break;
            case LOAD:
                player = HM.loadPlayer();
                gameTimer.setTime(player.getSavedTime());
                break;
            default:
                break;
        }
        return "";
    }

    //Metode til at fjerne penge fra rummene og tilføje dem til playerens wallet ArrayList.
    private void getCoin(Command command) {

        String coinItemName = command.getSecondWord();
        ICoin newCoin = player.getCurrentRoom().getCoin(coinItemName);

        if (newCoin == null) {
            System.out.println("  Den ting eksistere ikke\n");
        } else {
            addCoin("Penge");
            player.getCurrentRoom().removeCoin(coinItemName);
            System.out.println("Samlede " + coinItemName + "ne op\n");
        }
    }

    //Metode til at tjekke om en item er i ArrayListen Player's inventory.
    public Swag getSwag(String swagName) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getSwagDescription().equals(swagName)) {
                return player.getInventory().get(i);
            }
        }
        return null;
    }

    //Printer mulige kommandoer til skærmen.
    public String printHelp() {
        String output = "";
        output += "Du helt væk, mokaiens dunst sværmer omkring dig.\n";
        output += "Tag dig sammen.\n\n";
        output += "Dine råb om hjælp er:\n";
        return output + parser.showCommands() + "\n";
    }

    //Printer playerens inventory ArrayList's indhold til skærmen.
    public String printInventory() {
        String output = "";
        for (int i = 0; i < player.getInventory().size(); i++) {
            output += player.getInventory().get(i).getSwagDescription() + "\n";
        }
        return output;
    }

    //Printer playerens wallet ArrayList's indhold til skærmen.
    public String printWallet() {
        String output = "";
        if (player.getWallet().isEmpty()) {
            output += "Du har ingen mønter\n";
        } else if (player.getWallet().size() == 1) {
            output += "Du har " + player.getWallet().size() + " mønt\n";
        } else {
            output += "Du har " + player.getWallet().size() + " mønter\n";
        }
        return "Dine mønter:\n" + output;
    }

    //Kommando til bevægelse imellem rum, tjekker desuden for låste døre og printer highscore når man vinder.
    private boolean goRoom(Command command) {
        String direction = command.getSecondWord();
        IRoom nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("Bum! Du løb ind i en væg, drik noget mindre\n");
        } else {
            player.setCurrentRoom(nextRoom);
            if (player.getCurrentRoom() == hall_fame) {
                return true;
            }
            HighscoreManager highscoreManager = new HighscoreManager();
            highscoreManager.saveScoreFile(player, gameTimer);
        }
        return false;
    }

    //Metode til at afslutt spillet ud fra spillets missioner.
    private void questQuit() {
        if (npc_mor.isQuest() == true) {
            System.exit(0);
        }
    }

    //Tilføjer en coin til playerns wallet
    private void addCoin(String CoinName) {
        Coin coinToAdd = new Coin(CoinName);
        player.getWallet().add(coinToAdd);
        player.setScore(player.getScore() + coinToAdd.getVALUE());
    }

    //Tilføjer swag til playerens inventory
    public void addSwag(String Swagname) {
        Swag swagToAdd = new Swag(Swagname);
        player.getInventory().add(swagToAdd);
        player.setScore(player.getScore() + swagToAdd.getVALUE());
    }

    //String til at printe tekst ved skift af hvert rum.
    public String getExitsCurrentRoom() {
        return player.getCurrentRoom().getExitString();
    }

    //Metode til at fjerne swag fra playerens inventory
    public void removeSwag(String SwagName) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getSwagDescription().equals(SwagName)) {
                player.getInventory().remove(i);
            }
        }
    }

    //Kommando til at interagere med npc'erne
    private String interactNPC(Command command, String textInput) {

        if (!command.hasSecondWord()) {
            return "Hvem prøver du at kontakte?";
        } else if (player.getCurrentRoom() == johnny_bravo && command.getSecondWord().equalsIgnoreCase("johnny bravo")) {
            if (!npcs.keySet().contains(johnny_bravo)) {
                npcs.put(johnny_bravo, new NPC_JB(this, player));
            }
            return npcs.get(johnny_bravo).interact(textInput);
        } else if (player.getCurrentRoom() == randers && command.getSecondWord().equalsIgnoreCase("beatrice")) {
            if (!npcs.keySet().contains(randers)) {
                npcs.put(randers, new NPC_BT(this, player));
            }
            return npcs.get(randers).interact(textInput);
        } else if (player.getCurrentRoom() == michael_jackson && command.getSecondWord().equalsIgnoreCase("michael jackson")) {
            if (!npcs.keySet().contains(michael_jackson)) {
                npcs.put(michael_jackson, new NPC_MJ(this, player));
            }
            return npcs.get(michael_jackson).interact(textInput);
        } else if (player.getCurrentRoom() == gulddreng && command.getSecondWord().equalsIgnoreCase("gulddreng")) {
            if (!npcs.keySet().contains(gulddreng)) {
                npcs.put(gulddreng, new NPC_GD(this, player));
            }
            return npcs.get(gulddreng).interact(textInput);
        } else if (player.getCurrentRoom() == randers && command.getSecondWord().equalsIgnoreCase("mokai dealer")) {
            if (!npcs.keySet().contains(randers)) {
                npcs.put(randers, new NPC_MD(this, player));
            }
            return npcs.get(randers).interact(textInput);
        } else if (player.getCurrentRoom() == bjarne_riis && command.getSecondWord().equalsIgnoreCase("bjarne riis")) {
            if (!npcs.keySet().contains(bjarne_riis)) {
                npcs.put(bjarne_riis, new NPC_BR(this, player));
            }
            return npcs.get(bjarne_riis).interact(textInput);
        } else if (player.getCurrentRoom() == swag_city && command.getSecondWord().equalsIgnoreCase("epo dealer")) {
            if (!npcs.keySet().contains(swag_city)) {
                npcs.put(swag_city, new NPC_EPO(this, player));
            }
            return npcs.get(swag_city).interact(textInput);
        } else if (player.getCurrentRoom() == ole_henriksen && command.getSecondWord().equalsIgnoreCase("ole henriksen")) {
            if (!npcs.keySet().contains(ole_henriksen)) {
                npcs.put(ole_henriksen, new NPC_OH(this, player));
            }
            return npcs.get(ole_henriksen).interact(textInput);
        } else if (player.getCurrentRoom() == diskotekets_dør && command.getSecondWord().equalsIgnoreCase("doermand")) {
            if (!npcs.keySet().contains(diskotekets_dør)) {
                npcs.put(diskotekets_dør, new NPC_DM(this, player));
            }
            return npcs.get(diskotekets_dør).interact(textInput);
        } else if (player.getCurrentRoom() == mors_hus && command.getSecondWord().equalsIgnoreCase("mor")) {
            if (!npcs.keySet().contains(mors_hus)) {
                npcs.put(mors_hus, new NPC_MOR(this, player));
            }
            return npcs.get(mors_hus).interact(textInput);
        } else if (player.getCurrentRoom() == randers && command.getSecondWord().equalsIgnoreCase("biver")) {
            if (!npcs.keySet().contains(randers)) {
                npcs.put(randers, new NPC_RT(this, player));
            }
            return npcs.get(randers).interact(textInput);
        } else if (player.getCurrentRoom() == swag_city && command.getSecondWord().equalsIgnoreCase("info dealer")) {
            if (!npcs.keySet().contains(swag_city)) {
                npcs.put(randers, new NPC_ID(this, player));
            }
            return npcs.get(swag_city).interact(textInput);
        } else if (player.getCurrentRoom() == sidney_lee) {
            if (!npcs.keySet().contains(sidney_lee)) {
                npcs.put(sidney_lee, new NPC_SL(this, player));
            }
            return npcs.get(sidney_lee).interact(textInput);
        } else {
            return "Hvem prøver du at kontakte?";
        }
    }

    //createRooms metoden instantiere spillets rum, npc'er og items i spillet.
    private void createGame() {
        //Opretter spillets rum
        swag_city = new Room("Du er ved Swag City byskiltet");
        randers = new Room("Du er ved Randers, hjemstedet for Mokaien");
        mors_hus = new Room("Du er hjemme ved mor");
        gulddreng = new Room("Du er hos Gulddrengen");
        bjarne_riis = new Room("Du er hos Bjarne Riis");
        diskotekets_dør = new Room("Du er ved indgangen til diskoteket");
        diskoteket = new Room("Du er på diskoteket");
        sidney_lee = new Room("Du er i Sidney Lee's private rum");
        hall_fame = new Room("Du har besejret Sidney Lee, du er en sand cremerider helt");
        ole_henriksen = new Room("Du er hos Ole Henriksen");
        michael_jackson = new Room("Du er hos Michael Jackson");
        johnny_bravo = new Room("Du er hos Johnny Bravo");

        //Indsætter udgangene til hvert rum
        swag_city.setExit("east", randers);
        swag_city.setExit("south", johnny_bravo);
        swag_city.setExit("north", diskotekets_dør);

        randers.setExit("west", swag_city);
        randers.setExit("south", mors_hus);

        mors_hus.setExit("north", randers);
        mors_hus.setExit("west", johnny_bravo);

        johnny_bravo.setExit("north", swag_city);
        johnny_bravo.setExit("east", mors_hus);
        johnny_bravo.setExit("west", michael_jackson);

        michael_jackson.setExit("east", johnny_bravo);
        michael_jackson.setExit("north", ole_henriksen);

        ole_henriksen.setExit("south", michael_jackson);

        diskotekets_dør.setExit("south", swag_city);
        diskotekets_dør.setExit("east", gulddreng);
        diskotekets_dør.setExit("north", diskoteket);

        gulddreng.setExit("west", diskotekets_dør);
        gulddreng.setExit("north", bjarne_riis);

        bjarne_riis.setExit("south", gulddreng);

        diskoteket.setExit("south", diskotekets_dør);
        diskoteket.setExit("west", sidney_lee);

        sidney_lee.setExit("east", diskoteket);
        sidney_lee.setExit("south", hall_fame);

        //Sætter spillerens start rum og tilføjer den første ting i inventory
        player.setCurrentRoom(swag_city);
        addSwag("Swag håndtegn");

        //Coins tilføjes til rumene
        johnny_bravo.setCoin(new Coin("penge"));
        swag_city.setCoin(new Coin("penge"));
        diskotekets_dør.setCoin(new Coin("penge"));
        michael_jackson.setCoin(new Coin("penge"));
        gulddreng.setCoin(new Coin("penge"));
        bjarne_riis.setCoin(new Coin("penge"));

        //NPC'er indsættes i de forskellige rum.
        swag_city.setNPC(new NPC_ID(this, player));
        johnny_bravo.setNPC(new NPC_JB(this, player));
        michael_jackson.setNPC(new NPC_MJ(this, player));
        gulddreng.setNPC(new NPC_GD(this, player));
        bjarne_riis.setNPC(new NPC_BR(this, player));
        ole_henriksen.setNPC(new NPC_OH(this, player));
        mors_hus.setNPC(new NPC_MOR(this, player));
        sidney_lee.setNPC(new NPC_SL(this, player));
        diskotekets_dør.setNPC(new NPC_DM(this, player));
        randers.setNPC(new NPC_RT(this, player));

        //Lock condition til udgange
        diskotekets_dør.lockExit("north", true);
        sidney_lee.lockExit("south", true);
    }
}
