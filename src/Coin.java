
import java.util.Arrays;

public class Coin {
    private final int value = 10;
    private final String name = "penge";

    public Coin(int value, String name) {
    }

    String coinDescription;
    
    //Der oprettes en string som kan indeholde navnet til items(coins) i spillet.
    public Coin(String newCoinDescription) {
        
        coinDescription = newCoinDescription;
    }
    
    public String getCoinDescription() {
        return coinDescription;
    } 
}