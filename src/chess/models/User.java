package chess.models;

public class User {
    public enum Role {
        Lobby,
        Player,
        Spectator
    }
    String userName ="";
    ChessPiece.Colours playerColour = null;
    Role userRole = null;
    
    public ChessPiece.Colours getColour(){
        return playerColour;
    }
    public void setColour(ChessPiece.Colours colour){
        playerColour = colour;
    }
    public Role getRole(){
        return userRole;
    }
    public void setRole(Role role){
        userRole = role;
    }
}
