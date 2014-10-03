/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import java.util.Observable;
import java.util.Observer;
import chess.models.Rules;
import chess.models.Game;
import chess.models.UserManager;
import chess.views.*;

/**
 *
 * @author Benjin
 */
public class GameController extends Observable implements Observer {
    private Game game;
    private GameView view;
    
    public void start (Rules.RuleTypes type) {
        game = new Game ();
        game.AddUser (UserManager.getCurrentUser ());
        
        if (type == Rules.RuleTypes.VANILLA_CHESS_RULES) {
            view = new VanillaBoardView ();
        }
        
        view.addObserver (this);
        view.start ();
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    }
}
