package chess.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public abstract class GameView extends Observable {
    protected String name = "Generic Chess";

    abstract void printBoard();
    abstract void placePieces();
    abstract void demoBoard();
    private void placePiece(int row, int col) {}
    
    public void start () {
        System.out.println (name);
    }
}