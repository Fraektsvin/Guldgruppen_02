package Acquaintance;

import java.io.Serializable;

public interface IRoom extends Serializable {

    String getExitString();

    IRoom getExit(String direction);

    String getLongDescription();

    ICoin getCoin(String CoinName);

    void removeCoin(String CoinName);

    boolean isLocked(String direction);
}
