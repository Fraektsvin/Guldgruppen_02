
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private final String name;
    private Room currentRoom;
    // Erik forslag:
    private int savedTime;

    private final ArrayList<Coin> pengepung = new ArrayList<>();
    private final ArrayList<Swag> inventory = new ArrayList<>();

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Coin> getPengepung() {
        return pengepung;
    }

    public ArrayList<Swag> getInventory() {
        return inventory;
    }

    public Player(String name) {
        this.name = name;
    }

    public void displayName() {
        System.out.println(name);
    }

    public int getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(int savedTime) {
        this.savedTime = savedTime;
    }
}
