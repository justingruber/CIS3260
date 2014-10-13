/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.controllers.games.GameController;
import chess.controllers.games.VanillaChessController;
import chess.models.GameType;
import java.util.Observable;
import java.util.Observer;
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
    
    public void goGame (GameType type) {
        GameController gameControl = null;
        
        if (type == GameType.VANILLA) {
            gameControl = new VanillaChessController ();
        }
        
        gameControl.addObserver (this);
        gameControl.start ();
    }
    
    public void goQuit () {
        System.out.println ("BYE");
    }
    
    @Override
    public void update (Observable obj, Object arg) {
        if (obj instanceof MainMenuController) {
            if (arg instanceof GameType) {
                goGame ((GameType) arg);
            } else if (arg.equals (MainMenuController.QUIT)) {
                goQuit ();
            }
        } else if (obj instanceof GameController) {
            if ((int) arg == GameController.QUIT) {
                goMainMenu ();
            }
        }
    }
}

