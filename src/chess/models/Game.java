/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models;

import java.util.ArrayList;
/**
 *
 * @author Benjin
 */
public class Game {
    private User playerWhite;
    private User playerBlack;
    
    public Game () {
        
    }
    
    public void AddUser (User user) {
        if (playerWhite == null) {
            playerWhite = user;
        } else if (playerBlack == null) {
            playerBlack = user;
        }
    }
}
