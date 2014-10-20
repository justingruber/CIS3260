/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models.games;

import chess.models.Board;
import chess.models.ChessPiece;
import chess.models.GameSetting;
import chess.models.GameType;
import chess.models.User;
import chess.models.messages.Message;
import java.util.ArrayList;
import java.util.Observable;
/**
 *
 * @author Benjin
 */
public abstract class Game extends Observable {
    protected GameType type;
    protected int maxPlayers = 2;
    protected Board board;
    protected ArrayList <ChessPiece> pieces = new ArrayList();
    protected ArrayList <Message> messages = new ArrayList();
    
    final ArrayList <GameSetting> supportedSettings = new ArrayList <> ();
    
    private boolean isHost = true;
    private int state;
    private final ArrayList <GameSetting> selectedSettings = new ArrayList <> ();
    private final ArrayList <User> players = new ArrayList <> ();
    private final ArrayList <User> spectators = new ArrayList <> ();
    
    public Game () {
        state = Game.STATE_LOBBY;
    }
    
    public ArrayList <GameSetting> getSupportedSettings () {
        return new ArrayList <> (supportedSettings);
    }
    
    public void setSelectedSettings (ArrayList <GameSetting> settings) {
        selectedSettings.clear ();
        selectedSettings.addAll (settings);
    }
    
    public ArrayList <GameSetting> getSelectedSettings () {
        return new ArrayList <> (selectedSettings);
    }
    
    public boolean isHost () {
        return isHost;
    }
    
    public void setState (int state) {
        this.state = state;
    }
    
    public int getState () {
        return state;
    }
    
    public void addUser (User user) {
        if (players.size () < maxPlayers) {
            players.add (user);
            playerAdded (user);
        } else {
            spectators.add (user);
        }
    }
    
    public int getMaxPlayers () {
        return maxPlayers;
    }
    
    public Board getBoard () {
        return board;
    }
    
    public ArrayList <Message> getMessages () {
        return messages;
    }
    
    
    protected abstract void playerAdded (User user);
    
    protected abstract void createBoard ();
    
    public static final int STATE_LOBBY = 0;
}
