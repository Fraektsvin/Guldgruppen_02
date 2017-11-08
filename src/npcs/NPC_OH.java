package npcs;


import java.util.Scanner;


public class NPC_OH extends NPC {
    
    
    public NPC_OH() {
        super("Ole Henriksen", "I'm sooo fabolous.");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact(Scanner input) {
        System.out.println("Ole Henriksen: Ej, en lækker lille basse, der kom ind ad min dør! Vil du hjælpe lille mig, sådan lige hurtigt? Jeg skal nok gøre det tiden værd! (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Hvor ER det bare fabulous! Der er den her dørmand, som ser mega lækker ud. Giv mig hans nummer, vil du ikke nok? (ja/nej)");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("ja")) {
                System.out.println("Ole Henriksen: Her er en seddel, som han kan skrive det på. Glæder mig sindssygt meget!\n");
                quest1 = true;
            }
            else if (answer2.equalsIgnoreCase("nej")) {
                System.out.println("Ole Henriksen: Nå… Så fik du mine håb op… Nåh, gode tanker! Kom igen hvis du alligevel vil!\n");
            }
            else {
                System.out.println("Ole Henriksen: Åh nej, lille stakkel, har du slået hovedet?\n");
            }
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Ole Henriksen: Du er da bare en kedelig gut… Men det er ok, du er nuttet nok. Sig til hvis du ændrer mening.\n");
        }
        else {
            System.out.println("Ole Henriksen: Åh nej, lille stakkel, har du slået hovedet?\n");
        }
    }
}