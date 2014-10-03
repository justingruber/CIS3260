package chess.views;

import java.util.Observable;
import chess.models.Board;

public abstract class GameView extends Observable {

    abstract void printBoard();

    abstract void placePieces(Board board);
}