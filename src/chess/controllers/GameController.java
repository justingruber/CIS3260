/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.views.terminal.VanillaChessTerminal;
import chess.Application;
import chess.DisplayMode;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import chess.models.Rules;
import chess.models.Game;
import chess.models.UserManager;
import chess.views.terminal.ChessTerminal;
import chess.views.terminal.VanillaChessTerminal;
/**
 *
 * @author Benjin
 */
public class GameController extends Observable implements Observer {
    private Game game;
    private ChessTerminal terminalView;
    
    public void start (Rules.RuleTypes type) {
        game = new Game (type);
        game.addUser (UserManager.getCurrentUser ());
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            if (type == Rules.RuleTypes.VANILLA_CHESS_RULES) {
                terminalView = new VanillaChessTerminal ();
            }
            
            terminalView.setBoard (game.getBoard ());
            
            while (true) {
                terminalView.update ();
                String input = terminalView.readLine ();
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
                    
                    for (String message:game.getMessages ()) {
                        System.out.println (message);
                    }
                } else {
                    //error
                }
            }
        }
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    }
}
