package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_JB extends NPC {

    public NPC_JB(Game game, Player player) {
        super("Johnny Bravo", "HU HA HI, Johnny Bravo!", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                if (game.getSwag("EPO") != null) {
                    return "Der blev sagt ingen kommentarer.\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!\n"
                            + "Tak fordi at du spillede med os, din stodder.";
                }
            case 1:
                interactionState = 2;
                return "Johnny Bravo: HEY HO kammerat! Du virker som en flinker type, "
                        + "kunne du tænke dig at hjælpe Johnny her? (ja/nej)";
            case 2:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 3;
                    return "Johnny Bravo: Konge! Nu skal du høre, jeg har hørt at der er "
                            + "kommet den her foxy lady til Randers ved navn Beatrice,\n"
                            + "kunne du ikke skaffe mig hendes nummer, så jeg kan give hende "
                            + "lidt af Johnny charmen? (ja/nej)";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Johnny Bravo: Det styre du jo også bare selv makker, siger det bare - jeg er en ladykiller!";
                } else {
                    interactionState = 0;
                    return "Johnny Bravo: Brormand jeg tror ikke vi er på samme bølgelængde";
                }
            case 3:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 4;
                    game.addSwag("Seddel fra Johnny Bravo");
                    game.randers.setNPC(new NPC_BT(game, player));
                    return "Johnny Bravo: Sådan skal det lyde! Her får du en seddel som hun kan skrive nummeret på.\n"
                            + "Du møder mig bare her igen når du har skaffet nummeret.\n"
                            + "Held og lykke! HUH HAH JOHNNY BRAVO";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Johnny Bravo: Troede du var en rigtig mand... jeg tog fejl.";
                } else {
                    interactionState = 0;
                    return "Johnny Bravo: Hvad snakker du om man!?";
                }
            case 4:
                if (game.getSwag("Seddel fra Johnny Bravo") != null) {
                    return "Du er allerede på denne mission.";
                } else if (game.getSwag("Johnny Bravo håret") != null) {
                    return "Denne mission er allerede færdiggjort.";
                } else if (game.getSwag("Beatrice's nummer") != null) {
                    game.removeSwag("Beatrice's nummer");
                    game.addSwag("Johnny Bravo håret");
                    game.gameTimer.addTime(60);
                    return "Johnny Bravo: Du skaffede mig nummeret! Du er en sand guttermand.\n"
                            + "Her tag min paryk der ligner mit hår på en prik, så kan det være du er heldig hos damerne.\n\n"
                            + "Mission fuldført";
                }
            default:
                return "";
        }
    }
}
