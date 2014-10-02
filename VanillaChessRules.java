package chess;

import java.lang.Enum;
import java.util.ArrayList;

public class VanillaChessRules extends Rules{
    public enum ChessPieces {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }
    
    private ArrayList <ChessPiece> pieces = new ArrayList();
    private ArrayList <ChessEnums.BoardTypes> boardTypes = new ArrayList();
    private Board board = null;
    private ChessEnums.RuleTypes ruleType = ChessEnums.RuleTypes.VANILLA_CHESS_RULES;
    private ChessEnums.BoardTypes selectedBoardType;
    private String message = ""; 
    
    public VanillaChessRules(){
        super(ChessEnums.RuleTypes.VANILLA_CHESS_RULES);
        boardTypes.add(ChessEnums.BoardTypes.VANILLA_CHESS_BOARD);
        createPiecesList();
    }
    
    private ArrayList <ChessPiece> initializeWhitePieces(){
        ArrayList <ChessPiece> whitePieces = new ArrayList();
        
        ChessPiece whiteKing = new ChessPiece(ChessPieces.KING,ChessPiece.Colours.WHITE,4,1); 
        whitePieces.add(whiteKing);
        ChessPiece whiteQueen = new ChessPiece(ChessPieces.QUEEN,ChessPiece.Colours.WHITE,5,1);
        whitePieces.add(whiteQueen);
        ChessPiece whiteRook = new ChessPiece(ChessPieces.ROOK,ChessPiece.Colours.WHITE,1,1);
        whitePieces.add(whiteRook);
        ChessPiece whiteRook1 = new ChessPiece(ChessPieces.ROOK,ChessPiece.Colours.WHITE,8,1);
        whitePieces.add(whiteRook1);
        ChessPiece whiteKnight = new ChessPiece(ChessPieces.KNIGHT,ChessPiece.Colours.WHITE,2,1);
        whitePieces.add(whiteKnight);
        ChessPiece whiteKnight1 = new ChessPiece(ChessPieces.KNIGHT,ChessPiece.Colours.WHITE,7,1);
        whitePieces.add(whiteKnight1);
        ChessPiece whiteBishop = new ChessPiece(ChessPieces.BISHOP,ChessPiece.Colours.WHITE,3,1);
        whitePieces.add(whiteBishop);
        ChessPiece whiteBishop1 = new ChessPiece(ChessPieces.BISHOP,ChessPiece.Colours.WHITE,6,1);
        whitePieces.add(whiteBishop1);
        
        int i = 0;
        
        for(i = 1; i <= 8; i++){
            ChessPiece pawn = new ChessPiece(ChessPieces.PAWN, ChessPiece.Colours.WHITE,i,2);
            whitePieces.add(pawn);
        }
        
        return whitePieces;
    }
    
    private ArrayList <ChessPiece> initializeBlackPieces(){
        ArrayList <ChessPiece> blackPieces = new ArrayList();
        
        ChessPiece blackKing = new ChessPiece(ChessPieces.KING,ChessPiece.Colours.BLACK,5,8); 
        blackPieces.add(blackKing);
        ChessPiece blackQueen = new ChessPiece(ChessPieces.QUEEN,ChessPiece.Colours.BLACK,4,8);
        blackPieces.add(blackQueen);
        ChessPiece blackRook = new ChessPiece(ChessPieces.ROOK,ChessPiece.Colours.BLACK,1,8);
        blackPieces.add(blackRook);
        ChessPiece blackRook1 = new ChessPiece(ChessPieces.ROOK,ChessPiece.Colours.BLACK,8,8);
        blackPieces.add(blackRook1);
        ChessPiece blackKnight = new ChessPiece(ChessPieces.KNIGHT,ChessPiece.Colours.BLACK,2,8);
        blackPieces.add(blackKnight);
        ChessPiece blackKnight1 = new ChessPiece(ChessPieces.KNIGHT,ChessPiece.Colours.BLACK,7,8);
        blackPieces.add(blackKnight1);
        ChessPiece blackBishop = new ChessPiece(ChessPieces.BISHOP,ChessPiece.Colours.BLACK,3,8);
        blackPieces.add(blackBishop);
        ChessPiece blackBishop1 = new ChessPiece(ChessPieces.BISHOP,ChessPiece.Colours.BLACK,6,8);
        blackPieces.add(blackBishop1);
        
        int i = 0;
        
        for(i = 1; i <= 8; i++){
            ChessPiece pawn = new ChessPiece(ChessPieces.PAWN, ChessPiece.Colours.BLACK,i,7);
            blackPieces.add(pawn);
        }
        
        return blackPieces;
    }
    
    
    private void createPiecesList(){
        ArrayList <ChessPiece> whitePieces = initializeWhitePieces();
        ArrayList <ChessPiece> blackPieces = initializeBlackPieces();
        
        pieces.addAll(whitePieces);
        pieces.addAll(blackPieces);
    }
    
    
    public Boolean createBoard(ChessEnums.BoardTypes boardType){
        this.board = new VanillaChessBoard();
        this.board.setPieces(this.pieces);
        return true;
    }
    
    public ArrayList<ChessEnums.BoardTypes> getBoardTypes(){
        ArrayList copyBoardTypes = new ArrayList(this.boardTypes);
        return copyBoardTypes;
    }
    
    public String getDescription(){
        return "These set of rules are for the standard chess game. The exact description of the rules can be found on wikipedia ";
    }
    
    public Board getBoardInstance(){
        return this.board;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    private void setMessage(String message){
        this.message = message; 
    }
    
    private Boolean isCheck(){
        return false;
    }
    
    private Boolean isCheckMate(){
        return false;
    }
    
    private Boolean isStaleMate(){
        return false;
    }
    
    public Boolean tryMove(int curX, int curY, int newX, int newY){
        
        return true;
    }
    
    private Boolean tryMovePawn(int x, int y){
        return true;
    }
    
}   
