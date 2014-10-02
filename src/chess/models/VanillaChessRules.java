package chess.models;

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
    private int isCheckMate = 0; // 1 == true, 0 == false
    private int isCheck = 0; // 1 == true, 0 == false
    private int isStaleMAte = 0; // 1 == true, 0 == false
    
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
    
    public Boolean isCheck(){
        if(this.isCheck == 1){
            return true;
        }
        return false;
    }
    
    public Boolean isCheckMate(){
        if(this.isCheckMate == 1){
            return true;
        }
        return false;
    }
    
    public Boolean isStaleMate(){
        if(this.isStaleMAte == 1){
            return true;
        }
        return false;
    }
    //Move the friendly check to the take function? -- Do we really need it?
    //Move the cur==new coordniates to a new function? -- Do we really need it? 
    private Boolean validateCoordinates(int curX, int curY, int newX, int newY){
        Boolean boolReturns;
        
        //Make sure that the move
        if(curX == newX && curY == newY){
            setMessage("ERROR: New coordinates are the same as current coordinates");
            return false;
        }
      
        //Make sure that there is a piece at current x and y coordinates
        ChessPiece checkCurLocationPiece = this.board.getPieceAtPosition(curX, curY);
        if(checkCurLocationPiece == null){
            setMessage("ERROR: There is no piece at current x and y coordinates");
            return false;
        }
        
        //Check if new coordinates are within the boards ranges
        boolReturns = this.board.isPositionValid(newX,newY);
        if(boolReturns != true){ 
            setMessage("ERROR: New coordinates are out of bounds");
            return false;
        }
        
        //Check if friendly piece is on the new coordinates
        boolReturns = this.board.isPosistionOcuppied(newX, newY);
        if(boolReturns != false){
            ChessPiece curLocationPiece = this.board.getPieceAtPosition(curX, curY);
            ChessPiece newLocationPiece = this.board.getPieceAtPosition(newX, newY);
          
            if(curLocationPiece != null && newLocationPiece != null && curLocationPiece.getChessPieceColour() == newLocationPiece.getChessPieceColour()){
                setMessage("ERROR: Friendly piece exists at those coordinates");
                return false;
            }   
        }
        return true;
    }
    //When to check if new == current
    //Need to check for check and stalemate
    //Need to implement a currX and curY check?
    public Boolean tryMove(int curX, int curY, int newX, int newY){
        Boolean boolReturns = null;
        
        setMessage("");
        
        boolReturns = validateCoordinates(curX,curY,newX,newY);
        
        if(boolReturns == false){
            return false;
        }
        
        boolReturns = null;
        
        boolReturns = tryMoveCheck(curX,curY,newX, newY);
        
        //Set the new coordinates for the piece 
        if(boolReturns == true){
            take(curX,curY,newX,newY);
            return true;
        }else{
            setMessage("ERROR: The piece cannot move to the coordinates: (" + newX + "," + newY +")" );
            return false;
        }
    }
    
    
    private void take(int curX, int curY, int newX, int newY){
        //Check if King. If king then checkmate.
        Boolean boolReturn;
        
        boolReturn = this.board.isPosistionOcuppied(newX, newY);
        
        if(boolReturn == true){
            ChessPiece takePiece = this.board.getPieceAtPosition(newX, newY);
            takePiece.setState(1);
            takePiece.setX(0);
            takePiece.setY(0);
        }
        
        ChessPiece piece = this.board.getPieceAtPosition(curX, curY);
        
        piece.setX(newX);
        piece.setY(newY);
    }
    
    //Test what happens when curX and Y = destX and Y
    //Error check dest?
    //Check what happens when pawn reaches 1-maxrange and maxrange
    private  ArrayList<Integer[]> move(int xIncrement, int yIncrement, int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        
        ChessPiece pieceAtNewPosition = null; 
        int newX = curX;
        int newY = curY;
        Boolean boolReturn;
        
        while(newX != destX || newY != destY){
            newX = newX + xIncrement;
            newY = newY + yIncrement;
            //Check to make sure new coordinates don't go out of range
            boolReturn = this.board.isPositionValid(newX, newY);
            
            if(boolReturn == true){
                //Check if the new coordinates are empty
                pieceAtNewPosition = this.board.getPieceAtPosition(newX, newY);
            
                if(pieceAtNewPosition == null){
                    Integer[] move = new Integer[]{newX,newY};
                    possibleMoves.add(move);
                }else{//New coordinates are not empty so need to check if enemy or friendly
                    ChessPiece pieceAtOldPosition = this.board.getPieceAtPosition(curX, curY);
                    
                    //If enemy then add the move to the possible moves list
                    if(pieceAtOldPosition.getChessPieceColour() != pieceAtNewPosition.getChessPieceColour()){
                        Integer[] move = new Integer[]{newX,newY};
                        possibleMoves.add(move);
                    }
                    break;
                }
            }else{
                break;
            }   
        }
        return possibleMoves;
    }
    
    
    private ArrayList<Integer[]> moveUp(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleUpMoves = new ArrayList();
        
        possibleUpMoves.addAll(move(0,1,curX,curY,destX,destY));
        
        return possibleUpMoves;
    }
    
    private ArrayList<Integer[]> moveDown(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleDownMoves = new ArrayList();
        
        possibleDownMoves.addAll(move(0,-1,curX,curY,destX,destY));
        
        return possibleDownMoves;
    }
    
    private ArrayList<Integer[]> moveRight(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleRightMoves = new ArrayList();
        
        possibleRightMoves.addAll(move(1,0,curX,curY,destX,destY));
        
        return possibleRightMoves;
    }
    
    private ArrayList<Integer[]> moveLeft(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleLeftMoves = new ArrayList();
        
        possibleLeftMoves.addAll(move(-1,0,curX,curY,destX,destY));
        
        return possibleLeftMoves;
    }
    
    private ArrayList<Integer[]> moveLeftUpDiagonally(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleLeftUpDiagonally = new ArrayList();
        
        possibleLeftUpDiagonally.addAll(move(-1,1,curX,curY,destX,destY));
        
        return possibleLeftUpDiagonally;
    }
    
    private ArrayList<Integer[]> moveLeftDownDiagonally(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleLeftDownDiagonally = new ArrayList();
        
        possibleLeftDownDiagonally.addAll(move(-1,-1,curX,curY,destX,destY));
        
        return possibleLeftDownDiagonally;
    }
    
    private ArrayList<Integer[]> moveRightUpDiagonally(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleRightUpDiagonally = new ArrayList();
        
        possibleRightUpDiagonally.addAll(move(1,1,curX,curY,destX,destY));
        
        return possibleRightUpDiagonally;
    }
    
    private ArrayList<Integer[]> moveRightDownDiagonally(int curX, int curY, int destX, int destY){
        ArrayList <Integer[]> possibleRightDownDiagonally = new ArrayList();
        
        possibleRightDownDiagonally.addAll(move(1,-1,curX,curY,destX,destY));
        
        return possibleRightDownDiagonally;
    }
    
    private ArrayList <Integer[]> pawnPossibleMoves(String colour, int curX,int curY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        temp = new ArrayList();
        System.out.println(colour);
        //Case1: Move up 2 squares for white pawn if pawn is in its starting position
        if(curY == 2 && colour.equalsIgnoreCase("white")){   
            destX = curX;
            destY = curY + 2;
            temp = moveUp(curX,curY,destX,destY);
        }else if(curY == 7 && colour.equalsIgnoreCase("black")){//Case1: Move down 2 squares for black pawn if pawn is in its starting position
            destX = curX;
            destY = curY - 2;
            temp = moveDown(curX,curY,destX,destY);
        }else{//Case2: Move up or down 1 square if pawn is not in starting position
            if(colour.equalsIgnoreCase("white")){
                destX = curX;
                destY = curY + 1;
                temp = moveUp(curX,curY,destX,destY);
            }else{
                destX = curX;
                destY = curY - 1;
                temp = moveDown(curX,curY,destX,destY);
            }
        }
    
        if(temp.size() > 0){
            boolReturn = this.board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
            if(boolReturn == true){
                temp.remove(0);
            }
            possibleMoves.addAll(temp);
        }
        
            
        //Case3: Move left up or down diagonally 1 square only if enemy is there
        temp = new ArrayList();
        if(colour.equalsIgnoreCase("white")){
            destX = curX - 1;
            destY = curY + 1;
            temp = moveLeftUpDiagonally(curX,curY,destX,destY);
        }else{
            destX = curX - 1;
            destY = curY - 1;
            temp = moveLeftDownDiagonally(curX,curY,destX,destY);
        }
            
            
        if(temp.size() > 0){
            boolReturn = this.board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
            if(boolReturn == false){
                temp.remove(0);
            }
            possibleMoves.addAll(temp);
        }    
            
        //Case4: Move right up or down diagnally if enemy piece is there
        temp = new ArrayList();
        if(colour.equalsIgnoreCase("white")){
            destX = curX + 1;
            destY = curY + 1;
            temp = moveRightUpDiagonally(curX,curY,destX,destY);
        }else{
            destX = curX + 1;
            destY = curY - 1;
            temp = moveRightDownDiagonally(curX,curY,destX,destY);
        }
            
        if(temp.size() > 0){
            boolReturn = this.board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
            if(boolReturn == false){
                temp.remove(0);
            }
            possibleMoves.addAll(temp);
        }    
        
        //Case5: Pawn reached the end of the board
            
        return possibleMoves;
    } 
    
    private ArrayList <Integer[]> rookPossibleMoves(int curX,int curY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        //Case 1: Move up
        temp = new ArrayList();
        destX = curX;
        destY = this.board.getMaxY();
        
        temp = moveUp(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 2: Move down
        temp = new ArrayList();
        destX = curX;
        destY = this.board.getMinY();
        
        temp = moveDown(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 3: Move right
        temp = new ArrayList();
        destX = board.getMaxX();
        destY = curY;
        
        temp = moveRight(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 4: Move left
        temp = new ArrayList();
        destX = board.getMinX();
        destY = curY;
        
        temp = moveLeft(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> bishopPossibleMoves(int curX,int curY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        
        //Case 1: Left up diagonal
        temp = new ArrayList();
        destX = this.board.getMinX();
        destY = this.board.getMaxY();
        
        temp = moveLeftUpDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 2: Right up diagonal
        temp = new ArrayList();
        destX = this.board.getMaxX();
        destY = this.board.getMaxY();
        
        temp = moveRightUpDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 3: Left down diagonal
        temp = new ArrayList();
        destX = this.board.getMinX();
        destY = this.board.getMinY();
        
        temp = moveLeftDownDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Cas3 4: Right down diagonal
        temp = new ArrayList();
        destX = this.board.getMaxX();
        destY = this.board.getMinY();
        
        temp = moveRightDownDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> kingPossibleMoves(int curX,int curY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        //Case 1: Move up 1 square
        temp = new ArrayList();
        destX = curX;
        destY = curY + 1;
        temp = moveUp(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 2: move down 1 square
        temp = new ArrayList();
        destX = curX;
        destY = curY - 1;
        temp = moveDown(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 3: Move right 1 square
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY;
        temp = moveRight(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 4: Move left 1 square
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY;
        temp = moveLeft(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 5: Move Left up diagonally 
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY + 1;
        temp = moveLeftUpDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 6: Move right up diagonally
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY + 1;
        temp = moveRightUpDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 7: Move left down diagonally 
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY - 1;
        temp = moveLeftDownDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 8: Move right down diagonally
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY - 1;
        temp = moveRightDownDiagonally(curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> queenPossibleMoves(int curX,int curY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> rookMoves = new ArrayList();
        ArrayList <Integer[]> bishopMoves = new ArrayList();
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        rookMoves = rookPossibleMoves(curX,curY);
        bishopMoves = bishopPossibleMoves(curX,curY);
        
        possibleMoves.addAll(rookMoves);
        possibleMoves.addAll(bishopMoves);
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> knightPossibleMoves(int curX,int curY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        //Case 1: Move up two squares and 1 to the left
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY + 2;
        
        temp = move(-1,2,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 2: Move Up two squares and 1 to the right
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY + 2;
        
        temp = move(1,2,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 3: Move up one square and two to the left
        temp = new ArrayList();
        destX = curX - 2;
        destY = curY + 1;
        
        temp = move(-2,1,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 4: Move up one square and two to the right
        temp = new ArrayList();
        destX = curX + 2;
        destY = curY + 1;
        
        temp = move(2,1,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 5: Move down one square and two to the left
        temp = new ArrayList();
        destX = curX - 2;
        destY = curY - 1;
        
        temp = move(-2,-1,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 6: Move down one square and two to the right
        temp = new ArrayList();
        destX = curX + 2;
        destY = curY - 1;
        
        temp = move(2,-1,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 7: Move down two squares and one to the left
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY - 2;
        
        temp = move(-1,-2,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        //Case 8: Move down two squares and two to the right
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY - 2;
        
        temp = move(1,-2,curX,curY,destX,destY);
        possibleMoves.addAll(temp);
        
        return possibleMoves;
    }
    
    //Change invalid move to something more meaningful. 
    private Boolean tryMoveCheck(int curX,int curY,int newX, int newY){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> tempPossibleMoves;
        Boolean boolReturn;
        ChessPiece piece = board.getPieceAtPosition(curX, curY);
        
        if(piece.getChessPieceName() == ChessPieces.PAWN){
            possibleMoves = pawnPossibleMoves(piece.getChessPieceColour().toString(),curX,curY);
        }else if(piece.getChessPieceName() == ChessPieces.ROOK){
            possibleMoves = rookPossibleMoves(curX,curY);
        }else if(piece.getChessPieceName() == ChessPieces.BISHOP){
            possibleMoves = bishopPossibleMoves(curX,curY);
        }else if(piece.getChessPieceName() == ChessPieces.KING){
            possibleMoves = kingPossibleMoves(curX,curY);
        }else if(piece.getChessPieceName() == ChessPieces.QUEEN){
            possibleMoves = queenPossibleMoves(curX,curY);
        }else if(piece.getChessPieceName() == ChessPieces.KNIGHT){
            possibleMoves = knightPossibleMoves(curX,curY);
        }else{
            System.out.println("ERROR: The coordinates passed to tryMoveCheck did not contain a vanilla chess piece");
            System.exit(1);
        }
        
        
        
        if(possibleMoves.size() == 0){
            setMessage("ERROR: Invalid move");
            return false;
        }else{//Need to check if newX and newY are within the list
            int foundFlag = 0;
            System.out.println("Found Moves");
            
            for(int i = 0; i < possibleMoves.size();i++){
                if(possibleMoves.get(i)[0] == newX && possibleMoves.get(i)[1] == newY){
                    foundFlag = 1;
                }
                System.out.println("(" + possibleMoves.get(i)[0] + "," + possibleMoves.get(i)[1] + ")" );
            }
            if(foundFlag == 1){
                return true;
            }else{
                return false;
            }
        }
    }
}   
