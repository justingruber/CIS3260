/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import java.util.Observer;
import java.util.Observable;
import chess.models.Rules;
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
    
    @Override
    public void update (Observable object, Object arg) {
        if (arg == MainMenuController.PLAY) {
            goGame (Rules.RuleTypes.VANILLA_CHESS_RULES);
        } else if (arg == MainMenuController.QUIT) {
            System.out.println ("BYE");
        }
    }
}
