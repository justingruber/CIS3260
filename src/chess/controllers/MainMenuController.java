/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.Application;
import chess.DisplayMode;
import chess.models.GameType;
import chess.models.messages.Message;
import chess.views.terminals.MainMenuTerminal;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Benjin
 */
public class MainMenuController extends Observable implements Observer {
    public void start () {
        //UserManager.setCurrentUser ("Naruto");
        this.setChanged ();
                            this.notifyObservers (GameType.values ()[0]);
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            MainMenuTerminal terminalView = new MainMenuTerminal ();
            terminalView = new MainMenuTerminal ();
            terminalView.showTitle ();
            
            while (true) {
                terminalView.update ();
                Scanner scan = new Scanner (System.in);
                String input = scan.nextLine ();
                //String input = MainMenuController.PLAY;
                input = input.toLowerCase ().trim ();
                
                if (terminalView.getState () == MainMenuTerminal.State.MAIN) {
                    if (input.equals (MainMenuController.PLAY)) {
                        terminalView.setState (MainMenuTerminal.State.PLAY);
                    } else if (input.equals (MainMenuController.QUIT)) {
                        this.setChanged ();
                        this.notifyObservers (MainMenuController.QUIT);
                        break;
                    } else {  //invalid input
                        terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                    }
                } else {
                    Pattern pattern = Pattern.compile ("\\d+");
                    Matcher matcher = pattern.matcher (input);
                    
                    if (matcher.find ()) {
                        int num = Integer.parseInt (matcher.group ());
                        
                        if (num >= 1 && num <= GameType.values ().length) {
                            this.setChanged ();
                            this.notifyObservers (GameType.values ()[num - 1]);
                            break;
                        } else {
                            terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                        }
                    } else if (input.equals ("back")) {
                        terminalView.setState (MainMenuTerminal.State.MAIN);
                        terminalView.showTitle ();
                    } else {
                        terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                    }
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
