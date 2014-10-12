package chess.models;

import java.util.ArrayList;

public class VanillaChessBoard extends Board {
    private int minX = 0;
    private int maxX = 0;
    private int minY = 0;    
    private int maxY = 0;
    public VanillaChessBoard(){
        super();
        this.type = BoardType.VANILLA;
        //init vanilla chess board dimension to x(1:8) y(1:8) 
        minX = 1;
        maxX = 8;
        minY = 1;
        maxY = 8;
        
    }
    public int getMinX(){
        return minX;
    }
    private void setMinX(int x){
        minX = x;
    }
    public int getMaxX(){
        return maxX;
    }
    private void setMaxX(int x){
        maxX = x;
    }
    public int getMinY(){
        return minY;
    }
    private void setMinY(int y){
        minY = y;
    }
    public int getMaxY(){
        return maxY;
    }
    private void setMaxY(int y){
        maxY = y;
    }
    
    @Override
    public ChessPiece getPieceAtPosition(int x, int y){
        ArrayList <ChessPiece> chessPieces = this.getPieces ();
       /*for loop iterate through chessPieces and find chess piece*/
       for(ChessPiece piece: chessPieces){
           if(piece.getX()==x && piece.getY()==y){
               return piece;
           }
       }
       return null;   //return null if nothing is found
    }
    @Override 
    public boolean isPosistionOcuppied(int x, int y){
        ChessPiece tempPiece = getPieceAtPosition(x,y);
        if(tempPiece!=null){
            if(tempPiece.getState() == 0){  //0 = non taken
                return true;
            }
        }
        return false;    //if no pieces found, then not occupied
    }
    @Override 
    public boolean isPositionValid(int x, int y){
        //check if coordinate is out of bound
        if(x<minX || x > maxX){
            return false;
        }
        if(y<minY || y > maxY){
            return false;
        }
        return true;
    }
    
}
