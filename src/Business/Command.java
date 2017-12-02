package Business;

public class Command {

    private final CommandWord commandWord;
    private final String secondWord;

    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }
}