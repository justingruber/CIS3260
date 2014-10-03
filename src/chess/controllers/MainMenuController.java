/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.Application;
import chess.DisplayMode;
import chess.models.Rules;
import chess.models.Game;
import chess.models.UserManager;
import chess.views.MainMenuView;
import chess.views.terminal.MainMenuTerminal;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Benjin
 */
public class MainMenuController extends Observable implements Observer {
    private MainMenuView view;
    
    public void start () {
        UserManager.setCurrentUser ("Naruto");
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            MainMenuTerminal terminalView = new MainMenuTerminal ();
            terminalView = new MainMenuTerminal ();
            terminalView.update ();
            String input = terminalView.getInput ();
            
            if (input == MainMenuTerminal.PLAY) {
                this.setChanged ();
                this.notifyObservers (MainMenuController.PLAY);
            } else if (input == MainMenuTerminal.QUIT) {
                this.setChanged ();
                this.notifyObservers (MainMenuController.QUIT);
            }
        }
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    
    }
    public static final int PLAY = 1;
    public static final int QUIT = 0;
}
