/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminals;

import chess.models.messages.Message;
import chess.views.MainMenuView;
import java.util.Scanner;
/**
 *
 * @author Benjin
 */
public class MainMenuTerminal extends MainMenuView {
    
    public void print (String text) {
        System.out.print (text);
    }
    
    public void printLine (String text) {
        System.out.println (text);
    }
    
    @Override
    public void update () {
        System.out.println ("===================================");
        System.out.println ("    Super Awesome Chess Program    ");
        System.out.println ("===================================");
        System.out.println ("(1) Play");
        System.out.println ("(2) Quit");
    }
    
    @Override
    public void showMessage (Message message) {
        System.out.println ("==============================================");
        System.out.println (message.getType () + ": " + message.getText ());
        System.out.println ("==============================================");
    }
}
