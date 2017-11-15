package npcs;

import java.util.Scanner;

public class NPC_BT extends NPC {

    public NPC_BT() {
        super("Beatrice", "");
    }

    @Override
    public void interact(Scanner input) {

        System.out.println("Beatrice: En seddel fra Johnny Bravo!? Jeg har hørt en del rygter om ham, måske jeg alligevel skulle finde ud af om de passer");
        System.out.println("Beatrice: Her lad mig skrive mit nummer på seddelen\n");
        setQuest(true);
    }
}
