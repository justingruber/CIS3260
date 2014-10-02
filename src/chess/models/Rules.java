package chess;
import java.util.ArrayList;

public abstract class Rules {
    private ChessEnums.RuleTypes ruleType;
    
    public Rules(ChessEnums.RuleTypes ruleType){
        this.ruleType = ruleType;
    }
    
    abstract public ArrayList<ChessEnums.BoardTypes> getBoardTypes();
    
    abstract public String getDescription();
    
    abstract public Boolean createBoard(ChessEnums.BoardTypes boardType);
    
    public ChessEnums.RuleTypes getRulesType(){
        return ruleType;
    }
}
