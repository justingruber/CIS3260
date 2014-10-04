package chess.models;

public class User {
    private String username = "";
    private ChessPiece.Colours playerColour = null;
    private Role userRole = null;
    
    public User () {
        
    }
    
    public User (String username) {
        this.username = username;
    }
    
    public void setUsername (String name) {
        username = name;
    }
    
    public String getUsername () {
        return username;
    }
    
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
    
    public enum Role {
        Lobby,
        Player,
        Spectator
    }
}
