package npcs;


import java.util.Scanner;


public class NPC_JB extends NPC {

   
    
    public NPC_JB() {
        super("Johnny Bravo", "HU HA HI, Johnny Bravo!");
    }

    public void interact(Scanner input) {
        System.out.println("Johnny Bravo: HEY HO kammerat! Du virker som en flinker type, kunne du tænke dig at hjælpe Johnny her? (ja/nej)");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("ja")) {
            System.out.println("Johnny Bravo: Konge! Nu skal du høre, jeg har hørt at der er kommet den her foxy lady til Randers ved navn Beatrice,");
            System.out.println("              kunne du ikke skaffe mig hendes nummer, så jeg kan give hende lidt af Johnny charmen? (ja/nej)");
            String answer2 = input.nextLine();
            if (answer2.equalsIgnoreCase("ja")) {
                System.out.println("Johnny Bravo: Sådan skal det lyde! Her får du en seddel som hun kan skrive nummeret på.");
                System.out.println("Du møder mig bare her igen når du har skaffet nummeret. Held og lykke! HUH HAH JOHNNY BRAVO\n");
                quest1 = true;
            }
            else if (answer2.equalsIgnoreCase("nej")) {
                System.out.println("Johnny Bravo: Troede du var en rigtig mand... jeg tog fejl.\n");
            }
            else {
                System.out.println("Johnny Bravo: Hvad snakker du om man!?\n");
            }
        }
        else if (answer1.equalsIgnoreCase("nej")) {
            System.out.println("Johnny Bravo: Det styre du jo også bare selv makker, siger det bare - jeg er en ladykiller!\n");
        }
        else {
            System.out.println("Johnny Bravo: Brormand jeg tror ikke vi er på samme bølgelængde\n");
        }
    }
    
    public boolean quest1; {
        quest1 = false;
    }
}
