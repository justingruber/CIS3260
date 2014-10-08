package chess.views;

import chess.models.Board;
import chess.models.messages.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

public abstract class GameView extends Observable {
    private ArrayList <Message> messages = new ArrayList <Message> ();
    private Board board;
    
    public abstract void displayHelp ();
    
    public void setBoard (Board board) {
        this.board = board;
    }
    
    public Board getBoard () {
        return board;
    }
    
    public void addMessage (Message message) {
        messages.add (message);
    }
    
    public void addMessages (Collection <Message> messages) {
        this.messages.addAll (messages);
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
    
    public abstract void displayMessages ();
}