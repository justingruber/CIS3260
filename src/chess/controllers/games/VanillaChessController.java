/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers.games;

import chess.Application;
import chess.DisplayMode;
import chess.models.User;
import chess.models.games.VanillaChessGame;
import chess.models.messages.Message;
import chess.views.terminals.VanillaChessTerminal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Benjin
 */
public class VanillaChessController extends GameController {
    
    @Override
    public void start () {
        VanillaChessGame game = new VanillaChessGame ();
        game.addUser (new User ("Naruto"));
        game.addUser (new User ("Sasuke"));
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            VanillaChessTerminal terminalView = new VanillaChessTerminal ();
            terminalView.setBoard (game.getBoard ());
            boolean showBoard = true;
            terminalView.addMessage (new Message (Message.Type.INFO, "Enter help for a list of commands."));
            
            while (true) {
                if (showBoard) {
                    terminalView.displayBoard ();
                }
                
                showBoard = true;
                terminalView.update (game.getCurrentMover (), game.getState ());
                Scanner scan = new Scanner (System.in);
                String input = scan.nextLine ();
                input = input.toLowerCase ().trim ();
                
                if (input.equals ("board")) {
                    
                } else if (input.equals ("legend")) {
                    showBoard = false;
                    terminalView.displayLegend ();
                } else if (input.equals ("help")) {
                    showBoard = false;
                    terminalView.showHelp ();
                } else if (input.equals ("quit")) {
                    this.setChanged ();
                    this.notifyObservers (GameController.QUIT);
                    break;
                } else {
                    Pattern pattern = Pattern.compile ("^\\s*[a-h][1-8] [a-h][1-8]\\s*$");
                    Matcher matcher = pattern.matcher (input);

                    if (matcher.find ()) {
                        input = matcher.group ();
                        String [] temp = input.split (" ");
                        String abc = "abcdefgh";
                        int curX = abc.indexOf (temp [0].charAt (0)) + 1;
                        int curY = Integer.parseInt ("" + temp [0].charAt (1));
                        int newX = abc.indexOf (temp [1].charAt (0)) + 1;
                        int newY = Integer.parseInt ("" + temp [1].charAt (1));
                        
                        boolean success = game.tryMove (curX, curY, newX, newY);

                        if (success) {
                            
                        } else {

                        }
                        
                        ArrayList <Message> messages = game.getMessages ();
                        
                        for (Message m:messages) {
                            if (m.getType () == Message.Type.ERROR) {
                                showBoard = false;
                                break;
                            }
                        }
                        
                        terminalView.addMessages (messages);
                    } else {
                        //error
                        showBoard = false;
                        terminalView.addMessage (new Message (Message.Type.ERROR, "That's not a valid command."));
                    }
                }
            }
        }
    }
}
