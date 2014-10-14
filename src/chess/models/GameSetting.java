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
public enum GameSetting {
    PROMOTION ("Promotion"),
    CASTLING ("Castling"),
    EN_PASSANT ("En Passant"),
    DRAW_BY_AGREEMENT ("Draw by Agreement"),
    STALEMATE ("Stalemate"),
    FIFTY_MOVE_RULE ("Fifty-move Rule"),
    ;
    
    private final String name;
    
    private GameSetting (String name) {
        this.name = name;
    }
    
    @Override
    public String toString () {
        return name;
    }
}