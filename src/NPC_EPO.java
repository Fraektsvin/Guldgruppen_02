
import java.util.Scanner;


public class NPC_EPO extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_EPO(String name, String greeting) {
        super("Dealer", "");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact_EPO() {
        System.out.println("Dealer: Nå det er den tid igen? Jeg sender en regning til ham");
        System.out.println("Dealer: *Giver dig en lille pose med EPO*\n");
        quest1 = true;
    }
}