package npcs;

import java.util.Scanner;

public class NPC_ID extends NPC {
    
    public NPC_ID() {
        super("Info dealer", "");
    }
    
    @Override
    public void interact(Scanner input) {
        System.out.println("Info dealer: Velkommen til Swag City! Jeg kan give dig nogle enkelte informationer.");
        System.out.println("Fra Swag City byskiltet kan du gå");
        System.out.println("east = Randers   |   south = Johnny Bravo   |   north = Diskotekets indgang");
        System.out.println("Resten er op til dig. Held og lykke Erik Deluxe!\n");
        setQuest(true);
    }
}
