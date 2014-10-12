package chess.models;

import java.util.ArrayList;

public abstract class Board {
    private ArrayList <ChessPiece> chessPieces;
    protected BoardType type;
    
    public final BoardType getType() {
        return type;
    }
    
    public final ArrayList <ChessPiece> getPieces(){
        return new ArrayList (chessPieces);
    }
    
    public final void setPieces(ArrayList <ChessPiece> chessPieceList){
        chessPieces = chessPieceList;            
    }
    
    abstract public ChessPiece getPieceAtPosition(int x, int y);
    abstract public boolean isPosistionOcuppied(int x, int y);
    abstract public boolean isPositionValid(int x, int y);
    abstract public int getMinX();
    abstract public int getMinY();
    abstract public int getMaxX();
    abstract public int getMaxY();
}
