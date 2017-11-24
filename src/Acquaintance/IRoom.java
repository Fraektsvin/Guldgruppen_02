/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

/**
 *
 * @author sigur
 */
public interface IRoom {
    String getExitString();
    IRoom getExit(String direction);
    String getLongDescription();
    String getMediumDescription();
    ICoin getCoin(String CoinName);
    void removeCoin(String CoinName);
    boolean isLocked(String direction);
}
