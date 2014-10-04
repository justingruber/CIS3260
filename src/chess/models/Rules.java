package chess.models;
import java.util.ArrayList;

public abstract class Rules {
    public enum RuleTypes {VANILLA_CHESS_RULES}
    private RuleTypes ruleType;
    protected Board board = null;
    
    public Rules(RuleTypes ruleType){
        this.ruleType = ruleType;
    }
    
    abstract public ArrayList<Board.BoardTypes> getBoardTypes();
    
    abstract public String getDescription();
    
    abstract public Boolean createBoard(Board.BoardTypes boardType);
    
    abstract public Boolean isGameOver();
    
    public RuleTypes getRulesType(){
        return ruleType;
    }
    
    public Board getBoardInstance () {
        return board;
    }
    
    abstract public Boolean tryMove(int curX, int curY, int newX, int newY);
    abstract public ArrayList<String> getMessages();
}
