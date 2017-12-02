package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_OH extends NPC {

    public NPC_OH(Game game, Player player) {
        super("Ole Henriksen", "I'm sooo fabolous.", game, player);
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
                return "Ole Henriksen: Ej, en lækker lille basse, der kom ind ad min dør!\n"
                        + "Vil du hjælpe lille mig, sådan lige hurtigt? Jeg skal nok gøre det tiden værd! (ja/nej)";
            case 2:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 3;
                    return "Hvor ER det bare fabulous! Der er den her dørmand, som ser mega lækker ud.\n"
                            + "Giv mig hans nummer, vil du ikke nok? (ja/nej)";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Ole Henriksen: Du er da bare en kedelig gut…\n"
                            + "Men det er ok, du er nuttet nok. Sig til hvis du ændrer mening.";
                } else {
                    interactionState = 0;
                    return "Ole Henriksen: Åh nej, lille stakkel, har du slået hovedet?";
                }
            case 3:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 4;
                    game.addSwag("Seddel fra Ole Henriksen");
                    return "Ole Henriksen: Her er en seddel, som han kan skrive det på. Glæder mig sindssygt meget!";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Ole Henriksen: Nå… Så fik du mine håb op… Nåh, gode tanker! Kom igen hvis du alligevel vil!";
                } else {
                    interactionState = 0;
                    return "Ole Henriksen: Åh nej, lille stakkel, har du slået hovedet?";
                }
            case 4:
                if (game.getSwag("Seddel fra Ole Henriksen") != null) {
                return "Du er allerede på denne mission.";
            } else if (game.getSwag("Fabulous tøj fra Ole Henriksen") != null) {
                return "Denne mission er allerede færdiggjort.";
            } else if (game.getSwag("Dørmandens nummer") != null) {
                game.removeSwag("Dørmandens nummer");
                game.addSwag("Fabulous tøj fra Ole Henriksen");
                game.gameTimer.addTime(60);
                return "Ole Henriksen: Du fik nummeret!?\n"
                        + "Ole Henriksen: Jeg havde aldrig turde håbe på at han kunne være til sådan noget.\n"
                        + "Ole Henriksen: Jaja der kan man se, nogle gange er man heldig! Ej hvor jeg bare er glad nu.\n"
                        + "Ole Henriksen: Her lad mig hjælpe med dit forfærdelige kluns, her får du et rigtigt outfit.\n"
                        + "Mission fuldført.";
            }
            default:
                return "";
        }
    }
}