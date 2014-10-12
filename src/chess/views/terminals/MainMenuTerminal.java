/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminals;

import chess.models.GameType;
import chess.models.messages.Message;
import chess.views.MainMenuView;
import java.util.Scanner;
/**
 *
 * @author Benjin
 */
public class MainMenuTerminal extends MainMenuView {
    private State state = State.MAIN;
    
    @Override
    public void update () {
        if (state == State.MAIN) {
            System.out.println ("Play");
            System.out.println ("Quit");
        } else if (state == State.PLAY) {
            int i = 1;
            System.out.println ("Which variant do you want to play?");
            
            for (GameType type:GameType.values ()) {
                System.out.println ("[" + i + "] " + type);
                i++;
            }
            
            System.out.println ("Back");
        }
    }
    
    public void showTitle () {
        System.out.println ("===================================");
        System.out.println ("    Super Awesome Chess Program    ");
        System.out.println ("===================================");
    }
    
    public void setState (State state) {
        this.state = state;
    }
    
    public State getState () {
        return state;
    }
    
    @Override
    public void displayMessage (Message message) {
        System.out.println ("==============================================");
        System.out.println (message.getType () + ": " + message.getText ());
        System.out.println ("==============================================");
    }
    
    public enum State { MAIN, PLAY }
}
