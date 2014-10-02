package chess;
import java.lang.Exception;

public class ChessPiece {
    public enum Colours{
        BLACK,
        WHITE
    }
    
    private VanillaChessRules.ChessPieces pieceName; 
    private Colours pieceColour;
    private int X = 0;
    private int Y = 0;
    private int state = 0;
    
    public ChessPiece (VanillaChessRules.ChessPieces pieceName, Colours pieceColour){
        this.setChessPieceName(pieceName);
        this.setChessPieceColour(pieceColour);
    }
    
    public ChessPiece (VanillaChessRules.ChessPieces pieceName, Colours pieceColour, int x, int y) {
        this.setChessPieceName(pieceName);
        this.setChessPieceColour(pieceColour);
        this.setX(x);
        this.setY(y);     
    }
    
    public Enum getChessPieceName(){
        return this.pieceName;
    }
    
    public void setChessPieceName(VanillaChessRules.ChessPieces pieceName){
        this.pieceName = pieceName;
    }
    
    public Enum getChessPieceColour(){
        return this.pieceColour;
    }
    
    public void setChessPieceColour(Colours pieceColour){
        this.pieceColour = pieceColour;
    }
    
    public int getX(){
        return this.X;
    }
    
    public void setX(int x){
        this.X = x;
    }
    
    public void setY(int y){
        this.Y = y;
    }
    
    public int getY(){
        return this.Y;
    }
    
    public int getState(){
        return this.state;
    }
    
    public void setState(int state){
        this.state = state;
    }
    
}
