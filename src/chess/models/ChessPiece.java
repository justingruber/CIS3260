package chess.models;
import java.lang.Exception;

public class ChessPiece {
    public enum Colours{
        BLACK ("Black"),
        WHITE ("White");
        
        private final String text;
        
        private Colours (String text) {
            this.text = text;
        }
        
        @Override
        public String toString () {
            return this.text;
        }
    }
    
    public enum ChessPieces {
        KING ("King"),
        QUEEN ("Queen"),
        ROOK ("Rook"),
        BISHOP ("Bishop"),
        KNIGHT ("Knight"),
        PAWN ("Pawn");
        
        private final String text;
        
        private ChessPieces (String text) {
            this.text = text;
        }
        
        @Override
        public String toString () {
            return this.text;
        }
    }
    
    private ChessPieces pieceName; 
    private Colours pieceColour;
    private int X = 0;
    private int Y = 0;
    private int previousX = 0;
    private int previousY = 0;
    private int state = 0;
    
    public ChessPiece (ChessPieces pieceName, Colours pieceColour){
        this.setChessPieceName(pieceName);
        this.setChessPieceColour(pieceColour);
    }
    
    public ChessPiece (ChessPieces pieceName, Colours pieceColour, int x, int y) {
        this.setChessPieceName(pieceName);
        this.setChessPieceColour(pieceColour);
        this.setX(x);
        this.setY(y);     
    }
    
    public ChessPiece (ChessPiece piece) {
        this.pieceName = piece.getChessPieceName();
        this.pieceColour = piece.getChessPieceColour();
        this.X = piece.getX();
        this.Y = piece.getY();
        this.state = piece.getState();
    }
    
    public ChessPieces getChessPieceName(){
        return this.pieceName;
    }
    
    public void setChessPieceName(ChessPieces pieceName){
        this.pieceName = pieceName;
    }
    
    public Colours getChessPieceColour(){
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
    
    public int getPreviousX(){ 
        return this.previousX;
    }
    
    public int getPreviousY(){ 
        return this.previousY;
    }
    
    public void setPreviousX(int x){
        this.previousX = x;
    }
    
    public void setPreviousY(int y){
        this.previousY = y;
    }
    
}
