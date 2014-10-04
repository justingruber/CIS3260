/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminals;

import chess.views.GameView;
import chess.models.messages.Message;
import java.util.ArrayList;

/**
 *
 * @author Benjin
 */
public abstract class ChessTerminal extends GameView {
    ArrayList <String> helpLines = new ArrayList <String> ();
    
    public ChessTerminal () {
        helpLines.add ("help    - Shows a list of possible commands");
        helpLines.add ("display - Redraws the game in its current state");
        helpLines.add ("quit    - Exits to the main menu");
    }
    
    public void print (String text) {
        System.out.print (text);
    }
    
    public void printLine (String text) {
        System.out.println (text);
    }
    
    @Override
    public void showHelp () {
        for (String line:helpLines) {
            System.out.println (line);
        }
    }
    
    @Override
    public void showMessages () {
        Message message = null;
        
        do {
            message = this.getNextMessage ();
            
            if (message != null) {
                System.out.println (message.getType () + ": " + message.getText ());
            }
        } while (message != null);
    }
    
    public abstract void update ();
}
