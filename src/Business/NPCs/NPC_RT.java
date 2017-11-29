package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_RT extends NPC {

    public NPC_RT(Game game, Player player) {
        super("Biver", "Jeg er lederen af randers typerne! Vi drikker mokai og spiller hornmusik!", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                return "Biver: Hey, mand. Fedt kluns! Skal du ikke have en mokaï med os andre? (ja/nej)";
            case 1:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 2;
                    player.getInventory().clear();
                    return "Biver: Fedt, mand! Hop på scooteren, så skal vi til party!\n"
                            + "...\n"
                            + "...\n"
                            + "Du blev fuld og randers typerne tog alt dit swag - Game over!\n"
                            + "Tak fordi at du spillede med os, din stodder.";
                }
                else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Biver: ej, hvor er du nedern, mand!";
                }
                else {
                    interactionState = 0;
                    return "Biver: Makker jeg tror ikke vi er på samme bølgelængde";
                }
            case 2:
                if (game.getSwag("EPO") != null) {
                    player.getInventory().clear();
                    return "Der blev sagt ingen kommentarer!\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!\n"
                            + "Tak fordi at du spillede med os, din stodder.";
                }
            default:
                return "";
        }
    }
}