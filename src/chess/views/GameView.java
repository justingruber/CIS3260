package chess.views;

import java.util.Observable;
import chess.models.Board;
import java.util.ArrayList;
import chess.models.messages.Message;

public abstract class GameView extends Observable {
    private ArrayList <Message> messages = new ArrayList <Message> ();
    private Board board;
    
    public abstract void printBoard();
    
    public abstract void placePieces(Board board);
    
    public abstract void showHelp ();
    
    public void setBoard (Board board) {
        this.board = board;
    }
    
    public Board getBoard () {
        return board;
    }
    
    public void addMessage (Message message) {
        messages.add (message);
    }
    
    public Message getNextMessage () {
        if (messages.size () > 0) {
            return messages.remove (0);
        } else {
            return null;
        }
    }
    
    public void clearMessages () {
        messages.clear ();
    }
    
    public abstract void showMessages ();
}