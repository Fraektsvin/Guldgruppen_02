package Business;

import Acquaintance.IRoom;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private final String name;
    private IRoom currentRoom;
    private int savedTime;
    private int score;
    private int coin;

    private final ArrayList<Coin> pengepung = new ArrayList<>();
    private final ArrayList<Swag> inventory = new ArrayList<>();

    public IRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(IRoom currentRoom) {
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

    public int getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(int savedTime) {
        this.savedTime = savedTime;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public String getName() {
        return name;
    }
}
