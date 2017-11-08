package npcs;

import java.io.Serializable;
import java.util.Scanner;


public abstract class NPC implements Serializable {

    private String name;
    private String greeting;
    private boolean quest = false;


    public NPC(String name, String greeting) {
        this.name = name;
        this.greeting = greeting;
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
    
    public abstract void interact(Scanner scanner);
}
