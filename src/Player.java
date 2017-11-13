
import java.util.ArrayList;

public class Player {

    private final String name;
    private Room currentRoom;
    private int score; 

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
     public int getScore() {
        return score;
    }
     public void setScore(int score) {
        this.score = score;
    }
}