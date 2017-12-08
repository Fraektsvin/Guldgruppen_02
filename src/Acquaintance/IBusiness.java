package Acquaintance;

public interface IBusiness {

    String printWelcome();

    String printHelp();

    String printInventory();

    String printWallet();

    String goToDirection(String direction);

    String interactWith(String npc, String textInput);

    String whichNPC();

    String getCoin(String coin);

    void timerStart();

    void timerStop();

    boolean questQuit();

    boolean winQuit();

    String savePlayer();

    String loadPlayer();
    
    String score();
    
    String time();
}
