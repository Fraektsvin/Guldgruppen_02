
import java.util.Scanner;


public class NPC_MOR extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_MOR(String name, String greeting) {
        super("Mor", "Velkommen hjem søn!");
    }
    public void interact_MOR() {
        System.out.println("Mor: Hvad er det dog, du har på? Skøre barn… Kan du ikke tage den der ting af og komme ind og få et stykke kage? (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Mor: Super, kom med mig og smag den nybagte kage\n");
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Mor: Hvordan er det jeg har opdraget dig!? Uartige knægt, du skal gøre som mor siger. Smut med dig nu, jeg skal gøre rent.\n");
        }
        else {
            System.out.println("Mor: Er det sådan noget nymoderne slang? Snak normalt til mig.\n");
        }
    }
}