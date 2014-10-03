package chess;

import java.util.Observable;
public abstract class GameView extends Observable{


	abstract void printBoard(); 
	abstract void placePieces(Board board);

}