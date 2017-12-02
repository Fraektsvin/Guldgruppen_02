package Business;

import Acquaintance.ICoin;
import java.io.Serializable;

public class Coin implements ICoin, Serializable {

    String coinDescription;
    private final int VALUE = 25;

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
