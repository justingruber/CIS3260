package chess.models;

import java.util.ArrayList;
import chess.models.messages.*;

public abstract class Rules {
    private ArrayList <User> players = new ArrayList <> ();
    private ArrayList <User> spectators = new ArrayList <> ();
    private int maxPlayers = 1;
    private RuleTypes ruleType;
    protected Board board = null;
    
    public Rules(RuleTypes ruleType){
        this.ruleType = ruleType;
    }
    
    /* 
     * Name: getBoardTypes
     * Description: Returns all the board types that the implementation supports
     * Returns: ArrayList<Board.BoardTypes>
     */
    abstract public ArrayList<Board.BoardTypes> getBoardTypes();
    
    /* 
     * Name: createBoard
     * Description: Initializes the board
     * Returns: - true if board was initialized successfully
     *          - false if failed to initialize board
     */
    abstract public Boolean createBoard(Board.BoardTypes boardType);
    
    /* 
     * Name: tryMove
     * Description: Handles all the movements of the pieces
     * Returns: - true if the move was successful
     *          - false if if the move was unsuccessful
     */
    abstract public Boolean tryMove(ChessPiece.Colours color, int curX, int curY, int newX, int newY);
    
    /* 
     * Name: getMessages
     * Description: Returns a list of message objects that the user of the rules can use/display
     * Returns: ArrayList<Message>
     */
    abstract public ArrayList<Message> getMessages();
    
    /* 
     * Name: isGameOver
     * Description: Lets the user of the class know if a game is over
     * Returns: - true if the game is over
     *          - false if the game is not over
     */
    abstract public Boolean isGameOver();
    
    /* 
     * Name: getDescription
     * Description: Returns a desctiption of the rules
     * Returns: String
     */
    abstract public String getDescription();
    
    /* 
     * Name: getRulesType
     * Description: Allows the user to get the type of game that the class implemented 
     * Returns: EuleTypes
     */
    public RuleTypes getRulesType(){
        return ruleType;
    }
    
    /* 
     * Name: getBoardInstance
     * Description: Gives the user the board instance that was created with the createBoard()
     * Returns: Board
     */
    public Board getBoardInstance () {
        return board;
    }
    
    public enum RuleTypes { VANILLA_CHESS_RULES };
}
