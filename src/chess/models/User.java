package chess.models;

public class User {
    String userName ="";
    ChessPiece.Colours playerColour = null;
    ChessEnums.Role userRole = null;
    
    public ChessPiece.Colours getColour(){
        return playerColour;
    }
    public void setColour(ChessPiece.Colours colour){
        playerColour = colour;
    }
    public ChessEnums.Role getRole(){
        return userRole;
    }
    public void setRole(ChessEnums.Role role){
        userRole = role;
    }
}
