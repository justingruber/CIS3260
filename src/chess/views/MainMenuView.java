package chess.views;

import chess.models.messages.Message;
import java.util.Observable;

public abstract class MainMenuView extends Observable {
    
    public abstract void update ();
    public abstract void displayMessage (Message message);
}