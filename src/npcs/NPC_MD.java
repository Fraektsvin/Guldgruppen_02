package npcs;


import java.util.Scanner;


public class NPC_MD extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_MD() {
        super("Mokai dealer", "");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact() {
        System.out.println("Mokai dealer: Jeg leverer de mest friske mokai's du kan få");
        System.out.println("Mokai dealer: Har du guldpenge fra Gulddrengen!?");
        System.out.println("Mokai dealer: Her skynd dig! Tag disse mokai, Gulddrengen kan ikke vente!\n");
        quest1 = true;
    }
}