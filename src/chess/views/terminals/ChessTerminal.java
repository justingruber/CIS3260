/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminals;

import chess.views.GameView;
import chess.models.messages.Message;
import java.util.Scanner;

/**
 *
 * @author Benjin
 */
public abstract class ChessTerminal extends GameView {
    
    public void print (String text) {
        System.out.print (text);
    }
    
    public void printLine (String text) {
        System.out.println (text);
    }
    
    public abstract void update ();
    public abstract void showMessage (Message message);
}
