/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models.games;

import chess.models.Board;
import chess.models.BoardType;
import chess.models.ChessPiece;
import chess.models.GameType;
import chess.models.User;
import chess.models.messages.Message;
import java.util.ArrayList;
/**
 *
 * @author Benjin
 */
public abstract class Game {
    protected GameType type;
    private ArrayList <User> players = new ArrayList <> ();
    private ArrayList <User> spectators = new ArrayList <> ();
    protected int maxPlayers = 2;
    protected Board board;
    protected ArrayList <ChessPiece> pieces = new ArrayList();
    protected ArrayList <Message> messages = new ArrayList();
    
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
}
