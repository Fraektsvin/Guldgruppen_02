package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_BT extends NPC {

    public NPC_BT(Game game, Player player) {
        super("Beatrice", "", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                game.removeSwag("Seddel fra Johnny Bravo");
                game.addSwag("Beatrice's nummer");
                return "Beatrice: En seddel fra Johnny Bravo!?\n"
                        + "Jeg har hørt en del rygter om ham, måske jeg alligevel skulle finde ud af om de passer.\n"
                        + "Her lad mig skrive mit nummer på seddelen";
            case 1:
                if (game.getSwag("Beatrice's nummer") != null) {
                    return "Du har allerede fået Beatrice's nummer.\n"
                            + "Måske du skulle aflevere det hos Johnny Bravo.";
                } else if (game.getSwag("Johnny Bravo håret") != null) {
                    return "Denne mission er allerede færdiggjort.";
                } else if (game.getSwag("EPO") != null) {
                    player.getInventory().clear();
                    setQuest(true);
                    return "Der blev sagt ingen kommentarer!\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!\n"
                            + "Tak fordi at du spillede med os, din stodder.";
                }
            default:
                return "";
        }
    }
}
