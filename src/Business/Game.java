package Business;

import Acquaintance.ICoin;
import Acquaintance.IRoom;
import Business.NPCs.*;
import Data.HighscoreManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Game implements Serializable {

    private Player player;
    private final HighscoreManager HM;
    private final Map<IRoom, NPC> npcs;
    public GameTimer gameTimer;
    public Room swag_city, randers, johnny_bravo, mors_hus, gulddreng,
            bjarne_riis, diskotekets_dør, diskoteket, sidney_lee, hall_fame,
            ole_henriksen, michael_jackson;

    //Constructor
    public Game(Player player, HighscoreManager HM) {
        this.player = player;
        this.HM = HM;
        createGame();
        npcs = new HashMap<>();
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

    //Printer en intro til spillet når spillet startes.
    public String printWelcome() {
        StringBuilder welcomeString = new StringBuilder();
        welcomeString.append("Velkommen til Swag City!\n");
        welcomeString.append("Byen hvor drenge bliver til mænd... eller noget.\n");
        welcomeString.append("\n     ----- Introduktion til spillet -----     \n\n");
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
        welcomeString.append("for at blive byens nye mest swagste person.\n");
        welcomeString.append("-----------------------------------------------------\n\n");
        welcomeString.append("Når du trykker på knappen 'videre', starter spillets timer,");
        welcomeString.append(" og du har herfra kun 120 sekunder til at gennemføre spillet.\n");
        welcomeString.append("Dog opnår du 60 sekunders mere spiltid for hver fuldført mission.\n");
        welcomeString.append("Held og lykke!");
        return welcomeString.toString();
    }

    //Spillets kommandoer bliver herunder defineret.
    public String processCommand(Command command, String textInput) {
        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
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

    //Printer mulige kommandoer til skærmen.
    public String printHelp() {
        return "Brug knapperne 'snak med' til at snakke med spillets NPC'er\n"
                + "Hvis du bliver bedt om at svare på noget, skal du bruge inputfeltet i bunden af skærmen"
                + "til at skrive hvad du vil svare og herefter trykke 'snak med' igen.\n"
                + "og 'SYD', 'NORD', 'ØST' og 'VEST' til at navigere rundt i spillet.\n\n";
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
    public String goRoom(Command command) {
        String direction = command.getSecondWord();
        IRoom nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            return "Bum! Du løb ind i en væg, drik noget mindre\n";
        }
        if (player.getCurrentRoom().isLocked(direction)) {
            if (player.getCurrentRoom().equals(sidney_lee)) {
                if (npcs.containsKey(sidney_lee)) {
                    if (npcs.get(sidney_lee).isQuest() == false) {
                        sidney_lee.lockExit("south", false);
                    }
                } else {
                    return "Kun adgang for byens største swagster.\n"
                            + "Du har ikke vundet over Sidney Lee endnu.\n"
                            + "Prøv igen når du har besejret ham.";
                }
            } else if (player.getCurrentRoom().equals(diskotekets_dør)) {
                if (player.getInventory().size() > 3) {
                    diskotekets_dør.lockExit("north", false);
                    return "Swaggen oser ud af dig! Du er nu klar til diskoteket\n";
                } else {
                    return "Du skal have mere swag for at komme igennem!\n";
                }
            }
        } else {
            player.setCurrentRoom(nextRoom);
            if (player.getCurrentRoom() == hall_fame) {
                HM.saveScoreFile(player, gameTimer);
            }
            return player.getCurrentRoom().getLongDescription();
        }
        return "";
    }

    //Kommando til at interagere med npc'erne.
    private String interactNPC(Command command, String textInput) {
        if (player.getCurrentRoom() == johnny_bravo && command.getSecondWord().equalsIgnoreCase("johnny bravo")) {
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

    //Metode til at fjerne penge fra rummene og tilføje dem til playerens wallet ArrayList.
    private String getCoin(Command command) {
        String coinItemName = command.getSecondWord();
        ICoin newCoin = player.getCurrentRoom().getCoin(coinItemName);

        if (newCoin == null) {
            return "Der er ikke flere penge i dette rum.\n";
        } else {
            addCoin("Penge");
            player.getCurrentRoom().removeCoin(coinItemName);
            return "Samlede pengene op.\n";
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

    //Getter metode til at retunere npcs fra deres map.
    public Map<IRoom, NPC> getNpcs() {
        return npcs;
    }

    //Tilføjer en coin til playerns wallet.
    private void addCoin(String CoinName) {
        Coin coinToAdd = new Coin(CoinName);
        player.getWallet().add(coinToAdd);
        player.setScore(player.getScore() + coinToAdd.getVALUE());
    }

    //Tilføjer swag til playerens inventory.
    public void addSwag(String Swagname) {
        Swag swagToAdd = new Swag(Swagname);
        player.getInventory().add(swagToAdd);
        player.setScore(player.getScore() + swagToAdd.getVALUE());
    }

    //Metode til at fjerne swag fra playerens inventory.
    public void removeSwag(String SwagName) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getSwagDescription().equals(SwagName)) {
                player.getInventory().remove(i);
            }
        }
    }
}
