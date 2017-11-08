
import java.util.Scanner;


public class NPC_DM extends NPC {
    
    Scanner input = new Scanner(System.in);
    
    public NPC_DM(String name, String greeting) {
        super("Dørmand", "Holdt holdt holdt! Ingen adgang på diskoteket med en så lav swag-promille.");
    }
    
    public boolean quest1; {
        quest1 = false;
    }
    public void interact_DM() {
        System.out.println("Dørmand: Vil Ole Henriksen have mit nummer? Kom lige med mig.");
        System.out.println("*I bevæger jer væk fra indgangen til diskuteket*");
        System.out.println("Dørmand: Jeg har aldrig sagt det her til nogen, men ham Olesvesken er squ lidt af en frækkert");
        System.out.println("         med hans små spirrevip-bevægelser huhadada");
        System.out.println("Dørmand: Her, skynd dig at tage mit nummer. Så skal jeg være den rigtige mand i Ole's liv.\n");
        quest1 = true;
    }
}