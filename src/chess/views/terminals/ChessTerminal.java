/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminals;

import chess.models.GameSetting;
import chess.models.messages.Message;
import chess.views.GameView;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Benjin
 */
public abstract class ChessTerminal extends GameView {
    private final HashMap <String, String> helpLines = new HashMap <> ();
    
    public ChessTerminal () {
        helpLines.put ("board", "board - Redraws the board in its current state.");
        helpLines.put ("quit", "quit - Exits to the main menu.");
    }
    
    public void addHelpLine (String key, String line) {
        helpLines.put (key, line);
    }
    
    public void removeHelpLine (String key) {
        helpLines.remove (key);
    }
    
    public void displaySelectedSettings (ArrayList <GameSetting> settings) {
        System.out.println ("===============SETTINGS===============");
        
        if (settings.size () <= 0) {
            System.out.println ("No settings selected.");
        }
        
        for (GameSetting setting:settings) {
            System.out.println (setting);
        }
        
        System.out.println ("======================================");
    }
    
    public void displaySupportedSettings (ArrayList <GameSetting> settings) {
        System.out.println ("======================AVAILABLE SETTINGS====================");
        System.out.println ("[0] None");
        
        for (int i = 0; i < settings.size (); i++) {
            System.out.println ("[" + (i + 1) + "] " + settings.get (i));
        }
        
        System.out.println ("==================================================");
        System.out.println ("Enter the combination of settings you want. For example 135 for [1], [3], and [5]. Enter cancel to return without changing anything.");
    }
    
    @Override
    public void displayHelp () {
        System.out.println ("======================HELP====================");
        
        for (String line:helpLines.values ()) {
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
                if (printed == false) {
                    System.out.println ("==============================================");
                }
                
                printed = true;
                
                System.out.println (message.getType () + ": " + message.getText ());
            }
        } while (message != null);
        
        if (printed) {
            System.out.println ("==============================================");
        }
    }
}
