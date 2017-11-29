package Business.NPCs;

import Business.Game;
import Business.Player;

public class NPC_SL extends NPC {

    public NPC_SL(Game game, Player player) {
        super("Sidney Lee", "Jeg er forlækker til love!", game, player);
    }

    @Override
    public String interact(String textInput) {
        switch (interactionState) {
            case 0:
                interactionState = 1;
                return "Sidney Lee: Så du er den nye fyr i byen… Fedt kluns, af en taber.\n"
                        + "Lad os se hvad du dur til! DJ, spin that shit!\n"
                        + "Sidney Lee: Hvor mange af kendisserne som opholder sig i Swag City\n"
                        + "kender jeg personligt? (1, 2, 3, 4 eller 5)";
            case 1:
                if (textInput.equalsIgnoreCase("3")) {
                    interactionState = 2;
                    game.sidney_lee.lockExit("south", false);
                    return "Sidney Lee: Pokkers du svarede korrekt...\n"
                            + "Sidney Lee: Jeg overgiver mig, jeg er besejret.\n"
                            + "Du har besejret Sidney Lee, træd venligst ind i Hall of Fame (south exit)";
                }
                else {
                    player.getInventory().clear();
                    setQuest(false);
                    return "Sidney Lee: Ha-ha-ha-ha det var jo nemmere end at spille Tekken med bind for øjnene.\n"
                            + "Sidney Lee: Smut ud af min by! Du hører ikke til her.\n"
                            + "Du tabte til Sidney Lee - Game over!";
                    
                }
            case 2:
                if (game.getSwag("EPO") != null) {
                    player.getInventory().clear();
                    return "Der blev sagt ingen kommentarer!\n"
                            + "Du snakkede med nogen mens du havde EPO - Game over!";
                }
            default:
                return "";
        }
    }
}