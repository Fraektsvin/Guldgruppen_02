

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    //æøå understøttes ikke, derfor bruger vi de engelske kommandoer.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), 
    LOOK("look"), GET("get"), INTERACT("interact"), PENGEPUNG("pengepung"), 
    SAVE("save"), LOAD("load");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
