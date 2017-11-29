package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_EPO extends NPC {
    
    public NPC_EPO(Game game, Player player) {
        super("EPO dealer", "", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                game.removeSwag("Seddel fra Bjarne Riis");
                game.addSwag("EPO");
                return "EPO dealer: Nå det er den tid igen? Jeg sender en regning til ham.\n"
                        + "EPO dealer: *Giver dig en lille pose med EPO*.\n"
                        + "EPO dealer: Husk! Ikke snak med nogen før du har afleveret varen til Bjarne Riis.";
            case 1:
                if (game.getSwag("EPO") != null) {
                return "Du har allerede fået en pose EPO.\n"
                        + "Måske du skulle aflevere den hos Bjarne Riis.\n"
                        + "Tak fordi at du spillede med os, din stodder.";
            }
            default:
                return "";
        }
    }
}