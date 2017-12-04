package Business;

import java.io.Serializable;
import java.util.HashMap;

public class CommandWords implements Serializable{

    private HashMap<String, CommandWord> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    public String showAll() {
        String output = "";
        for (String command : validCommands.keySet()) {
            output += (command + " | ");
        }
        return output;
    }
}
