
import java.util.Scanner;


public class NPC_RT extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_RT(String name, String greeting) {
        super("Biver", "Jeg er lederen af randers typerne! Vi drikker mokai og spiller hornmusik!");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact_RT() {
        System.out.println("Biver: Hey, mand. Fedt kluns! Skal du ikke have en mokaï med os andre? (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Biver: Fedt, mand! Hop på scooteren, så skal vi til party!");
            System.out.println("...");
            System.out.println("...");
            System.out.println("Du blev fuld og randers typerne tog alt dit swag\n");
            quest1 = true;
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Biver: ej, hvor er du nedern, mand!\n");
        }
        else {
            System.out.println("Biver: Makker jeg tror ikke vi er på samme bølgelængde\n");
        }
    }
}