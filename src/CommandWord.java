
public enum CommandWord {
    //æøå understøttes ikke, derfor bruger vi de engelske kommandoer.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),
    GET("get"), INTERACT("interact"), PENGEPUNG("pengepung"), SAVE("save"),
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
