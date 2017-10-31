
import java.util.Scanner;


public class NPC_SL extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_SL(String name, String greeting) {
        super("Sidney Lee", "Jeg er forlækker til love!");
    }
    public void interact_SL() {
        System.out.println("Sidney Lee: Så du er den nye fyr i byen… Fedt kluns, af en taber. Lad os se om du kan danse! DJ, spin that shit! (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Sidney Lee: Selvfølgelig vil du gerne det, jeg er jo den bedste haha nemt! Kunne du skynde dig til Randers og hente mig en mokai?");
            System.out.println("           min flotte hals trænger til noget væske. (ja/nej)");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("ja")) {
                System.out.println("Sidney Lee: Sådan bro! Afsted med dig nu, jeg har travlt.\n");
            }
            else if (answer2.equalsIgnoreCase("nej")) {
                System.out.println("Sidney Lee: Siger du nej til Gulddrengen!? Vi er ikke venner..\n");
            }
            else {
                System.out.println("Sidney Lee: Hvad snakker du om man!?\n");
            }
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Sidney Lee: En afvisning!? Det er jeg ikke vant til. Smut med dig nu jeg har travlt.\n");
        }
        else {
            System.out.println("Sidney Lee: Makker jeg tror ikke vi er på samme bølgelængde\n");
        }
    }
}