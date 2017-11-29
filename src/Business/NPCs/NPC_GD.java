package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_GD extends NPC {

    public NPC_GD(Game game, Player player) {
        super("Gulddreng", "\u266A\u266A\u266A Er du model? Vil du med på hotel? \u266A\u266A\u266A", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                return "Gulddreng: Hey mand! Guldrengen taler til dig. Kunne du ikke tænke dig at hjælpe mig med noget?\n"
                        + "Det jo trods alt Gulddrengen der spørger. (ja/nej)";
            case 1:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 2;
                    return "Gulddreng: Selvfølgelig vil du gerne det, jeg er jo den bedste haha nemt!\n"
                            + "Kunne du skynde dig til Randers og hente mig en helt frisk mokai?\n"
                            + "Min flotte hals trænger til noget væske. (ja/nej)";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Gulddreng: En afvisning!? Det er jeg ikke vant til.\n"
                            + "Smut med dig nu jeg har travlt.";
                } else {
                    interactionState = 0;
                    return "Gulddreng: Makker jeg tror ikke vi er på samme bølgelængde";
                }
            case 2:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 3;
                    game.addSwag("Guldpenge fra Gulddrengen");
                    game.randers.setNPC(new NPC_MD(game, player));
                    return "Gulddreng: Sådan bro! Her tag mine guldpenge det burde være rigeligt.\n"
                            + "Afsted med dig nu, jeg har travlt.";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Gulddreng: Siger du nej til Gulddrengen!? Vi er ikke venner..";
                } else {
                    interactionState = 0;
                    return "Gulddreng: Hvad snakker du om man!?";
                }
            case 3:
                if (game.getSwag("Guldpenge fra Gulddrengen") != null) {
                    return "Du er allerede på denne mission.";
                } else if (game.getSwag("Gulddreng's guldkæde") != null) {
                    return "Denne mission er allerede færdiggjort.";
                } else if (game.getSwag("Frisk mokai") != null) {
                    game.removeSwag("Frisk mokai");
                    game.addSwag("Gulddreng's guldkæde");
                    game.gameTimer.addTime(60);
                    return "Gulddreng: En frisk mokai? Sygt god stil! Gulddrengen takker, her tag min guldkæde.\n"
                            + "Hvorfor tænker du måske? Bare fordi jeg kan, nemt.\n"
                            + "Mission fuldført.";
                } else if (game.getSwag("EPO") != null) {
                    player.getInventory().clear();
                    return "Der blev sagt ingen kommentarer.\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!";
                }
                return "";
            default:
                return "";
        }
    }
}
