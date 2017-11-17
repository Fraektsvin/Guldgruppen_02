package Business.NPCs;

import java.util.Scanner;

public class NPC_SL extends NPC {

    public NPC_SL() {
        super("Sidney Lee", "Jeg er forlækker til love!");
    }

    @Override
    public void interact(Scanner input) {
        System.out.println("Sidney Lee: Så du er den nye fyr i byen… Fedt kluns, af en taber. Lad os se hvad du dur til! DJ, spin that shit!");
        System.out.println("Sidney Lee: Hvor mange af kendisserne som opholder sig i Swag City kender jeg personligt? (1, 2, 3, 4 eller 5)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("3")) {
            System.out.println("Sidney Lee: Pokkers du svarede korrekt...");
            System.out.println("Sidney Lee: Jeg overgiver mig, jeg er besejret.");
            setQuest(true);
        } else {
            System.out.println("Sidney Lee: Ha-ha-ha-ha det var jo nemmere end at spille Tekken med bind for øjnene\n");
            System.out.println("Sidney Lee: Smut ud af min by! Du hører ikke til her");
            setQuest(false);
        }
    }
}
