/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers.games;

import chess.Application;
import chess.DisplayMode;
import chess.models.ChessPiece;
import chess.models.GameSetting;
import chess.models.User;
import chess.models.games.Game;
import chess.models.games.VanillaChessGame;
import chess.models.messages.Message;
import chess.views.terminals.VanillaChessTerminal;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *a
 * @author Benjin
 */
public class VanillaChessController extends GameController {
    private VanillaChessGame game = new VanillaChessGame ();
    private VanillaChessTerminal terminalView;
    
    @Override
    public void start () {
        game.addObserver (this);
        game.addUser (new User ("Naruto"));
        game.addUser (new User ("Sasuke"));
        
        /*ArrayList <GameSetting> awd = new ArrayList <> ();
        awd.add (GameSetting.PROMOTION);
        awd.add (GameSetting.FIFTY_MOVE_RULE);
        awd.add (GameSetting.DRAW_BY_AGREEMENT);
        game.setSelectedSettings (awd);*/
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            terminalView = new VanillaChessTerminal ();
            terminalView.addObserver (this);
            terminalView.setBoard (game.getBoard ());
            terminalView.stateChanged (Game.STATE_LOBBY, game.getSelectedSettings ());
            
            boolean showBoard = true;
            terminalView.addMessage (new Message (Message.Type.INFO, "Enter help for a list of commands."));
            
            while (true) {
                if (showBoard) {
                    terminalView.displayBoard ();
                    showBoard = false;
                }
                
                terminalView.update (game.getCurrentMover (), game.getState ());
                Scanner scan = new Scanner (System.in);
                String input = scan.nextLine ();
                input = input.toLowerCase ().trim ();
                
                if (input.equals ("board")) {
                    showBoard = true;
                } else if (input.equals ("legend")) {
                    terminalView.displayLegend ();
                } else if (input.equals ("help")) {
                    terminalView.displayHelp ();
                } else if (input.equals ("quit")) {
                    this.setChanged ();
                    this.notifyObservers (GameController.QUIT);
                    break;
                } else if (input.equals ("settings")) {
                    //////////
                    terminalView.displaySelectedSettings (game.getSelectedSettings ());
                } else if (input.equals ("start") && game.getState () == Game.STATE_LOBBY) {
                    if (game.isHost ()) {
                        game.setState (VanillaChessGame.STATE_NORMAL);
                        terminalView.stateChanged (VanillaChessGame.STATE_NORMAL, game.getSelectedSettings ());
                        showBoard = true;
                    } else {
                        terminalView.addMessage (new Message (Message.Type.ERROR, "Only the host can do that."));
                    }
                } else if (input.equals ("setsettings") && game.getState () == Game.STATE_LOBBY) {
                    if (game.isHost ()) {
                        while (true) {
                            terminalView.displaySupportedSettings (game.getSupportedSettings ());
                            terminalView.displayMessages ();
                            input = scan.nextLine ().toLowerCase ().trim ();
                            
                            if (input.equals ("cancel")) {
                                break;
                            }
                            
                            Pattern pattern = Pattern.compile ("^\\s*\\d+\\s*$");
                            Matcher matcher = pattern.matcher (input);
                            
                            if (matcher.find ()) {
                                ArrayList <Integer> nums = new ArrayList <> ();
                                
                                for (int i = 0; i < input.length (); i++) {
                                    nums.add (Integer.parseInt (String.valueOf (input.charAt (i))));
                                }
                                
                                if (nums.indexOf (0) > -1) {
                                    while (nums.indexOf (0) > -1) {
                                        nums.remove (0);
                                    }
                                    
                                    if (nums.size () > 0) {
                                        terminalView.addMessage (new Message (Message.Type.ERROR, "You can't have [0] with other settings."));
                                    } else {
                                        game.setSelectedSettings (new ArrayList <GameSetting> ());
                                        break;
                                    }
                                } else {
                                    ArrayList <GameSetting> settings = new ArrayList <> ();
                                    boolean valid = true;
                                    
                                    for (int num:nums) {
                                        if (num > GameSetting.values ().length) {
                                            valid = false;
                                            terminalView.addMessage (new Message (Message.Type.ERROR, "[" + num + "] is not a valid setting."));
                                        } else {
                                            if (settings.indexOf (GameSetting.values ()[num - 1]) == -1) {
                                                settings.add (GameSetting.values ()[num - 1]);
                                            }
                                        }
                                    }
                                    
                                    if (valid) {
                                        game.setSelectedSettings (settings);
                                        terminalView.addMessage (new Message (Message.Type.SUCCESS, "Settings have been updated."));
                                        break;
                                    }
                                }
                            } else {
                                terminalView.addMessage (new Message (Message.Type.ERROR, "That's not a valid input."));
                            }
                        }
                    } else {
                        terminalView.addMessage (new Message (Message.Type.ERROR, "Only the host can do that."));
                    }
                } else if (input.equals ("draw") && game.getSelectedSettings ().contains (GameSetting.DRAW_BY_AGREEMENT) && game.getState () == VanillaChessGame.STATE_NORMAL) {
                    terminalView.displayDrawRequest (game.getCurrentMover ());
                    
                    while (true) {
                        terminalView.displayMessages ();
                        input = scan.nextLine ().trim ();
                        
                        if (input.equals ("1")) {
                            terminalView.addMessage (new Message (Message.Type.INFO, "The game has ended in draw."));
                            terminalView.stateChanged (VanillaChessGame.STATE_GAME_OVER, game.getSelectedSettings ());
                            showBoard = true;
                            break;
                        } else if (input.equals ("2")) {
                            terminalView.addMessage (new Message (Message.Type.INFO, "The draw by agreement wasn't agreed upon."));
                            break;
                        } else {
                            terminalView.addMessage (new Message (Message.Type.ERROR, "That's not a valid input."));
                        }
                    }
                } else if (input.equals ("fifty") && game.getSelectedSettings ().contains (GameSetting.FIFTY_MOVE_RULE) && game.getState () == VanillaChessGame.STATE_NORMAL) {
                    if (game.isFiftyMoveDraw ()) {
                        terminalView.addMessage (new Message (Message.Type.SUCCESS, "The fifty-move rule has been applied. The game ends in a draw."));
                        terminalView.stateChanged (VanillaChessGame.STATE_GAME_OVER, game.getSelectedSettings ());
                    } else {
                        terminalView.addMessage (new Message (Message.Type.INFO, "The fifty-move rule does not apply at this point."));
                    }
                } else {
                    Pattern pattern = Pattern.compile ("^\\s*[a-h][1-8] [a-h][1-8]\\s*$");
                    Matcher matcher = pattern.matcher (input);

                    if (matcher.find ()) {
                        if (game.getState () == VanillaChessGame.STATE_NORMAL) {
                            input = matcher.group ();
                            String [] temp = input.split (" ");
                            String abc = "abcdefgh";
                            int curX = abc.indexOf (temp [0].charAt (0)) + 1;
                            int curY = Integer.parseInt ("" + temp [0].charAt (1));
                            int newX = abc.indexOf (temp [1].charAt (0)) + 1;
                            int newY = Integer.parseInt ("" + temp [1].charAt (1));

                            boolean success = game.tryMove (curX, curY, newX, newY);

                            if (success) {
                                showBoard = true;
                            }
                            
                            if (game.isGameOver ()) {
                                terminalView.stateChanged (VanillaChessGame.STATE_GAME_OVER, game.getSelectedSettings ());
                            }

                            ArrayList <Message> messages = game.getMessages ();

                            for (Message m:messages) {
                                if (m.getType () == Message.Type.ERROR) {
                                    showBoard = false;
                                    break;
                                }
                            }

                            terminalView.addMessages (messages);
                        } else if (game.getState () == Game.STATE_LOBBY) {
                            terminalView.addMessage (new Message (Message.Type.ERROR, "That's not a valid command. Enter help for a list of commands."));
                        } else if (game.getState () == VanillaChessGame.STATE_GAME_OVER) {
                            terminalView.addMessage (new Message (Message.Type.ERROR, "The game is already over."));
                        }
                    } else {
                        terminalView.addMessage (new Message (Message.Type.ERROR, "That's not a valid command. Enter help for a list of commands."));
                    }
                }
            }
        }
    }
    
    @Override
    public void update (Observable obj, Object args) {
        if (args.equals ("PROMOTE")) {
            terminalView.displayPromotion ();
            Scanner scan = new Scanner (System.in);
            
            while (true) {
                terminalView.displayMessages ();
                String input = scan.nextLine ().trim ();
                
                //this is pretty gross too
                if (input.equals ("1")) {
                    game.promotePiece (ChessPiece.ChessPieces.BISHOP);
                    break;
                } else if (input.equals ("2")) {
                    game.promotePiece (ChessPiece.ChessPieces.ROOK);
                    break;
                } else if (input.equals ("3")) {
                    game.promotePiece (ChessPiece.ChessPieces.QUEEN);
                    break;
                } else if (input.equals ("4")) {
                    game.promotePiece (ChessPiece.ChessPieces.KNIGHT);
                    break;
                } else {
                    terminalView.addMessage (new Message (Message.Type.ERROR, "That's not a valid choice."));
                }
            }
            
            return;
        }
        
        if (Application.DISPLAY_MODE == DisplayMode.TERMINAL) {
            terminalView.stateChanged ((int) args, game.getSelectedSettings ());
        }
    }
}
