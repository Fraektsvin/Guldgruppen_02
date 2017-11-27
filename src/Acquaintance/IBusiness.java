package Acquaintance;

public interface IBusiness {

    String printWelcome();

    String printHelp();

    String printInventory();

    String printWallet();
    
    String goToDirection(String direction);
    
    String interactWith(String npc);
    
    String whichNPC();
    
    String getCoin(String coin);

    public String printPengepung();
}
