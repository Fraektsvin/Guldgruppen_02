package Business.NPCs;

import java.util.Scanner;

public class NPC_MJ extends NPC {

    public NPC_MJ() {
        super("Michael Jackson", "\u266A\u266A\u266A Annie are you ok? Are you ok, Annie...\u266A\u266A\u266A");
    }

    @Override
    public void interact(Scanner input) {
        System.out.println("Michael Jackson: A-heehee! Velkommen til mit skjulte fristed! Hvis ikke du røber min hemmelighed at jeg er her, må du få mine guldsko.");
        System.out.println("                 Men først, skal du svare på mit spørgsmål! er du med? (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Michael Jackson: Glimrende! nu skal du høre! Hvilket album er det mest solgte i verden?");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("thriller")) {
                System.out.println("Michael Jackson: Du er en sand fan! Her er mine sko\n");
                setQuest(true);
            } else if (!answer2.equalsIgnoreCase("thriller")) {
                System.out.println("Michael Jackson: Du er ikke den smarteste, er du? Ingen sko til dig.\n");
            }
        } else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Michael Jackson: Du er ikke sjov...\n");
        } else {
            System.out.println("Michael Jackson: Ahva? Annie, er du ok?\n");
        }
    }
}
