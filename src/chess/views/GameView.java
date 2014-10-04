package chess.views;

import java.util.Observable;
import chess.models.Board;

public abstract class GameView extends Observable {
    private Board board;
    
    public abstract void printBoard();
    
    public abstract void placePieces(Board board);
    
    public void setBoard (Board board) {
        this.board = board;
    }
    
    public Board getBoard () {
        return board;
    }
}