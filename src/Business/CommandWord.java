package Business;

public enum CommandWord {
    //æøå understøttes ikke, derfor bruger vi de engelske kommandoer.
    GO("go"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),
    GET("get"), INTERACT("interact"), WALLET("wallet"), SAVE("save"),
    LOAD("load");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public String toString() {
        return commandString;
    }
}
