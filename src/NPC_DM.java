
import java.util.Scanner;


public class NPC_DM extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_DM(String name, String greeting) {
        super("Dørmand", "Holdt holdt holdt! Ingen adgang på diskuteket med en så lav swag-promille.");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact_DM() {
        System.out.println("Dørmand: Hey mand! Guldrengen taler til dig. Kunne du ikke tænke dig at hjælpe mig med noget? Det jo trods alt Gulddrengen der spørger. (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Dørmand: Selvfølgelig vil du gerne det, jeg er jo den bedste haha nemt! Kunne du skynde dig til Randers og hente mig en mokai?");
            System.out.println("           min flotte hals trænger til noget væske. (ja/nej)");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("ja")) {
                System.out.println("Dørmand: Sådan bro! Afsted med dig nu, jeg har travlt.\n");
            }
            else if (answer2.equalsIgnoreCase("nej")) {
                System.out.println("Dørmand: Siger du nej til Gulddrengen!? Vi er ikke venner..\n");
            }
            else {
                System.out.println("Dørmand: Hvad snakker du om man!?\n");
            }
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Dørmand: En afvisning!? Det er jeg ikke vant til. Smut med dig nu jeg har travlt.\n");
        }
        else {
            System.out.println("Dørmand: Makker jeg tror ikke vi er på samme bølgelængde\n");
        }
    }
}