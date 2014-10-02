package chess.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameView {


	abstract void printBoard(); 
	abstract void placePieces();
	abstract void demoBoard();
	private void placePiece(int row, int col) {}

}