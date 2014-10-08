/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.Application;
import chess.DisplayMode;
//import chess.models.UserManager;
import chess.views.terminals.MainMenuTerminal;
import chess.models.messages.Message;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Benjin
 */
public class MainMenuController extends Observable implements Observer {
    public void start () {
        //UserManager.setCurrentUser ("Naruto");
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            MainMenuTerminal terminalView = new MainMenuTerminal ();
            terminalView = new MainMenuTerminal ();
            terminalView.update ();
            
            while (true) {
                Scanner scan = new Scanner (System.in);
                String input = scan.nextLine ();
                //String input = MainMenuController.PLAY;
                input = input.toLowerCase ().trim ();
                
                if (input.equals (MainMenuController.PLAY)) {
                    this.setChanged ();
                    this.notifyObservers (MainMenuController.PLAY);
                    break;
                } else if (input.equals (MainMenuController.QUIT)) {
                    this.setChanged ();
                    this.notifyObservers (MainMenuController.QUIT);
                    break;
                } else {  //invalid input
                    terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                }
            }
        }
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    }
    
    public static final String PLAY = "play";
    public static final String QUIT = "quit";
}
