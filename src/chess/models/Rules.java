package chess.models;

import java.util.ArrayList;
import chess.models.messages.*;

public abstract class Rules {
    public enum RuleTypes {VANILLA_CHESS_RULES}
    private RuleTypes ruleType;
    protected Board board = null;
   
    
    public Rules(RuleTypes ruleType){
        this.ruleType = ruleType;
    }
    
    abstract public ArrayList<Board.BoardTypes> getBoardTypes();
    
    abstract public Boolean createBoard(Board.BoardTypes boardType);
    
    abstract public Boolean tryMove(ChessPiece.Colours color, int curX, int curY, int newX, int newY);
    
    abstract public ArrayList<Message> getMessages();
    
    abstract public Boolean isGameOver();
    
    abstract public String getDescription();
    
    public RuleTypes getRulesType(){
        return ruleType;
    }
    
    public Board getBoardInstance () {
        return board;
    }
}
