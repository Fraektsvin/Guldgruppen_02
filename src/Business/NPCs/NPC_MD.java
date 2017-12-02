package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_MD extends NPC {
    
    public NPC_MD(Game game, Player player) {
        super("Mokai dealer", "", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                if (game.getSwag("EPO") != null) {
                    setQuest(true);
                    return "Der blev sagt ingen kommentarer.\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!\n"
                            + "Tak fordi at du spillede med os, din stodder.";
                }
            case 1:
                interactionState = 2;
                game.removeSwag("Guldpenge fra Gulddrengen");
                game.addSwag("Frisk mokai");
                return "Mokai dealer: Jeg leverer de mest friske mokai's du kan få!\n"
                        + "Har du guldpenge fra Gulddrengen!? Her skynd dig!\n"
                        + "Tag denne mokai, Gulddrengen kan ikke vente!";
            case 2:
                if (game.getSwag("Frisk mokai") != null) {
                return "Du har allerede fået en frisk mokai.\n"
                        + "Måske du skulle aflevere den hos Gulddrengen.";
            }
            default:
                return "";
        }
    }
}