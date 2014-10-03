/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.models.Rules;
import chess.models.Game;
import chess.models.UserManager;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Benjin
 */
public class MainMenuController extends Observable implements Observer {

    public void start () {
        UserManager.setCurrentUser ("Naruto");
        
        System.out.println ("CHESS GAME");
        System.out.println (MainMenuController.PLAY);
        System.out.println (MainMenuController.QUIT);
        Scanner scan = new Scanner (System.in);
        //String option = scan.nextLine ();
        String option = MainMenuController.PLAY;
        
        if (option.equals (MainMenuController.PLAY)) {
            this.setChanged ();
            this.notifyObservers (MainMenuController.PLAY);
        } else if (option.equals (MainMenuController.QUIT)) {
            this.setChanged ();
            this.notifyObservers (MainMenuController.QUIT);
        }
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    }
    
    public static final String PLAY = "Play";
    public static final String QUIT = "Quit";
}
