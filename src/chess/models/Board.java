package chess;

import java.util.ArrayList;

public abstract class Board {
    
  
    abstract public ChessEnums.BoardTypes getBoardType();
    abstract public ArrayList <ChessPiece> getPieces();
    abstract public void setPieces(ArrayList <ChessPiece> chessPieceList);
    abstract public ChessPiece getPieceAtPosition(int x, int y);
    abstract public boolean isPosistionOcuppied(int x, int y);
    abstract public boolean isPositionValid(int x, int y);
}
