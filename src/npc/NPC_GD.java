package npc;

import java.util.Scanner;

public class NPC_GD extends NPC {

    public NPC_GD() {
        super("Gulddreng", "\u266A\u266A\u266A Er du model? Vil du med på hotel? \u266A\u266A\u266A");
    }

    public boolean quest1;

    {
        quest1 = false;
    }

    @Override
    public void interact(Scanner input) {
        System.out.println("Gulddreng: Hey mand! Guldrengen taler til dig. Kunne du ikke tænke dig at hjælpe mig med noget? Det jo trods alt Gulddrengen der spørger. (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Gulddreng: Selvfølgelig vil du gerne det, jeg er jo den bedste haha nemt! Kunne du skynde dig til Randers og hente mig en mokai?");
            System.out.println("           min flotte hals trænger til noget væske. (ja/nej)");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("ja")) {
                System.out.println("Gulddreng: Sådan bro! Her tag mine guldpenge det burde være rigeligt. Afsted med dig nu, jeg har travlt.\n");
                quest1 = true;
            } else if (answer2.equalsIgnoreCase("nej")) {
                System.out.println("Gulddreng: Siger du nej til Gulddrengen!? Vi er ikke venner..\n");
            } else {
                System.out.println("Gulddreng: Hvad snakker du om man!?\n");
            }
        } else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Gulddreng: En afvisning!? Det er jeg ikke vant til. Smut med dig nu jeg har travlt.\n");
        } else {
            System.out.println("Gulddreng: Makker jeg tror ikke vi er på samme bølgelængde\n");
        }
    }
}
