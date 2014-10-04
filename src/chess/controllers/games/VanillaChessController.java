/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers.games;

import chess.Application;
import chess.DisplayMode;
import chess.models.games.VanillaChessGame;
import chess.models.User;
import chess.views.terminals.VanillaChessTerminal;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.regex.Pattern;
import chess.models.messages.Message;
/**
 *
 * @author Benjin
 */
public class VanillaChessController extends GameController {
    
    @Override
    public void start () {
        VanillaChessGame game = new VanillaChessGame ();
        game.addUser (new User ("White"));
        game.addUser (new User ("Black"));
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            terminalView = new VanillaChessTerminal ();
            terminalView.setBoard (game.getBoard ());
            
            while (true) {
                terminalView.update ();
                
                Scanner scan = new Scanner (System.in);
                String input = scan.nextLine ();
                input = input.toLowerCase ().trim ();
                
                if (input.equals ("display")) {
                    terminalView.update ();
                } else if (input.equals ("quit")) {
                    this.setChanged ();
                    this.notifyObservers (GameController.QUIT);
                    break;
                } else {
                    Pattern pattern = Pattern.compile ("^\\s*\\d+ \\d+ \\d+ \\d+\\s*$");
                    Matcher matcher = pattern.matcher (input);

                    if (matcher.find ()) {
                        input = matcher.group ();
                        String [] coords = input.split (" ");

                        boolean success = game.tryMove (Integer.parseInt (coords [0]), Integer.parseInt (coords [1]), Integer.parseInt (coords [2]), Integer.parseInt (coords [3]));

                        if (success) {
                            terminalView.update ();
                        } else {

                        }

                        for (Message message:game.getMessages ()) {
                            System.out.println (message.getType() + ": " + message.getText());
                        }
                    } else {
                        //error
                    }
                }
            }
        }
    }
}
