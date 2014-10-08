/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminals;

import chess.models.messages.Message;
import chess.views.GameView;
import java.util.ArrayList;

/**
 *
 * @author Benjin
 */
public abstract class ChessTerminal extends GameView {
    private ArrayList <String> helpLines = new ArrayList <> ();
    
    public ChessTerminal () {
        helpLines.add ("board   - Redraws the board in its current state.");
        helpLines.add ("quit    - Exits to the main menu.");
    }
    
    public void addHelpLine (String line) {
        helpLines.add (line);
    }
    
    @Override
    public void displayHelp () {
        System.out.println ("======================HELP====================");
        
        for (String line:helpLines) {
            System.out.println (line);
        }
        
        System.out.println ("==============================================");
    }
    
    @Override
    public void displayMessages () {
        Message message = null;
        boolean printed = false;
        
        do {
            message = this.getNextMessage ();
            
            if (message != null) {
                printed = true;
                System.out.println ("==============================================");
                System.out.println (message.getType () + ": " + message.getText ());
            }
        } while (message != null);
        
        if (printed) {
            System.out.println ("==============================================");
        }
    }
}
