package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_MJ extends NPC {

    public NPC_MJ(Game game, Player player) {
        super("Michael Jackson", "\u266A\u266A\u266A Annie are you ok? Are you ok, Annie...\u266A\u266A\u266A", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
        case 0:
            interactionState = 1;
            return "Michael Jackson: Troede du jeg var død!? Tro om! Jeg er udødelig!\n"
                    + "Hvis ikke du røber min hemmelighed at jeg er her, må du få mine guldsko.\n"
                    + "Men først, skal du svare på mit spørgsmål! vil du være med? (ja/nej)";
            case 1:
            if (textInput.equalsIgnoreCase("ja")) {
                interactionState = 2;
                return "Michael Jackson: Glimrende! nu skal du høre!\n"
                        + "Hvilket album er det mest solgte i verden?";
            }
            else if (textInput.equalsIgnoreCase("nej")) {
                interactionState = 0;
                return "Michael Jackson: Nå. Jeg har ikke tid til dig alligevel, jeg er kongen af poppen!";
            }
            else {
                interactionState = 0;
                return "Michael Jackson: Ahva? Annie, er du ok?";
            }
            case 2:
                if(textInput.equalsIgnoreCase("thriller")) {
                    interactionState = 3;
                    game.addSwag("Michael Jackson guldsko");
                    game.gameTimer.addTime(60);
                    return "Michael Jackson: Du er en sand fan! Her er mine sko.\n"
                            + "Mission fuldført.";
                }
                else if (!textInput.equalsIgnoreCase("thriller")) {
                    interactionState = 0;
                    return "Michael Jackson: Forkert svar, du er ikke den største fan var?";
                }
            case 3:
                if (game.getSwag("Michael Jacksons guldsko") != null) {
                return "Denne mission er allerede færdiggjort.";
            } else if (game.getSwag("EPO") != null) {
                player.getInventory().clear();
                return "Der blev sagt ingen kommentarer.\n"
                        + "Du snakkede med nogen mens du havde EPO - Game over!\n"
                        + "Tak fordi at du spillede med os, din stodder.";
            }
        default :
            return "";
        }
    }
}