/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.Collection;

/**
 *
 * @author sigur
 */
public interface IHighScore {
    Collection<IScore> getScores();
    void addScore(IScore newScore);
}
