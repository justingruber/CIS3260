/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers;

import chess.Application;
import chess.DisplayMode;
import chess.models.GameType;
import chess.models.messages.Message;
import chess.views.graphical.MainMenuGraphical;
import chess.views.terminals.MainMenuTerminal;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author Benjin
 */
public class MainMenuController extends Observable implements Observer {
    public void start () {
        //this.setChanged ();
        //this.notifyObservers (GameType.values ()[0]);
        
        
        //Change this as you see fit, I just needed it for testing purposes and didn't know where to put it.
        System.out.println("[1] Graphical");
        System.out.println("[2] Terminal");
        System.out.println("[3] Online");
        boolean isValid = false;
        boolean isOnline = false;
        
        Scanner opt = new Scanner(System.in);
        
        int in = opt.nextInt();
        while(!isValid){
            if(in == 1){
                Application.DISPLAY_MODE = DisplayMode.GUI;
                isValid = true;
            }
            else if (in == 2) {
                Application.DISPLAY_MODE = DisplayMode.TERMINAL;
                isValid = true;
            }
            else if(in == 3){
                Application.DISPLAY_MODE = DisplayMode.TERMINAL;
                isOnline = true;
                isValid = true;
            }
            else {
                System.out.println("Enter 1, 2, or 3.");
            }
        
        }
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL && isOnline) {
            MainMenuTerminal terminalView = new MainMenuTerminal();
            terminalView = new MainMenuTerminal();
            terminalView.showTitle();

            connect(terminalView);
            
//            while (true) {
//                Scanner scan = new Scanner(System.in);
//                String input = scan.nextLine ();
//                 if (input.equals("back")) {
//                        terminalView.setState(MainMenuTerminal.State.MAIN);
//                        terminalView.showTitle();
//                    } else {
//                        terminalView.displayMessage(new Message(Message.Type.ERROR, "Invalid input"));
//                    }
//            }
        }
        
        else if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            MainMenuTerminal terminalView = new MainMenuTerminal ();
            terminalView = new MainMenuTerminal ();
            terminalView.showTitle ();
            
            while (true) {
                terminalView.update ();
                Scanner scan = new Scanner (System.in);
                String input = scan.nextLine ();
                //String input = MainMenuController.PLAY;
                input = input.toLowerCase ().trim ();
                
                if (terminalView.getState () == MainMenuTerminal.State.MAIN) {
                    if (input.equals (MainMenuController.PLAY)) {
                        terminalView.setState (MainMenuTerminal.State.PLAY);
                    } else if (input.equals (MainMenuController.QUIT)) {
                        this.setChanged ();
                        this.notifyObservers (MainMenuController.QUIT);
                        break;
                    } else {  //invalid input
                        terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                    }
                } else {
                    Pattern pattern = Pattern.compile ("\\d+");
                    Matcher matcher = pattern.matcher (input);
                    
                    if (matcher.find ()) {
                        int num = Integer.parseInt (matcher.group ());
                        
                        if (num >= 1 && num <= GameType.values ().length) {
                            this.setChanged ();
                            this.notifyObservers (GameType.values ()[num - 1]);
                            break;
                        } else {
                            terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                        }
                    } else if (input.equals ("back")) {
                        terminalView.setState (MainMenuTerminal.State.MAIN);
                        terminalView.showTitle ();
                    } else {
                        terminalView.displayMessage (new Message (Message.Type.ERROR, "Invalid input"));
                    }
                }
            }
        }
        else if(Application.DISPLAY_MODE == DisplayMode.GUI){
            MainMenuGraphical graphicalView = new MainMenuGraphical();
            graphicalView.MainMenuGraphical();
            
        }
    }
    
    @Override
    public void update (Observable object, Object args) {
        
    }
    
    
    private void connect(MainMenuTerminal terminalView) {
        String info = "";
        String host = "";

        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter host address:");
            host = stdIn.readLine();
            System.out.println("Enter a username: ");
            info = stdIn.readLine();
            Socket server;
            server = new Socket(host, 8080);
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

            out.println(info);
            Thread read = new Thread() {
                public synchronized void run() {
                    while (true) {
                        try {
                            String tmp = in.readLine();
                            System.out.println(tmp);
                            if (tmp.equalsIgnoreCase("Goodbye")) {
                                break;
                            }
                            else if(tmp.equalsIgnoreCase("Game is starting.")){
                                terminalView.setState(MainMenuTerminal.State.PLAY);
                                startGame();
                            }
                        } catch (Exception e) {
                        }

                    }
                    System.exit(0);
                }
            };
            read.start();
            Thread write = new Thread() {
                public synchronized void run() {
                    while (true) {
                        try {
                            String info = "";
                            info = stdIn.readLine();
                            out.println(info);
                        } catch (Exception e) {
                        }

                    }
                }
            };
            write.start();
            
            
        } catch (Exception e) {

        }

    }

    public void startGame(){
        this.setChanged();
        this.notifyObservers(GameType.values()[0]);
    }
    
    public static final String PLAY = "play";
    public static final String QUIT = "quit";
}
