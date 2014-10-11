/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models.games;

import chess.models.Board;
import chess.models.Rules;
import chess.models.User;

import chess.models.messages.Message;

import java.util.ArrayList;
/**
 *
 * @author Benjin
 */
public abstract class Game {
    private Rules rules;
    
    public abstract void addUser (User user);
    
    public Board getBoard () {
        return rules.getBoardInstance ();
    }
    
    public ArrayList <Message> getMessages () {
        return rules.getMessages ();
    }
    
    public User getCurrentMover () {
        return rules.getCurrentMover ();
    }
    
    public void setRules (Rules rules) {
        this.rules = rules;
    }
    
    public Rules getRules () {
        return rules;
    }
}
