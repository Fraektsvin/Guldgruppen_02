package Business;

import Business.NPCs.NPC_EPO;
import Business.NPCs.NPC_GD;
import Business.NPCs.NPC_ID;
import Business.NPCs.NPC_MOR;
import Business.NPCs.NPC_RT;
import Business.NPCs.NPC_OH;
import Business.NPCs.NPC_BR;
import Business.NPCs.NPC_JB;
import Business.NPCs.NPC_DM;
import Business.NPCs.NPC_MJ;
import Business.NPCs.NPC_MD;
import Business.NPCs.NPC_BT;
import Business.NPCs.NPC_SL;
import Data.HighscoreManager;
import Acquaintance.IGame;
import java.util.Scanner;

public class Game implements IGame {

    private final Parser parser;
    private Player player;
    private final HighscoreManager HM;
    Scanner scanner = new Scanner(System.in);

    /*
    Rooms placere vi udenfor 'createRoom' metoden,
    således vi kan tilgå rummene i andre metoder senere.
     */
    private Room swag_city, randers, johnny_bravo, mors_hus, gulddreng,
            bjarne_riis, diskotekets_dør, diskoteket, sidney_lee, hall_fame,
            ole_henriksen, michael_jackson;

    private final GameTimer gameTimer = new GameTimer();

    public Game(Player player, HighscoreManager HM) {
        this.player = player;
        this.HM = HM;
        gameTimer.timerStart();
        createRooms();
        parser = new Parser();
    }

    public void play() {
        System.out.println(printWelcome());

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Tak fordi at du spillede med os, din stodder.");
        gameTimer.timerStop();
    }

    //Printer en intro til spillet når spillet startes.
    @Override
    public String printWelcome() {
        //Intro til spillet
        StringBuilder welcomeString = new StringBuilder();
        welcomeString.append("Velkommen til Swag City!\n");
        welcomeString.append("Byen hvor drenge bliver til mænd... eller noget.\n");
        welcomeString.append("\n   ----- Introduktion til spillet -----   \n\n");
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
        welcomeString.append("Din spilletid er nu gået i gang, du har ").append(gameTimer.getTimeRemaining()).append(" sekunder tilbage.\n");
        welcomeString.append("For hver mission du klarer får du et minut mere at spille for. Held og lykke!\n\n");
        welcomeString.append("Har du brug for hjælp? Skriv '").append(CommandWord.HELP).append("' hvis du er fuld.\n\n");
        welcomeString.append(player.getCurrentRoom().getLongDescription());
        return welcomeString.toString();
    }

