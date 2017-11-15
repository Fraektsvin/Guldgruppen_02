package npc;

import java.util.Scanner;

public class NPC_EPO extends NPC {

    public NPC_EPO() {
        super("EPO dealer", "");
    }

    public boolean quest1;

    {
        quest1 = false;
    }

    @Override
    public void interact(Scanner input) {
        System.out.println("EPO dealer: Nå det er den tid igen? Jeg sender en regning til ham");
        System.out.println("EPO dealer: *Giver dig en lille pose med EPO*");
        System.out.println("EPO dealer: Husk! Ikke snak med nogen før du har afleveret varen til Bjarne Riis.\n");
        quest1 = true;
    }
}
