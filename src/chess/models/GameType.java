/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models;

/**
 *
 * @author Benjin
 */
public enum GameType {
    VANILLA ("Vanilla", "Regular ol' chess");
    
    private final String name;
    private final String description;

    private GameType (String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String toString () {
        return name;
    }
    
    public String getDescription () {
        return description;
    }
}
