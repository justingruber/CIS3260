/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminal;

import chess.views.MainMenuView;
import java.util.Scanner;
/**
 *
 * @author Benjin
 */
public class MainMenuTerminal extends MainMenuView {
    
    @Override
    public void update () {
        System.out.println ("SECRET AGENT LASER OBSTACLE CHESS");
        System.out.println (MainMenuTerminal.PLAY);
        System.out.println (MainMenuTerminal.QUIT);
    }
    
    public String getInput () {
        System.out.print ("What yoo wanna do, foo? ");
        Scanner scan = new Scanner (System.in);
        //String option = scan.nextLine ();
        String option = MainMenuTerminal.PLAY;
        
        if (option.equals (MainMenuTerminal.PLAY)) {
            return MainMenuTerminal.PLAY;
        } else if (option.equals (MainMenuTerminal.QUIT)) {
            return MainMenuTerminal.QUIT;
        }
        
        return null;
    }
    
    public static final String PLAY = "Play";
    public static final String QUIT = "Quit";
}
