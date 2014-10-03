/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import java.util.Observer;
import java.util.Observable;
import chess.models.Rules;
import chess.views.terminal.MainMenuTerminal;
/**
 *
 * @author Benjin
 */
public class MasterController implements Observer {
    
    public void goMainMenu () {
        MainMenuController mainMenuControl = new MainMenuController ();
        mainMenuControl.addObserver (this);
        mainMenuControl.start ();
    }
    
    public void goGame (Rules.RuleTypes type) {
        GameController gameControl = new GameController ();
        gameControl.addObserver (this);
        gameControl.start (type);
    }
    
    public void goQuit () {
        System.out.println ("BYE");
    }
    
    @Override
    public void update (Observable object, Object arg) {
        if ((int) arg == MainMenuController.PLAY) {
            goGame (Rules.RuleTypes.VANILLA_CHESS_RULES);
        } else if ((int) arg == MainMenuController.QUIT) {
            goQuit ();
        }
    }
}

