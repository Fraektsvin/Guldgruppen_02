package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_BR extends NPC {
    
    public NPC_BR(Game game, Player player) {
        super("Bjarne Riis", "Cykle, cykle, cykle. Ikke tænk på EPO!", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                return "Bjarne Riis: Hurtigt! Er du villig til at hjælpe mig? (ja/nej)";
            case 1:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 2;
                    return "Bjarne Riis: Jeg har brug for mere EPO! min dealer er ved swag city byskiltet."
                            + "\nVil du stadig hjælpe mig? I så fald her er en seddel, så han ved, du blev sendt af mig.\n"
                            + "Husk, ingen kommentarer! (ja/nej)";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Bjarne Riis: Nutidens ungdom…";
                } else {
                    interactionState = 0;
                    return "Bjarne Riis: Du har taget for meget EPO, unge mand!";
                }
            case 2:
                if (textInput.equalsIgnoreCase("ja")) {
                    interactionState = 3;
                    game.addSwag("Seddel fra Bjarne Riis");
                    game.swag_city.setNPC(new NPC_EPO(game, player));
                    return "Bjarne Riis: Godt, skynd dig! Tour de france er lige om hjørnet!";
                } else if (textInput.equalsIgnoreCase("nej")) {
                    interactionState = 0;
                    return "Bjarne Riis: I så fald har du ikke hørt noget, ingen kommentarer herfra!";
                } else {
                    interactionState = 0;
                    return "Bjarne Riis: Du har taget for meget EPO, unge mand!";
                }
            case 3:
                if (game.getSwag("Seddel fra Bjarne Riis") != null) {
                return "Du er allerede på denne mission.";
            } else if (game.getSwag("Bjarne Riis's hurtig briller") != null) {
                return "Denne mission er allerede færdiggjort.";
            } else if (game.getSwag("EPO") != null) {
                game.removeSwag("EPO");
                game.addSwag("Bjarne Riis's hurtig briller");
                game.gameTimer.addTime(60);
                return "Bjarne Riis: Skynd dig! Giv mig posen før nogen ser det!\n"
                        + "Bjarne Riis: Mange tak, husk det her er aldrig sket! Du ved intet.\n"
                        + "Bjarne Riis: Her tag mine hurtigbriller fra 96 da jeg vandt Tour de France som tak.\n"
                        + "Bjarne Riis: Snyd eller ej, så er du en sikker vinder! Mission fuldført.";
            }
                return "";
            default:
                return "";
        }
    }
}