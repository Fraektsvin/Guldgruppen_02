package Business.NPCs;

import java.util.Scanner;

public class NPC_BR extends NPC {
    
    public NPC_BR() {
        super("Bjarne Riis", "Cykle, cykle, cykle. Ikke tænk på EPO!");
    }
    
    @Override
    public void interact(Scanner input) {
        System.out.println("Bjarne Riis: Hurtigt! Er du villig til at hjælpe mig? (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Bjarne Riis: Jeg har brug for mere EPO! min dealer er ved swag city byskiltet. Vil du hjælpe mig?");
            System.out.println("I så fald her er en seddel, så han ved, du blev sendt af mig. Husk, ingen kommentarer! (ja/nej)");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("ja")) {
                System.out.println("Bjarne Riis: Godt, skynd dig! Tour de france er lige om hjørnet!\n");
                setQuest(true);
            } else if (answer2.equalsIgnoreCase("nej")) {
                System.out.println("Bjarne Riis: I så fald har du ikke hørt noget, ingen kommentarer herfra!\n");
            } else {
                System.out.println("Bjarne Riis: Du har taget for meget EPO, unge mand!\n");
            }
        } else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Bjarne Riis: Nutidens ungdom…\n");
        } else {
            System.out.println("Bjarne Riis: Du har taget for meget EPO, unge mand!\n");
        }
    }
}
