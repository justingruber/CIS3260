/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.views.graphical;

import chess.models.GameSetting;
import chess.models.messages.Message;
import chess.views.GameView;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Justin
 */
public class ChessGraphical extends GameView {

    private final HashMap<String, String> helpLines = new HashMap<>();

    public ChessGraphical() {
        helpLines.put("board", "board - Redraws the board in its current state.");
        helpLines.put("quit", "quit - Exits to the main menu.");
    }

    public void addHelpLine(String key, String line) {
        helpLines.put(key, line);
    }

    public void removeHelpLine(String key) {
        helpLines.remove(key);
    }

    public void displaySelectedSettings(ArrayList<GameSetting> settings) {
        //Have a popup for this
        for (GameSetting setting : settings) {
            //Add lines to popup
        }
    }

    public void displaySupportedSettings(ArrayList<GameSetting> settings) {
        //Have popup for this
        for (int i = 0; i < settings.size(); i++) {
            //("[" + (i + 1) + "] " + settings.get (i));
        }
        //("Enter the combination of settings you want. For example 135 for [1], [3], and [5]. Enter cancel to return without changing anything.");
    }

    @Override
    public void displayHelp() {
        for (String line : helpLines.values()) {

        }
    }

    @Override
    public void displayMessages() {
        Message message = null;
        boolean printed = false;

        do {
            message = this.getNextMessage();

            if (message != null) {
                if (!printed) {
                    //Print top of message line
                }

                printed = true;
                //Print message.getType () + ": " + message.getText ()
            }
        } while (message != null);

        if (printed) {
            //Print line
        }
    }

}