    //Spillets kommandoer bliver herunder defineret
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("Æhhh? Hvad fanden?\n");
            return false;
        }
        if (null != commandWord) //Kommandoer som bruges til at spille spillet.
        {
            switch (commandWord) {
                case HELP:
                    printHelp();
                    break;
                case GO:
                    wantToQuit = goRoom(command);
                    break;
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                case INVENTORY:
                    printInventory();
                    break;
                //der blevet lavet en ny kommando med Get så der kan pickes items up
                case PENGEPUNG:
                    printPengepung();
                    break;
                case GET:
                    getCoin(command);
                    break;
                case INTERACT:
                    interactNPC(command);
                    wantToQuit = inventoryQuit();
                    break;
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
        }
        return wantToQuit;
    }

    //Metode til at fjerne penge fra rummene og tilføje dem til ArrayListen player.getPengepung().
    private void getCoin(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvad vil du have?\n");
            return;
        }

        String coinItemName = command.getSecondWord();
        Coin newCoin = player.getCurrentRoom().getCoin(coinItemName);

        if (newCoin == null) {
            System.out.println("  Den ting eksistere ikke\n");
        } else {
            addCoin("Penge");
            player.getCurrentRoom().removeCoin(coinItemName);
            System.out.println("Samlede " + coinItemName + "ne op\n");
        }
    }

    //Metode til at tjekke om en item er i ArrayListen Player's inventory.
    private Swag getSwag(String swagName) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getSwagDescription().equals(swagName)) {
                return player.getInventory().get(i);
            }
        }
        return null;
    }

    //Printer mulige kommandoer til skærmen.
    @Override
    public void printHelp() {
        System.out.println("Du helt væk, mokaiens dunst sværmer omkring dig.");
        System.out.println("Tag dig sammen.\n");
        System.out.println("Dine råb om hjælp er:");
        parser.showCommands();
        System.out.println("\nget (Swagting)  |  go (Direction)  |  interact (NPC)\n");
    }

    //Printer ArrayListen player.getInventory()'s indhold til skærmen.
    @Override
    public void printInventory() {
        String output = "";
        for (int i = 0; i < player.getInventory().size(); i++) {
            output += player.getInventory().get(i).getSwagDescription() + "\n";
        }
        System.out.println(output);
    }

    //Printer ArrayListen player.getInventory()'s indhold til skærmen.
    @Override
    public void printPengepung() {
        String output = "";
        if (player.getPengepung().isEmpty()) {
            output += "Du har ingen mønter\n";
        } else if (player.getPengepung().size() == 1) {
            output += "Du har " + player.getPengepung().size() + " mønt\n";
        } else {
            output += "Du har " + player.getPengepung().size() + " mønter\n";
        }
        System.out.println("Dine mønter:");
        System.out.println(output);
    }

    //Kommando til bevægelse imellem rum, tjekker desuden for låste døre og printer highscore når man vinder.
    private boolean goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvor vil du hen Erik?");
            System.out.println(player.getCurrentRoom().getMediumDescription() + "\n");
            return false;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Bum! Du løb ind i en væg, drik noget mindre\n");
        } else if (player.getCurrentRoom().isLocked(direction)) {
            if (player.getInventory().size() > 3) {
                diskotekets_dør.lockExit("north", false);
                System.out.println("Swaggen oser ud af dig! Du er nu klar til diskoteket\n");
            } else {
                System.out.println("Du skal have mere swag for at komme igennem!\n");
            }
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            if (player.getCurrentRoom() == sidney_lee) {
                NPC_SL npc_sl = new NPC_SL();
                npc_sl.interact(scanner);
                if (npc_sl.isQuest() == true) {
                    sidney_lee.lockExit("south", false);
                    System.out.println("\nDu har besejret Sidney Lee, træd venligst ind i Hall of Fame (south exit)\n");
                } else if (npc_sl.isQuest() == false) {
                    System.out.println("Du tabte til Sidney Lee - Game over!\n");
                    player.getInventory().clear();
                    return true;
                }
            }
            //if statement som sætter wantToQuit = true, hvilket gør at man vinder spillet.
            if (player.getCurrentRoom() == hall_fame) {
                System.out.println("Du er officielt den mest swagste person!");
                System.out.println("Byen er deres o'høje Erik Deluxe.\n");
                int Score;
                Score = (player.getInventory().size() * 100) + (player.getPengepung().size() * 25);
                System.out.println("Din score er " + Score + " points.\n");
                System.out.println("Du havde " + gameTimer.getTimeRemaining() + " sekunder tilbage.\n");
                return true;
            }
            HighscoreManager highscoreManager = new HighscoreManager();
            highscoreManager.saveScoreFile(player);
        }
        return false;
    }

    //Kalder quit command og slutter spillet.
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Prøver du at stoppe med at spille!?\n");
            return false;
        } else {
            return true;
        }
    }

    //Checker om player.getInventory() er tom, i det tilfælde sættes wantToQuit = true og spillet sluttes.
    private boolean inventoryQuit() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Du har mistet alt dit swag");
            System.out.println("Du er ikke længere værdig til at opholde dig i Swag City\n");
            return true;
        } else {
            return false;
        }

    }

    private void addCoin(String CoinName) {
        Coin coinToAdd = new Coin(CoinName);
        player.getPengepung().add(coinToAdd);
        player.setScore(player.getScore() + coinToAdd.getVALUE());
    }

    private void addSwag(String Swagname) {
        Swag swagToAdd = new Swag(Swagname);
        player.getInventory().add(swagToAdd);
        player.setScore(player.getScore() + swagToAdd.getVALUE());
    }

    /*Metode til at fjerne items fra ArrayListen player.getInventory().
    player.getInventory().add(new Swag("Swag håndtegn")); brugte vi tidligere ved at tilføje vores items, dette 
    var ikke særlig smart da der hele tiden skulle kaldes de her ting derudover havde vi heller ikke score Value inden i koden
    Derfor benyttet vi os af en liste der indebære valuen samt med at der tilføjes items inden i inventory. tilføjelsen af setter og getter i player klassen har dermed gjort at vi kan skabe en ny metode
    inden i game klassen der gør at vi kan udskifte player.getInventory().add(new Swag("Swag håndtegn")); ud med addSwag fordi den indeholder det samme, da vi har skabt en ny metode. 
     */
    private void removeSwag(String SwagName) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getSwagDescription().equals(SwagName)) {
                player.getInventory().remove(i);
            }
        }
    }

    //Kommando til at interagere med npc'erne
    private void interactNPC(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvem prøver du at kontakte?\n");
        } else if (player.getCurrentRoom() == johnny_bravo && command.getSecondWord().equalsIgnoreCase("johnny bravo")) {
            NPC_JB npc_jb = new NPC_JB();
            if (getSwag("Seddel fra Johnny Bravo") != null) {
                System.out.println("Du er allerede på denne mission.\n");
            } else if (getSwag("Johnny Bravo håret") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("Beatrice's nummer") != null) {
                System.out.println("Johnny Bravo: Du skaffede mig nummeret! Du er en sand guttermand.");
                System.out.println("Johnny Bravo: Her tag min paryk der ligner mit hår på en prik, så kan det være du er heldig hos damerne.\n");
                removeSwag("Beatrice's nummer");
                addSwag("Johnny Bravo håret");
                System.out.println("Mission fuldført.\n");
                gameTimer.addTime(60);
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_jb.interact(scanner);
                if (npc_jb.isQuest() == true) {
                    addSwag("Seddel fra Johnny Bravo");
                    randers.setNPC(new NPC_BT());
                }
            }
        } else if (player.getCurrentRoom() == randers && command.getSecondWord().equalsIgnoreCase("beatrice")) {
            NPC_BT npc_bt = new NPC_BT();
            if (getSwag("Beatrice's nummer") != null) {
                System.out.println("Du har allerede fået Beatrice's nummer");
                System.out.println("Måske du skulle aflevere det hos Johnny Bravo.\n");
            } else if (getSwag("Johnny Bravo håret") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_bt.interact(scanner);
                if (npc_bt.isQuest() == true) {
                    removeSwag("Seddel fra Johnny Bravo");
                    addSwag("Beatrice's nummer");
                }
            }
        } else if (player.getCurrentRoom() == michael_jackson && command.getSecondWord().equalsIgnoreCase("michael jackson")) {
            NPC_MJ npc_mj = new NPC_MJ();
            if (getSwag("Michael Jacksons guldsko") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_mj.interact(scanner);
                if (npc_mj.isQuest() == true) {
                    addSwag("Michael Jackson guldsko");
                    System.out.println("Mission fuldført.\n");
                    gameTimer.addTime(60);
                }
            }
        } else if (player.getCurrentRoom() == gulddreng && command.getSecondWord().equalsIgnoreCase("gulddreng")) {
            NPC_GD npc_gd = new NPC_GD();
            if (getSwag("Guldpenge fra Gulddrengen") != null) {
                System.out.println("Du er allerede på denne mission.\n");
            } else if (getSwag("Gulddreng's guldkæde") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("Frisk mokai") != null) {
                System.out.println("Gulddreng: En frisk mokai? Sygt god stil! Gulddrengen takker, her tag min guldkæde");
                System.out.println("Hvorfor tænker du måske? Bare fordi jeg kan, nemt.\n");
                removeSwag("Frisk mokai");
                addSwag("Gulddreng's guldkæde");
                System.out.println("Mission fuldført.\n");
                gameTimer.addTime(60);
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_gd.interact(scanner);
                if (npc_gd.isQuest() == true) {
                    addSwag("Guldpenge fra Gulddrengen");
                    randers.setNPC(new NPC_MD());
                }
            }
        } else if (player.getCurrentRoom() == randers && command.getSecondWord().equalsIgnoreCase("mokai dealer")) {
            NPC_MD npc_md = new NPC_MD();
            if (getSwag("Frisk mokai") != null) {
                System.out.println("Du har allerede fået en frisk mokai");
                System.out.println("Måske du skulle aflevere den hos Gulddrengen.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_md.interact(scanner);
                if (npc_md.isQuest() == true) {
                    removeSwag("Guldpenge fra Gulddrengen");
                    addSwag("Frisk mokai");
                }
            }
        } else if (player.getCurrentRoom() == bjarne_riis && command.getSecondWord().equalsIgnoreCase("bjarne riis")) {
            NPC_BR npc_br = new NPC_BR();
            if (getSwag("Seddel fra Bjarne Riis") != null) {
                System.out.println("Du er allerede på denne mission.\n");
            } else if (getSwag("Bjarne Riis's hurtig briller") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Bjarne Riis: Skynd dig! Giv mig posen før nogen ser det!");
                System.out.println("Bjarne Riis: Mange tak, husk det her er aldrig sket! Du ved intet.");
                System.out.println("Bjarne Riis: Her tag mine hurtigbriller fra 96 da jeg vandt Tour de France som tak");
                System.out.println("Bjarne Riis: Snyd eller ej, så er du en sikker vinder!\n");
                removeSwag("EPO");
                addSwag("Bjarne Riis's hurtig briller");
                System.out.println("Mission fuldført.\n");
                gameTimer.addTime(60);
            } else {
                npc_br.interact(scanner);
                if (npc_br.isQuest() == true) {
                    addSwag("Seddel fra Bjarne Riis");
                    swag_city.setNPC(new NPC_EPO());
                }
            }
        } else if (player.getCurrentRoom() == swag_city && command.getSecondWord().equalsIgnoreCase("epo dealer")) {
            NPC_EPO npc_epo = new NPC_EPO();
            if (getSwag("EPO") != null) {
                System.out.println("Du har allerede fået en pose EPO");
                System.out.println("Måske du skulle aflevere den hos Bjarne Riis.\n");
            } else {
                npc_epo.interact(scanner);
                if (npc_epo.isQuest() == true) {
                    removeSwag("Seddel fra Bjarne Riis");
                    addSwag("EPO");
                }
            }
        } else if (player.getCurrentRoom() == ole_henriksen && command.getSecondWord().equalsIgnoreCase("ole henriksen")) {
            NPC_OH npc_oh = new NPC_OH();
            if (getSwag("Seddel fra Ole Henriksen") != null) {
                System.out.println("Du er allerede på denne mission.\n");
            } else if (getSwag("Fabulous tøj fra Ole Henriksen") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("Dørmandens nummer") != null) {
                System.out.println("Ole Henriksen: Du fik nummeret!?");
                System.out.println("Ole Henriksen: Jeg havde aldrig turde håbe på at han kunne være til sådan noget");
                System.out.println("Ole Henriksen: Jaja der kan man se, nogle gange er man heldig! Ej hvor jeg bare er glad nu");
                System.out.println("Ole Henriksen: Her lad mig hjælpe med dit forfærdelige kluns, her får du et rigtigt outfit.\n");
                removeSwag("Dørmandens nummer");
                addSwag("Fabulous tøj fra Ole Henriksen");
                System.out.println("Mission fuldført.\n");
                gameTimer.addTime(60);
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_oh.interact(scanner);
                if (npc_oh.isQuest() == true) {
                    addSwag("Seddel fra Ole Henriksen");
                }
            }
        } else if (player.getCurrentRoom() == diskotekets_dør && command.getSecondWord().equalsIgnoreCase("doermand")) {
            NPC_DM npc_dm = new NPC_DM();
            if (getSwag("Seddel fra Ole Henriksen") == null) {
                System.out.println("Smut med dig, jeg har travlt.\n");
            } else if (getSwag("Dørmandens nummer") != null) {
                System.out.println("Du har allerede fået dørmandens nummer");
                System.out.println("Måske du skulle aflevere den hos Ole Henriksen.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            } else {
                npc_dm.interact(scanner);
                if (npc_dm.isQuest() == true) {
                    removeSwag("Seddel fra Ole Henriksen");
                    addSwag("Dørmandens nummer");
                }
            }
        } else if (player.getCurrentRoom() == mors_hus && command.getSecondWord().equalsIgnoreCase("mor")) {
            NPC_MOR npc_mor = new NPC_MOR();
            npc_mor.interact(scanner);
            if (npc_mor.isQuest() == true) {
                player.getInventory().clear();
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            }
        } else if (player.getCurrentRoom() == randers && command.getSecondWord().equalsIgnoreCase("biver")) {
            NPC_RT npc_rt = new NPC_RT();
            npc_rt.interact(scanner);
            if (npc_rt.isQuest() == true) {
                player.getInventory().clear();
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                player.getInventory().clear();
            }
        } else if (player.getCurrentRoom() == swag_city && command.getSecondWord().equalsIgnoreCase("info dealer")) {
            System.out.println("Info dealer: Velkommen til Swag City! Jeg kan give dig nogle enkelte informationer.");
            System.out.println("Fra Swag City byskiltet kan du gå");
            System.out.println("east = Randers   |   south = Johnny Bravo   |   north = Diskotekets indgang");
            System.out.println("Resten er op til dig. Held og lykke Erik Deluxe!\n");
        } else if (getSwag("EPO") != null) {
            System.out.println("Der blev sagt ingen kommentarer");
            System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
            player.getInventory().clear();
        } else {
            System.out.println("Hvem prøver du at kontakte?\n");
        }
    }

    //createRooms metoden instantiere spillets rum, npc'er og items i spillet.
    private void createRooms() {
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
        swag_city.setNPC(new NPC_ID());
        johnny_bravo.setNPC(new NPC_JB());
        michael_jackson.setNPC(new NPC_MJ());
        gulddreng.setNPC(new NPC_GD());
        bjarne_riis.setNPC(new NPC_BR());
        ole_henriksen.setNPC(new NPC_OH());
        mors_hus.setNPC(new NPC_MOR());
        sidney_lee.setNPC(new NPC_SL());
        diskotekets_dør.setNPC(new NPC_DM());
        randers.setNPC(new NPC_RT());

        //Lock condition til udgange
        diskotekets_dør.lockExit("north", false);
        sidney_lee.lockExit("south", true);
    }
}
