package npcs;

import java.util.Scanner;

public class NPC_MD extends NPC {
    
    public NPC_MD() {
        super("Mokai dealer", "");
    }
    
    @Override
    public void interact(Scanner input) {
        System.out.println("Mokai dealer: Jeg leverer de mest friske mokai's du kan få");
        System.out.println("Mokai dealer: Har du guldpenge fra Gulddrengen!?");
        System.out.println("Mokai dealer: Her skynd dig! Tag disse mokai, Gulddrengen kan ikke vente!\n");
        setQuest(true);
    }
}
