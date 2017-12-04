package Business.NPCs;

import Business.Game;
import Business.Player;
import java.io.Serializable;

public abstract class NPC implements Serializable {

    protected Game game;
    protected Player player;
    protected int interactionState = 0;
    private String name;
    private String greeting;
    private boolean quest = false;

    public NPC(String name, String greeting, Game game, Player player) {
        this.name = name;
        this.greeting = greeting;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public String getGreeting() {
        return name + ": " + greeting + "\n";
    }

    public boolean isQuest() {
        return quest;
    }

    public void setQuest(boolean quest) {
        this.quest = quest;
    }
    
    public abstract String interact(String textInput);
}
