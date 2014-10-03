package chess.models;
import java.util.ArrayList;

public abstract class Rules {
    public enum RuleTypes {VANILLA_CHESS_RULES}
    private RuleTypes ruleType;
    
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
}
