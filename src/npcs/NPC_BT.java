package npcs;


import java.util.Scanner;


public class NPC_BT extends NPC {

    Scanner input = new Scanner(System.in);
    
    public NPC_BT() {
        super("Beatrice", "");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact() {        
        
        System.out.println("Beatrice: En seddel fra Johnny Bravo!? Jeg har hørt en del rygter om ham, måske jeg alligevel skulle finde ud af om de passer");
        System.out.println("Beatrice: Her lad mig skrive mit nummer på seddelen\n");
        quest1 = true;
    }
}
