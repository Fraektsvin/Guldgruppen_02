
import java.util.ArrayList;

public class Game {

    private Parser parser;
    private Room currentRoom;

    /*
    Rooms placere vi udenfor 'createRoom' metoden,
    således vi kan tilgå rummene i andre metoder senere.
     */
    Room swag_city, randers, johnny_bravo, mors_hus, gulddreng, bjarne_riis, diskotekets_dør, diskoteket, sidney_lee, hall_fame, ole_henriksen, michael_jackson;

    //Vi opretter en ArrayList til at indeholde vores ting som ligger i inventory.
    ArrayList<Coin> pengepung = new ArrayList<Coin>();
    ArrayList<Swag> inventory = new ArrayList<Swag>();
    
    GameTimer gameTimer = new GameTimer();

    public Game() {
        createRooms();
        parser = new Parser();
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Tak fordi at du spillede med os, din stodder.");
        gameTimer.timerStop();
    }

    //Printer en intro til spillet når spillet startes.
    private void printWelcome() {
        //Intro til spillet
        System.out.println("Velkommen til Swag City!");
        System.out.println("Byen hvor drenge bliver til mænd... eller noget.");
        System.out.println("\n   ----- Introduktion til spillet -----   \n");
        System.out.println("Erik Deluxe er havnet i Swag City.");
        System.out.println("Han er på en mission for at finde byens mest swagste person.");
        System.out.println("På Eriks farefulde færd møder han diverse hjælpere og modstandere,");
        System.out.println("disse kan henholdsvis hjælpe med tips til at vinde over Sidney Lee,");
        System.out.println("som er byens swagster, eller frarøve dig dine swagting.");
        System.out.println("Swagtingene kan opnås fra forksellige bekendtheder som befinder sig i byen,");
        System.out.println("der skal mindst opnås 3 yderlige swagting for at få adgang til byens diskotek,");
        System.out.println("som er opholdsstedet for Sidney Lee.");
        System.out.println("Som start har Erik Deluxe hans verdensberømte swag håndtegn,");
        System.out.println("disse swagting bruges som spillets liv, frarøves alle swagtingene taber man spillet,");
        System.out.println("og for at vinde spillet skal man besejre Sidney Lee i en dancebattle,");
        System.out.println("for at blive byens nye mest swagste person.\n\n");
        System.out.println("Din spilletid er nu gået i gang, du har " + gameTimer.timeRemaining + " sekunder tilbage.");
        System.out.println("For hver mission du klarer får du et minut mere at spille for. Held og lykke!\n");
        System.out.println("Har du brug for hjælp? Skriv '" + CommandWord.HELP + "' hvis du er fuld.\n\n");
        System.out.println(currentRoom.getLongDescription());
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
            case LOOK:
                printLook();
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
            default:
                break;
        }
        return wantToQuit;
    }
        

    //Metode til at fjerne penge fra rummene og tilføje dem til ArrayListen pengepung.
    private void getCoin(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvad vil du have?\n");
            return;
        }

        String coinItemName = command.getSecondWord();
        Coin newCoin = currentRoom.getCoin(coinItemName);

        if (newCoin == null) {
            System.out.println("  Den swag eksistere ikke\n");
        } else {
            pengepung.add(newCoin);
            currentRoom.removeCoin(coinItemName);
            System.out.println("Samlede " + coinItemName + "ne op\n");
        }
    }
    
    //Metode til at tjekke om en item er i ArrayListen inventory.
    private Swag getSwag(String swagName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getSwagDescription().equals(swagName)) {
                return inventory.get(i);
            }
        }
        return null;
    }

    //Printer mulige kommandoer til skærmen.
    private void printHelp() {
        System.out.println("Du helt væk, mokaiens dunst sværmer omkring dig.");
        System.out.println("Tag dig sammen.\n");
        System.out.println("Dine råb om hjælp er:");
        parser.showCommands();
        System.out.println("get (Swagting)  | go (Direction)  | interact (NPC)\n");
    }

    //Kommando implementeret for at giver bedre overblik når spillet spilles.
    private void printLook() {
        //Metode til at inspicere et rum.
        if (currentRoom == swag_city) {
            System.out.println("Du ser en shady type hænge ud ved byskiltet");
            System.out.println("Der ligger et par mønter på vejen\n");
        } else if (currentRoom == randers) {
            System.out.println("Der ligger mokaï strøget udover det hele.");
            System.out.println("Du ser et par fyre fikse deres knallerter\n");
        } else if (currentRoom == mors_hus) {
            System.out.println("Din mor stirrer hysterisk på dig.");
            System.out.println("Hun ser ikke ud til at kunne lide din swag\n");
        } else if (currentRoom == johnny_bravo) {
            System.out.println("Du ser den mest mandlige mandemand stirre dig i øjnene");
            System.out.println("Der ligger et par mønter, nok fra den sidste dulle han tog\n");
        } else if (currentRoom == michael_jackson) {
            System.out.println("Kongen af pop lever stadig, hans død var bare fake news");
            System.out.println("du ser et par mønter liggende på en sten\n");
        } else if (currentRoom == ole_henriksen) {
            System.out.println("Ole Henriksen står fremme i al sin fabulousness");
            System.out.println("Han kigger nysgerrigt på dig, som en løve.");
            System.out.println("...");
            System.out.println("Hvis en løve var gay\n");
        } else if (currentRoom == diskotekets_dør) {
            System.out.println("Dørmanden kigger surt på dig");
            System.out.println("Det er kun de mest swagste folk, som bliver lukket ind");
            System.out.println("Der ligger et par mønter på jorden\n");
        } else if (currentRoom == gulddreng) {
            System.out.println("Gulddrengs pige fans skriger højere end gulddreng synger");
            System.out.println("Han ser ud til at mangle noget");
            System.out.println("Pengene er smidt ud over det hele\n");
        } else if (currentRoom == bjarne_riis) {
            System.out.println("Du er ude på landet. Det eneste, du kan se er Bjarne Riis' skygge");
            System.out.println("Resten af manden er allerede langt over alle bakker");
            System.out.println("Det ligger nogle få mønter på jorden, utroligt Bjarne Riis har overset dem\n");
        } else if (currentRoom == diskoteket) {
            System.out.println("Der er fuckboys og duller galore");
            System.out.println("Der sidder en lækker lille sag oppe ved baren, måske skulle man snakke med hende?");
            System.out.println("Der sidder en rigtig makker nede ved et af bordene. Han vinker dig hen\n");
        } else if (currentRoom == sidney_lee) {
            System.out.println("Sidney Lee står og smørrer sig ind i bruncreme,");
            System.out.println("han virker klar til en gang dans\n");
        }
    }
    
    //Printer ArrayListen inventory's indhold til skærmen.
    private void printInventory() {
        String output = "";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).getSwagDescription() + "\n";
        }
        System.out.println(output);
    }

    //Printer ArrayListen inventory's indhold til skærmen.
    private void printPengepung() {
        String output = "";
        if (pengepung.isEmpty()) {
            output += "Du har ingen mønter\n";
        } else if (pengepung.size() == 1) {
            output += "Du har " + pengepung.size() + " mønt\n";
        } else {
            output += "Du har " + pengepung.size() + " mønter\n";
        }
        System.out.println("Dine mønter:");
        System.out.println(output);
    }

    //Kommando til bevægelse imellem rum, tjekker desuden for låste døre og printer highscore når man vinder.
    private boolean goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvor vil du hen Erik?");
            System.out.println(currentRoom.getMediumDescription() + "\n");
            return false;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Bum! Du løb ind i en væg, drink noget mindre\n");
        } else if (currentRoom.isLocked(direction)) {
            if (inventory.size() > 3) {
                diskotekets_dør.lockExit("north", false);
                System.out.println("Swaggen oser ud af dig! Du er nu klar til diskoteket\n");
            } else {
                System.out.println("Du skal have mere swag for at komme igennem!\n");
            }
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if (currentRoom == sidney_lee) {
                NPC_SL npc_sl = new NPC_SL("", "");
                npc_sl.interact_SL();
                if (npc_sl.quest1 == true) {
                    sidney_lee.lockExit("south", false);
                    System.out.println("\nDu har besejret Sidney Lee, træd venligst ind i Hall of Fame (south exit)\n");
                } else if (npc_sl.quest1 == false) {
                    System.out.println("Du tabte til Sidney Lee - Game over!\n");
                    inventory.clear();
                    return true;
                }
            }
            //if statement som sætter wantToQuit = true, hvilket gør at man vinder spillet.
            if (currentRoom == hall_fame) {
                System.out.println("Du er officielt den mest swagste person!");
                System.out.println("Byen er deres o'høje Erik Deluxe.\n");
                int Score;
                Score = (inventory.size() * 100) + (pengepung.size() * 25);
                System.out.println("Din score er " + Score + " points.\n");
                System.out.println("Du havde " + gameTimer.timeRemaining + " sekunder tilbage.\n");
                return true;
            }
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

    //Checker om inventory er tom, i det tilfælde sættes wantToQuit = true og spillet sluttes.
    private boolean inventoryQuit() {
        if (inventory.isEmpty()) {
            System.out.println("Du har mistet alt dit swag");
            System.out.println("Du er ikke længere værdig til at opholde dig i Swag City\n");
            return true;
        } else {
            return false;
        }
    }
    
    //Metode til at fjerne items fra ArrayListen inventory.
    public void removeSwag(String SwagName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getSwagDescription().equals(SwagName)) {
                inventory.remove(i);
            }
        }
    }

    //Kommando til at interagere med npc'erne
    private void interactNPC(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvem prøver du at kontakte?\n");
        } else if (currentRoom == johnny_bravo && command.getSecondWord().equalsIgnoreCase("johnny bravo")) {
            NPC_JB npc_jb = new NPC_JB("", "");
            if (getSwag("Seddel fra Johnny Bravo") != null) {
                System.out.println("Du er allerede på denne mission.\n");
            } else if (getSwag("Johnny Bravo håret") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("Beatrice's nummer") != null) {
                System.out.println("Johnny Bravo: Du skaffede mig nummeret! Du er en sand guttermand.");
                System.out.println("Johnny Bravo: Her tag min paryk der ligner mit hår på en prik, så kan det være du er heldig hos damerne.\n");
                removeSwag("Beatrice's nummer");
                inventory.add(new Swag("Johnny Bravo håret"));
                System.out.println("Mission fuldført.\n");
                gameTimer.timeRemaining += 60;
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_jb.interact_JB();
                if (npc_jb.quest1 == true) {
                    inventory.add(new Swag("Seddel fra Johnny Bravo"));
                    randers.setNPC("Beatrice", "");
                }
            }
        } else if (currentRoom == randers && command.getSecondWord().equalsIgnoreCase("beatrice")) {
            NPC_BT npc_bt = new NPC_BT("", "");
            if (getSwag("Beatrice's nummer") != null) {
                System.out.println("Du har allerede fået Beatrice's nummer");
                System.out.println("Måske du skulle aflevere det hos Johnny Bravo.\n");
            } else if (getSwag("Johnny Bravo håret") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");  
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_bt.interact_BT();
                if (npc_bt.quest1 == true) {
                    removeSwag("Seddel fra Johnny Bravo");
                    inventory.add(new Swag("Beatrice's nummer"));
                }
            }
        } else if (currentRoom == michael_jackson && command.getSecondWord().equalsIgnoreCase("michael jackson")) {
            NPC_MJ npc_mj = new NPC_MJ("", "");
            if (getSwag("Michael Jacksons guldsko") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_mj.interact_MJ();
                if (npc_mj.quest1 == true) {
                    inventory.add(new Swag("Michael Jacksons guldsko"));
                    System.out.println("Mission fuldført.\n");
                    gameTimer.timeRemaining += 60;
                }
            }
        } else if (currentRoom == gulddreng && command.getSecondWord().equalsIgnoreCase("gulddreng")) {
            NPC_GD npc_gd = new NPC_GD("", "");
            if (getSwag("Guldpenge fra Gulddrengen") != null) {
                System.out.println("Du er allerede på denne mission.\n");
            } else if (getSwag("Gulddreng's guldkæde") != null) {
                System.out.println("Denne mission er allerede færdiggjort.\n");
            } else if (getSwag("Frisk mokai") != null) {
                System.out.println("Gulddreng: En frisk mokai? Sygt god stil! Gulddrengen takker, her tag min guldkæde");
                System.out.println("Hvorfor tænker du måske? Bare fordi jeg kan, nemt.\n");
                removeSwag("Frisk mokai");
                inventory.add(new Swag("Gulddreng's guldkæde"));
                System.out.println("Mission fuldført.\n");
                gameTimer.timeRemaining += 60;
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_gd.interact_GD();
                if (npc_gd.quest1 == true) {
                    inventory.add(new Swag("Guldpenge fra Gulddrengen"));
                    randers.setNPC("Mokai dealer", "");
                }
            }
        } else if (currentRoom == randers && command.getSecondWord().equalsIgnoreCase("mokai dealer")) {
            NPC_MD npc_md = new NPC_MD("", "");
            if (getSwag("Frisk mokai") != null) {
                System.out.println("Du har allerede fået en frisk mokai");
                System.out.println("Måske du skulle aflevere den hos Gulddrengen.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_md.interact_MD();
                if (npc_md.quest1 == true) {
                    removeSwag("Guldpenge fra Gulddrengen");
                    inventory.add(new Swag("Frisk mokai"));
                }
            }
        } else if (currentRoom == bjarne_riis && command.getSecondWord().equalsIgnoreCase("bjarne riis")) {
            NPC_BR npc_br = new NPC_BR("", "");
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
                inventory.add(new Swag("Bjarne Riis's hurtig briller"));
                System.out.println("Mission fuldført.\n");
                gameTimer.timeRemaining += 60;
            } else {
                npc_br.interact_BR();
                if (npc_br.quest1 == true) {
                    inventory.add(new Swag("Seddel fra Bjarne Riis"));
                    swag_city.setNPC("EPO dealer", "");
                }
            }
        } else if (currentRoom == swag_city && command.getSecondWord().equalsIgnoreCase("epo dealer")) {
            NPC_EPO npc_epo = new NPC_EPO("", "");
            if (getSwag("EPO") != null) {
                System.out.println("Du har allerede fået en pose EPO");
                System.out.println("Måske du skulle aflevere den hos Bjarne Riis.\n");
            } else {
                npc_epo.interact_EPO();
                if (npc_epo.quest1 == true) {
                    removeSwag("Seddel fra Bjarne Riis");
                    inventory.add(new Swag("EPO"));
                }
            }
        } else if (currentRoom == ole_henriksen && command.getSecondWord().equalsIgnoreCase("ole henriksen")) {
            NPC_OH npc_oh = new NPC_OH("", "");
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
                inventory.add(new Swag("Fabulous tøj fra Ole Henriksen"));
                System.out.println("Mission fuldført.\n");
                gameTimer.timeRemaining += 60;
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_oh.interact_OH();
                if (npc_oh.quest1 == true) {
                    inventory.add(new Swag("Seddel fra Ole Henriksen"));
                }
            }
        } else if (currentRoom == diskotekets_dør && command.getSecondWord().equalsIgnoreCase("doermand")) {
            NPC_DM npc_dm = new NPC_DM("", "");
            if (getSwag("Seddel fra Ole Henriksen") == null) {
                System.out.println("Smut med dig, jeg har travlt.\n");
            }
            else if (getSwag("Dørmandens nummer") != null) {
                System.out.println("Du har allerede fået dørmandens nummer");
                System.out.println("Måske du skulle aflevere den hos Ole Henriksen.\n");
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            } else {
                npc_dm.interact_DM();
                if (npc_dm.quest1 == true) {
                    removeSwag("Seddel fra Ole Henriksen");
                    inventory.add(new Swag("Dørmandens nummer"));
                }
            }
        } else if (currentRoom == mors_hus && command.getSecondWord().equalsIgnoreCase("mor")) {
            NPC_MOR npc_mor = new NPC_MOR("", "");
            npc_mor.interact_MOR();
            if (npc_mor.quest1 == true) {
                inventory.clear();
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            }
        } else if (currentRoom == randers && command.getSecondWord().equalsIgnoreCase("biver")) {
            NPC_RT npc_rt = new NPC_RT("", "");
            npc_rt.interact_RT();
            if (npc_rt.quest1 == true) {
                inventory.clear();
            } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
            }
        } else if (currentRoom == swag_city && command.getSecondWord().equalsIgnoreCase("info dealer")) {
            System.out.println("Info dealer: Velkommen til Swag City! Jeg kan give dig nogle enkelte informationer.");
            System.out.println("Fra Swag City byskiltet kan du gå");
            System.out.println("east = Randers   |   south = Johnny Bravo   |   north = Diskotekets indgang");
            System.out.println("Resten er op til dig. Held og lykke Erik Deluxe!\n");
        } else if (getSwag("EPO") != null) {
                System.out.println("Der blev sagt ingen kommentarer");
                System.out.println("Du snakkede med nogen mens du havde EPO - Game over!\n");
                inventory.clear();
        } else {
            System.out.println("Hvem prøver du at kontakte?\n");
        }
    }

    //createRooms metoden instantiere spillets rum, npc'er og items i spillet.
    private void createRooms() {
        swag_city = new Room("Du er ved Swag City byskiltet");
        randers = new Room("Du er ved Randers, hjemstedet for Mokaien");
        mors_hus = new Room("Du er hjemme ved mor");
        gulddreng = new Room("Lyden af højt musik. Du er hos Gulddrengen");
        bjarne_riis = new Room("Så går det stærkt, du er hos Bjarne Riis");
        diskotekets_dør = new Room("Høj lyd af bass, festen venter bag diskotekets dør");
        diskoteket = new Room("BOOM BOOM WOOP WOOP PARTY PARTY, du er på diskoteket");
        sidney_lee = new Room("Lugten af selvbruner & foundation fylder lokalet");
        hall_fame = new Room("Du har besejret Sidney Lee, du er en sand cremerider helt");
        ole_henriksen = new Room("Du ser stramme jeans og en tanktop spændt op til lir, totalt fabulous. Hvem mon det er?");
        michael_jackson = new Room("Bam! You've been struck by a smooth criminal");
        johnny_bravo = new Room("Hih hah huh, Johnny Bravo");

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

        currentRoom = swag_city;

        inventory.add(new Swag("Swag håndtegn"));

        //Coins tilføjes til rumene
        johnny_bravo.setCoin(new Coin("penge"));
        swag_city.setCoin(new Coin("penge"));
        diskotekets_dør.setCoin(new Coin("penge"));
        michael_jackson.setCoin(new Coin("penge"));
        gulddreng.setCoin(new Coin("penge"));
        bjarne_riis.setCoin(new Coin("penge"));

        //NPC'er indsættes i de forskellige rum.
        swag_city.setNPC("Info dealer", "Snak med mig hvis du har brug for hjælp.");
        johnny_bravo.setNPC("Johnny Bravo", "HU HA HI, Johnny Bravo!");
        michael_jackson.setNPC("Michael Jackson", "\u266A\u266A\u266A Annie are you ok? Are you ok, Annie...\u266A\u266A\u266A");
        gulddreng.setNPC("Gulddreng", "\u266A\u266A\u266A Er du model? Vil du med på hotel? \u266A\u266A\u266A");
        bjarne_riis.setNPC("Bjarne Riis", "Cykle, cykle, cykle. Ikke tænk på EPO!");
        ole_henriksen.setNPC("Ole Henriksen", "I'm sooo fabolous.");
        mors_hus.setNPC("Mor", "Velkommen hjem søn!");
        sidney_lee.setNPC("Sidney Lee", "Jeg er forlækker til love!");
        diskotekets_dør.setNPC("Doermand", "Holdt holdt holdt! Ingen adgang på diskoteket med en så lav swag-promille.");
        randers.setNPC("Biver", "Jeg er lederen af randers typerne! Vi drikker mokai og spiller hornmusik!");

        //Lock condition til udgange
        diskotekets_dør.lockExit("north", true);
        sidney_lee.lockExit("south", true);
    }
}
