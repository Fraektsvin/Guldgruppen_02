package Acquaintance;

public interface IBusiness {

    String printWelcome();

    String printHelp();

    String printInventory();

    String printPengepung();
    
    String goToDirection(String direction);
    
    String interactWith(String npc);
    
    String whichNPC();
    
    String getCoin(String coin);
}
