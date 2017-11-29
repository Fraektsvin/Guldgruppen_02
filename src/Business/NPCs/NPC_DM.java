package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_DM extends NPC {

    public NPC_DM(Game game, Player player) {
        super("Doermand", "Holdt holdt holdt! Ingen adgang på diskoteket med en så lav swag-promille.", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                game.removeSwag("Seddel fra Ole Henriksen");
                game.addSwag("Dørmandens nummer");
                return "Dørmand: Vil Ole Henriksen have mit nummer? Kom lige med mig.\n"
                        + "*I bevæger jer væk fra indgangen til diskuteket*.\n"
                        + "Dørmand: Jeg har aldrig sagt det her til nogen, men ham Olesvesken er squ lidt af en frækkert\n"
                        + "med hans små spirrevip-bevægelser huhadada.\n"
                        + "Dørmand: Her, skynd dig at tage mit nummer. Så skal jeg være den rigtige mand i Ole's liv.";
            case 1:
                if (game.getSwag("Seddel fra Ole Henriksen") == null) {
                    return "Smut med dig, jeg har travlt.";
                } else if (game.getSwag("Dørmandens nummer") != null) {
                    return "Du har allerede fået dørmandens nummer.\n"
                            + "Måske du skulle aflevere den hos Ole Henriksen.";
                } else if (game.getSwag("EPO") != null) {
                    player.getInventory().clear();
                    return "Der blev sagt ingen kommentarer.\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!\n"
                            + "Tak fordi at du spillede med os, din stodder.";
                }
            default:
                return "";
        }
    }
}
