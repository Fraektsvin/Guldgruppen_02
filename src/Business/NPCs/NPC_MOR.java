package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_MOR extends NPC {

    public NPC_MOR(Game game, Player player) {
        super("Mor", "Velkommen hjem søn!", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                return "Mor: Hvad er det dog, du har på?\n"
                        + "Skøre barn… Kan du ikke tage de der ting af og komme ind og få et stykke kage? (ja/nej)";
            case 1:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 2;
                    player.getInventory().clear();
                    return "Mor: Super, kom med mig og smag den nybagte kage\n"
                            + "...\n"
                            + "...\n"
                            + "Du faldt i søvn og missede chancen for at battle Sidney Lee";
                }
                else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Mor: Hvordan er det jeg har opdraget dig!?\n"
                            + "Uartige knægt, du skal gøre som mor siger.\n"
                            + "Smut med dig nu, jeg skal gøre rent.";
                }
                else {
                    interactionState = 0;
                    return "Mor: Er det sådan noget nymoderne slang? Snak normalt til mig.";
                }
            case 2:
                if (game.getSwag("EPO") != null) {
                    player.getInventory().clear();
                    return "Der blev sagt ingen kommentarer!\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!";
                }
            default:
                return "";
        }
    }
}