package chess.views;

import java.util.Observable;
import chess.models.Board;

public abstract class GameView extends Observable {
    
    public abstract void printBoard();
    
    public abstract void placePieces(Board board);
}