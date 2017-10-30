import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

public class Room {

    private String description;
    private HashMap<String, Room> exits;
    //Vi opretter en ArrayList som kan indeholde de ting vi placere i de forskellige rum.
    ArrayList<Swag> swags = new ArrayList<Swag>();
    //Vi opretter et HashMap som kan indeholde npc'er som skal være i de forskellige rum.
    private HashMap<String, NPC> characters;
    private HashMap<String, Boolean> exitsLock;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        characters = new HashMap<String, NPC>();
        exitsLock = new HashMap<String, Boolean>();
    }

    //Getter metoder:
    public String getShortDescription() {
        return description;
    }
    
    
    public String getMediumDescription() {
        return getExitStringToGo();
    }
    
    
    public String getLongDescription() {
        return description + ".\n" + getExitString();
    }
    
    //Her skriver vi de ting som skal printes til skærmen ved starten af hvert rum.
    private String getExitString() {
        String returnString = "\nNPC'er i rummet:\n";
        returnString += getNPCString();
        returnString += "\nSwagting i rummet:\n";
        returnString += getRoomSwags() + "\n";
        returnString += "\nUdgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\n";
        return returnString;
    }
    
    
    private String getExitStringToGo() {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    
    public Swag getSwag(int index) {
        return swags.get(index);
        
    }

    //Få items fra rummet og systemet kan kende forskel på disse 2 commands ved at se om det er et string eller index
    public Swag getSwag(String SwagName) {
        for (int i = 0; i < swags.size(); i++) {
            if(swags.get(i).getSwagDescription().equals(SwagName))    {
                return swags.get(i);
            }
                      
        }
        return null;
        
    }

        //Beskriver hvilke ting der er i rummet.
    public String getRoomSwags() {
        String output = "";
        if(!swags.isEmpty()) {
        for (int i = 0; i < swags.size(); i++) {
            output += swags.get(i).getSwagDescription() + ". ";
        }
        return output;
        }
        else {
            return output + "Ingen swagting!";
        }
    }
    
    //Beskriver hvilke npc'er der er i rummet.
    public String getNPCString() {
        String charactersString = "";
        if(!characters.isEmpty()) {
            Set<String> npcNames = characters.keySet();
            for(String npcName : npcNames) {
                charactersString += getNPC(npcName).getGreeting();
            }
            return charactersString;
        }
        else {
            return charactersString + "Ingen NPC'er!\n";
        }
    }
    
    
    public NPC getNPC(String name) {
        return characters.get(name);
    }
    
    //Setter metoder:
    //Setter metoden bruges til at indsætte et specifikt item til rummet.
    public void setSwag(Swag newSwag){
       swags.add(newSwag);
    }
    
    //Setter motoden bruges til at indsætte en specifik npc i rummet.
    public void setNPC(String name, String description) {
        characters.put(name, new NPC(name, description));
    }
  
    
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
        exitsLock.put(direction, false);
        //sætter naboen og retningen, som den er mod.
    }
    
    
    public boolean isLocked(String direction){
        if (direction!= null)
            return exitsLock.get(direction).booleanValue();
        else
            return false;
        //returnerer om et rum er låst eller ej, sætter true hvis døren er låst, false hvis åben
    }
    
    
    public void lockExit(String direction, boolean condition){
        exitsLock.put(direction, condition);
    //låser eller ulåser en exit, sætter retningen, true hvis vejen skal være låst, false hvis ulåst
    }
    
    
    public void removeSwag(String SwagName) {
        for (int i = 0; i < swags.size(); i++) {
            if(swags.get(i).getSwagDescription().equals(SwagName))    {
            swags.remove(i);
            }
                      
        }       
    }
}
