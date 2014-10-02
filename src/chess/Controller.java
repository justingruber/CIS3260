/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Benjin
 */
public class Controller implements Observer {
    
    
    public void goMainMenu () {
        System.out.println ("CHESS GAME");
        System.out.println ("Play Vanilla");
        System.out.println ("Quit");
        Scanner scan = new Scanner (System.in);
        String option = scan.nextLine ();
        
        if (option.equals ("Play Vanilla")) {
            goGame ();
        } else if (option.equals ("Quit")) {
            //do quitting things here?
        }
    }
    
    public void goGame () {
        
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    }
}
