package Business;

import Acquaintance.ICoin;
import Acquaintance.IRoom;
import Business.NPCs.NPC;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class Room implements IRoom {

    private String description;
    private HashMap<String, IRoom> exits;
    private final HashMap<String, Boolean> exitsLock;
    //Vi opretter en ArrayList som kan indeholde de mønter vi placere i de forskellige rum.
    private ArrayList<Coin> coins = new ArrayList<>();
    //Vi opretter et HashMap som kan indeholde npc'er som skal være i de forskellige rum.
    private HashMap<String, NPC> characters;
    

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        characters = new HashMap<>();
        exitsLock = new HashMap<>();
    }

    //Getter metoder:
    @Override
    public String getLongDescription() {
        return description + ".\n" + getExitString();
    }

    //Her skriver vi de ting som skal printes til skærmen ved starten af hvert rum.
    @Override
    public String getExitString() {
        String returnString = "\nNPC'er i rummet:\n";
        returnString += getNPCString();
        returnString += "\nTing i rummet:\n";
        returnString += getRoomCoins();
        returnString += "\nUdgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\n\n";
        return returnString;
    }
    
    @Override
    public IRoom getExit(String direction) {
        return exits.get(direction);
    }

    //Få items fra rummet og systemet kan kende forskel på disse 2 commands ved at se om det er et string eller index
    @Override
    public ICoin getCoin(String CoinName) {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getCoinDescription().equals(CoinName)) {
                return coins.get(i);
            }
        }
        return null;
    }

    //Beskriver hvilke ting der er i rummet.
    public String getRoomCoins() {
        String output = "";
        if (!coins.isEmpty()) {
            for (int i = 0; i < coins.size(); i++) {
                output += coins.get(i).getCoinDescription() + "\n";
            }
            return output;
        } else {
            return output + "Ingen ting!\n";
        }
    }

    //Beskriver hvilke npc'er der er i rummet.
    public String getNPCString() {
        String charactersString = "";
        if (!characters.isEmpty()) {
            Set<String> npcNames = characters.keySet();
            for (String npcName : npcNames) {
                charactersString += getNPC(npcName).getGreeting();
            }
            return charactersString;
        } else {
            return charactersString + "Ingen NPC'er!\n";
        }
    }

    public NPC getNPC(String name) {
        return characters.get(name);
    }

    //Setter metoder:
    //Setter metoden bruges til at indsætte et specifikt item til rummet.
    public void setCoin(Coin newCoin) {
        coins.add(newCoin);
    }

    //Setter motoden bruges til at indsætte en specifik npc i rummet.
    public void setNPC(NPC npc) {
        characters.put(npc.getName(), npc);
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
        exitsLock.put(direction, false);
        //sætter naboen og retningen, som den er mod.
    }

    @Override
    public boolean isLocked(String direction) {
        if (direction != null) {
            return exitsLock.get(direction);
        } else {
            return false;
        }
        
    }

    //låser eller ulåser en exit, sætter retningen, true hvis vejen skal være låst, false hvis ulåst
    public void lockExit(String direction, boolean condition) {
        exitsLock.put(direction, condition);   
    }

    @Override
    public void removeCoin(String CoinName) {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getCoinDescription().equals(CoinName)) {
                coins.remove(i);
            }
        }
    }
}
