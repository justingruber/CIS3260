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
                    Pattern pattern = Pattern.compile ("^\\s*[a-h][1-8] [a-h][1-8]\\s*$");
                    Matcher matcher = pattern.matcher (input);

                    if (matcher.find ()) {
                        input = matcher.group ();
                        String [] temp = input.split (" ");
                        String abc = "abcdefh";
                        int curX = abc.indexOf (temp [0].charAt (0)) + 1;
                        int curY = Integer.parseInt ("" + temp [0].charAt (1));
                        int newX = abc.indexOf (temp [1].charAt (0)) + 1;
                        int newY = Integer.parseInt ("" + temp [1].charAt (1));
                        
                        boolean success = game.tryMove (curX, curY, newX, newY);

                        if (success) {
                            
                        } else {

                        }
                        
                        for (String message:game.getMessages ()) {
                            System.out.println (message);
                        }
                    } else {
                        //error
                        System.out.println ("AWDAWD");
                    }
                }
            }
        }
    }
}
