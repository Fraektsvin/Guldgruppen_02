package Business;

import Acquaintance.ICoin;
import java.io.Serializable;

public class Coin implements Serializable, ICoin {

    public Coin(int value, String name) {
    }

    String coinDescription;
    private final int VALUE = 25;

    //Der oprettes en string som kan indeholde navnet til items(coins) i spillet.
    public Coin(String newCoinDescription) {
        coinDescription = newCoinDescription;
    }

    @Override
    public String getCoinDescription() {
        return coinDescription;
    }

    public int getVALUE() {
        return VALUE;
    }
}
