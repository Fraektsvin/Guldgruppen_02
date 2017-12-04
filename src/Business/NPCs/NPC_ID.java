package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_ID extends NPC {

    public NPC_ID(Game game, Player player) {
        super("Info dealer", "", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                return "Info dealer: Velkommen til Swag City!\n"
                        + "Jeg kan give dig nogle enkelte informationer. Fra Swag City byskiltet kan du gå.\n"
                        + "east = Randers   |   south = Johnny Bravo   |   north = Diskotekets indgang.\n"
                        + "Resten er op til dig.\n"
                        + "Held og lykke Erik Deluxe!";
            case 1:
                if (game.getSwag("EPO") != null) {
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
