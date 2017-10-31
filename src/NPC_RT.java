
import java.util.Scanner;


public class NPC_RT extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_RT(String name, String greeting) {
        super("Biver", "Holdt holdt holdt! Ingen adgang på diskuteket med en så lav swag-promille.");
    }
    public void interact_RT() {
        System.out.println("Biver: Hey, mand. Fedt kluns! Skal du ikke have en mokaï med os andre? (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Biver: Fedt, mand! Hop på scooteren, så skal vi til party!\n");
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Biver: ej, hvor er du nedern, mand!\n");
        }
        else {
            System.out.println("Biver: Makker jeg tror ikke vi er på samme bølgelængde\n");
        }
    }
}