package chess.models;

import java.lang.Enum;
import java.util.ArrayList;

public class VanillaChessRules extends Rules{
    private ArrayList <ChessPiece> pieces = new ArrayList();
    private ArrayList <Board.BoardTypes> boardTypes = new ArrayList();
    private ArrayList <String> messages = new ArrayList();
    private RuleTypes ruleType = RuleTypes.VANILLA_CHESS_RULES;
    private Board.BoardTypes selectedBoardType;
    private String message = ""; 
    private Boolean isCheckMate = false;
    private Boolean isCheck = false;
    private Boolean isStaleMAte = false;
    
    public VanillaChessRules(){
        super(RuleTypes.VANILLA_CHESS_RULES);
        boardTypes.add(Board.BoardTypes.VANILLA_CHESS_BOARD);
        createPiecesList();
        this.messages.add("0 messages");
    }
    
    private ArrayList <ChessPiece> initializeWhitePieces(){
        ArrayList <ChessPiece> whitePieces = new ArrayList();
        
        ChessPiece whiteKing = new ChessPiece(ChessPiece.ChessPieces.KING,ChessPiece.Colours.WHITE,4,1); 
        whitePieces.add(whiteKing);
        ChessPiece whiteQueen = new ChessPiece(ChessPiece.ChessPieces.QUEEN,ChessPiece.Colours.WHITE,5,1);
        whitePieces.add(whiteQueen);
        ChessPiece whiteRook = new ChessPiece(ChessPiece.ChessPieces.ROOK,ChessPiece.Colours.WHITE,1,1);
        whitePieces.add(whiteRook);
        ChessPiece whiteRook1 = new ChessPiece(ChessPiece.ChessPieces.ROOK,ChessPiece.Colours.WHITE,8,1);
        whitePieces.add(whiteRook1);
        ChessPiece whiteKnight = new ChessPiece(ChessPiece.ChessPieces.KNIGHT,ChessPiece.Colours.WHITE,2,1);
        whitePieces.add(whiteKnight);
        ChessPiece whiteKnight1 = new ChessPiece(ChessPiece.ChessPieces.KNIGHT,ChessPiece.Colours.WHITE,7,1);
        whitePieces.add(whiteKnight1);
        ChessPiece whiteBishop = new ChessPiece(ChessPiece.ChessPieces.BISHOP,ChessPiece.Colours.WHITE,3,1);
        whitePieces.add(whiteBishop);
        ChessPiece whiteBishop1 = new ChessPiece(ChessPiece.ChessPieces.BISHOP,ChessPiece.Colours.WHITE,6,1);
        whitePieces.add(whiteBishop1);
        
        int i = 0;
        
        for(i = 1; i <= 8; i++){
            ChessPiece pawn = new ChessPiece(ChessPiece.ChessPieces.PAWN, ChessPiece.Colours.WHITE,i,2);
            whitePieces.add(pawn);
        }
        
        return whitePieces;
    }
    
    private ArrayList <ChessPiece> initializeBlackPieces(){
        ArrayList <ChessPiece> blackPieces = new ArrayList();
        
        ChessPiece blackKing = new ChessPiece(ChessPiece.ChessPieces.KING,ChessPiece.Colours.BLACK,5,8); 
        blackPieces.add(blackKing);
        ChessPiece blackQueen = new ChessPiece(ChessPiece.ChessPieces.QUEEN,ChessPiece.Colours.BLACK,4,8);
        blackPieces.add(blackQueen);
        ChessPiece blackRook = new ChessPiece(ChessPiece.ChessPieces.ROOK,ChessPiece.Colours.BLACK,1,8);
        blackPieces.add(blackRook);
        ChessPiece blackRook1 = new ChessPiece(ChessPiece.ChessPieces.ROOK,ChessPiece.Colours.BLACK,8,8);
        blackPieces.add(blackRook1);
        ChessPiece blackKnight = new ChessPiece(ChessPiece.ChessPieces.KNIGHT,ChessPiece.Colours.BLACK,2,8);
        blackPieces.add(blackKnight);
        ChessPiece blackKnight1 = new ChessPiece(ChessPiece.ChessPieces.KNIGHT,ChessPiece.Colours.BLACK,7,8);
        blackPieces.add(blackKnight1);
        ChessPiece blackBishop = new ChessPiece(ChessPiece.ChessPieces.BISHOP,ChessPiece.Colours.BLACK,3,8);
        blackPieces.add(blackBishop);
        ChessPiece blackBishop1 = new ChessPiece(ChessPiece.ChessPieces.BISHOP,ChessPiece.Colours.BLACK,6,8);
        blackPieces.add(blackBishop1);
        
        int i = 0;
        
        for(i = 1; i <= 8; i++){
            ChessPiece pawn = new ChessPiece(ChessPiece.ChessPieces.PAWN, ChessPiece.Colours.BLACK,i,7);
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
    
    
    public Boolean createBoard(Board.BoardTypes boardType){
        this.board = new VanillaChessBoard();
        this.board.setPieces(this.pieces);
        return true;
    }
    
    public ArrayList<Board.BoardTypes> getBoardTypes(){
        ArrayList copyBoardTypes = new ArrayList(this.boardTypes);
        return copyBoardTypes;
    }
    
    public String getDescription(){
        return "These set of rules are for the standard chess game. The exact description of the rules can be found on wikipedia ";
    }
    
    @Override
    public ArrayList<String> getMessages(){
        return this.messages;
    }
    
    private void setMessage(String message){
        this.message = message;
        setMessages(this.message);
    }
    
    private void setMessages(String message){
        this.messages.add(message);
    }
    
    private Boolean isCheck(ChessPiece.Colours colour,Board board){
        Boolean boolReturn = null;
        boolReturn = checkForCheck(colour,board);
        return boolReturn;
    }
    
    private Boolean isCheckMate(){
        if(this.isCheckMate == true){
            return true;
        }
        return false;
    }
    
    private Boolean isStaleMate(){
        if(this.isStaleMAte == true){
            setMessage("Game Over: STALEMATE");
            return true;
        }
        return false;
    }
    
    private Boolean isDraw(){
        return false;
    }
    
    private Boolean checkForCheckMate(ChessPiece.Colours colour, Board board){
        ArrayList <ArrayList> possibleMoves = new ArrayList(); 
        ArrayList <Integer[]> tempArray = new ArrayList();
        int curX = 0;
        int curY = 0;
        Board tempBoard = new VanillaChessBoard();
        Boolean boolReturn = null;
        tempBoard.setPieces(copyPiecesList(board.getPieces()));
        ArrayList <String> tempMessages = new ArrayList(this.messages);
        
        for(ChessPiece piece: board.getPieces()){
            curX = piece.getX();
            curY = piece.getY();
            if(piece.getChessPieceColour() == colour && piece.getState() == 0){
                //System.out.println("Position = (" + piece.getX() + "," + piece.getY() + ")");
                //System.out.println("Name = " + piece.getChessPieceName() + " colour = " + piece.getChessPieceColour() + " state = " + piece.getState());
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
                    tempArray = new ArrayList(pawnPossibleMoves(piece.getChessPieceColour().toString(),curX,curY,board));                    
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.ROOK){
                    tempArray = new ArrayList(rookPossibleMoves(curX,curY,board));
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.BISHOP){
                    tempArray = new ArrayList(bishopPossibleMoves(curX,curY,board));   
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING){
                    tempArray = new ArrayList(kingPossibleMoves(curX,curY,board));
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.QUEEN){
                    tempArray = new ArrayList(queenPossibleMoves(curX,curY,board));  
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KNIGHT){
                    tempArray = new ArrayList(knightPossibleMoves(curX,curY,board));
                }else{
                    System.out.println("ERROR: The coordinates passed to tryMoveCheck did not contain a vanilla chess piece");
                    System.exit(1);
                }
           }
            if(tempArray.size() != 0 && tempArray != null && piece.getState() == 0){
                for(int i = 0; i < tempArray.size(); i++){
                    //System.out.println("(" + tempArray.get(i)[0] + "," + tempArray.get(i)[1] + ")");
                    boolReturn = take(piece.getX(),piece.getY(),tempArray.get(i)[0],tempArray.get(i)[1],tempBoard);
                    if(boolReturn == true){
                        this.messages = new ArrayList(tempMessages);
                        return false;
                    }
                }
            }
       }
        this.messages = new ArrayList();
        setMessages("GAME OVER: Checkmate");
        this.isCheckMate = true;
        return true;
    }
    
    private Boolean checkForStaleMate(ChessPiece.Colours colour, Board board){
        ArrayList <ArrayList> possibleMoves = null;
        ArrayList <Integer[]> temp;
        possibleMoves = new ArrayList(getAllPossibleMoves(colour, board));
        int staleMateFlag = 1; //0 == no stalemate 1 == stalemate
        
        /*for(int i = 0; i < possibleMoves.size();i++){
            for(int j = 0; j < possibleMoves.get(i).size();j++){
                temp = new ArrayList(possibleMoves.get(i));
                System.out.println("(" + temp.get(j)[0] + "," + temp.get(j)[0] + ")");
            }
        }*/
        
        for(int i = 0; i < possibleMoves.size();i++){
            if(possibleMoves.get(i).isEmpty() != true){
                staleMateFlag = 0;
                //System.out.println("No staleMate");
            }
        }
        //System.out.println(staleMateFlag);
        
        if(staleMateFlag == 1){
            this.isStaleMAte = true;
        }
        
        return true;
    }
    
    private Boolean checkForCheck(ChessPiece.Colours friendlyColour,Board board){
        ArrayList <ArrayList> possibleMoves = new ArrayList(); 
        ArrayList <Integer[]> tempArray = null;
        ChessPiece king = null;
        ChessPiece.Colours enemyColour;
        
        if(friendlyColour  == ChessPiece.Colours.WHITE){
            enemyColour = ChessPiece.Colours.BLACK;
        }else{
            enemyColour = ChessPiece.Colours.WHITE;
        }
        
        king = getKing(friendlyColour,board);
        
        possibleMoves = getAllPossibleMoves(enemyColour,board);
        //System.out.println(king.getChessPieceColour() +" King coordinates: (" + king.getX() + "," + king.getY() + ")");
        for(int i = 0; i < possibleMoves.size();i++){
            for(int j = 0; j < possibleMoves.get(i).size();j++){
                tempArray = new ArrayList(possibleMoves.get(i));
                //System.out.println("(" + tempArray.get(j)[0] + "," + tempArray.get(j)[1] + ")");
                if(king.getX() == tempArray.get(j)[0] && king.getY() == tempArray.get(j)[1]){
                    this.isCheck = true;
                    //System.out.println("1 (" + tempArray.get(j)[0] + "," + tempArray.get(j)[1] + ")");
                    return true;
                }
            }
        }
        return false;
    }
    
    //Checks enemy pieces after a move to determine if he is in stalemate
    private ArrayList <ArrayList> getAllPossibleMoves(ChessPiece.Colours colour,Board board){
        ArrayList <ArrayList> possibleMoves = new ArrayList(); 
        ArrayList <Integer[]> tempArray = null;
        int curX = 0;
        int curY = 0;
        
        for(ChessPiece piece: board.getPieces()){
            curX = piece.getX();
            curY = piece.getY();
            
            if(piece.getChessPieceColour() == colour){
                //System.out.println("Name = " +piece.getChessPieceName() + " Colour = " + piece.getChessPieceColour());
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
                    tempArray = new ArrayList(pawnPossibleMoves(piece.getChessPieceColour().toString(),curX,curY,board));
                    possibleMoves.add(tempArray);
                    //printList(tempArray,"Pawn");
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.ROOK){
                    tempArray = new ArrayList(rookPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                    //printList(tempArray,"Rook");
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.BISHOP){
                    tempArray = new ArrayList(bishopPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                    //printList(tempArray,"Bishop");
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING){
                    tempArray = new ArrayList(kingPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                    //printList(tempArray,"King");
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.QUEEN){
                    tempArray = new ArrayList(queenPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                    //printList(tempArray,"Queen");
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KNIGHT){
                    tempArray = new ArrayList(knightPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                    //printList(tempArray,"Knight");
                }else{
                    System.out.println("ERROR: The coordinates passed to tryMoveCheck did not contain a vanilla chess piece");
                    System.exit(1);
                }
           }
       }
        return possibleMoves;
    }
    //If null then no king was found
    private ChessPiece getKing(ChessPiece.Colours colour,Board board){
        for(ChessPiece piece: board.getPieces()){
           if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING && piece.getChessPieceColour() == colour){
               return piece;
           }
       }
        return null;
    }
    
    public Boolean isGameOver(){
        if(isCheckMate() == true){
            return true;
        } 
        return false;
    }
    
    //Move the friendly check to the take function? -- Do we really need it?
    //Move the cur==new coordniates to a new function? -- Do we really need it? 
    private Boolean validateCoordinates(int curX, int curY, int newX, int newY){
        Boolean boolReturns;
        
        //Make sure it's not checkmate
        if(isGameOver() == true){
           setMessage("GAME OVER: Can't move any piece");
           return false;
        }
        
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
    
    @Override
    //When to check if new == current
    //Need to check for check and stalemate
    //Need to implement a currX and curY check?
    public Boolean tryMove(int curX, int curY, int newX, int newY){
        Boolean boolReturns = null;
        ChessPiece.Colours friendlyColour;
        ChessPiece.Colours enemyColour;
        ChessPiece piece = null;
        Boolean isCheckMae = null;
        
        this.messages = new ArrayList();
        
        boolReturns = validateCoordinates(curX,curY,newX,newY);
        
        if(boolReturns == false){
            return false;
        }
        
        boolReturns = null;
        
        boolReturns = tryMoveCheck(curX,curY,newX, newY,this.board);
        piece = this.board.getPieceAtPosition(curX, curY);
        
        friendlyColour = piece.getChessPieceColour();
        
        if(friendlyColour == ChessPiece.Colours.WHITE){
            enemyColour = ChessPiece.Colours.BLACK;
        }else{
            enemyColour = ChessPiece.Colours.WHITE;
        }
        
        //Set the new coordinates for the piece 
        if(boolReturns == true){
            //checkForCheck(friendlyColour,this.board);
            take(curX,curY,newX,newY,this.board);
            checkForCheckMate(enemyColour,this.board);
            //isGameOver();
            return true;
        }else{
            setMessage("ERROR: The piece cannot move to the coordinates: (" + newX + "," + newY +")" );
            return false;
        }
    }
    
    private ArrayList <ChessPiece> copyPiecesList(ArrayList<ChessPiece> oldPiecesList){
        ArrayList <ChessPiece> copyPiecesList = new ArrayList();
        ChessPiece tempPiece;
        
        for(int i = 0; i < oldPiecesList.size(); i++){
            tempPiece = new ChessPiece(oldPiecesList.get(i));
            copyPiecesList.add(tempPiece);
        }
        
        return copyPiecesList;
    }
    
    private Boolean take(int curX, int curY, int newX, int newY,Board board){
        Boolean boolReturn;
        Boolean isCheck;
        Boolean isCheckMate;
        ArrayList <ChessPiece> copyOfPieces;
        ChessPiece pieceFromCopy;
        ChessPiece.Colours friendlyColour;
        ChessPiece.Colours enemyColour;
        Board tempBoard = new VanillaChessBoard();
        
        ChessPiece piece = board.getPieceAtPosition(curX, curY);
        
        friendlyColour = piece.getChessPieceColour();
        
        if(friendlyColour == ChessPiece.Colours.WHITE){
            enemyColour = ChessPiece.Colours.BLACK;
        }else{
            enemyColour = ChessPiece.Colours.WHITE;
        }
        
        //Check if you are in check
        isCheck = isCheck(friendlyColour,board);
        
        if (isCheck == true) {
            copyOfPieces = copyPiecesList (board.getPieces());
            tempBoard.setPieces(copyOfPieces);
            boolReturn = null;
            boolReturn = tempBoard.isPosistionOcuppied(newX, newY);
            
            if(boolReturn == true){
                ChessPiece takePiece = tempBoard.getPieceAtPosition(newX, newY);
                takePiece.setState(1);
                takePiece.setX(0);
                takePiece.setY(0);
            }
            
            pieceFromCopy = tempBoard.getPieceAtPosition(curX, curY);
            
            pieceFromCopy.setX(newX);
            pieceFromCopy.setY(newY);
            //Should this be on the copy?
            
            //Are you still in check after the move?
            
            isCheck = isCheck(friendlyColour,tempBoard);
            if(isCheck == true){
                setMessage("Error: The move will leave you in check");
                return false;
            }else{
                board.setPieces(copyOfPieces);
                //Check if you put enemy into check
                isCheck = isCheck(enemyColour,board);
                
                if(isCheck == true){
                    setMessage(enemyColour.toString() + " king is in Check");
                }
                setMessage("Succes: Move was successful");
                return true;
            }
        }else{
            boolReturn = null;
            boolReturn = board.isPosistionOcuppied(newX, newY);
        
            if(boolReturn == true){
                ChessPiece takePiece = board.getPieceAtPosition(newX, newY);
                takePiece.setState(1);
                takePiece.setX(0);
                takePiece.setY(0);
            }
            
            piece.setX(newX);
            piece.setY(newY);
            isCheck = isCheck(enemyColour,board);
 
            if(isCheck == true){
                setMessage(enemyColour.toString() + " king is in Check");
            }
            setMessage("Succes: Move was successful");
            //isCheckMate = checkForCheckMate(enemyColour,board);
            return true;
        }
    }
    
    //Test what happens when curX and Y = destX and Y
    //Error check dest?
    //Check what happens when pawn reaches 1-maxrange and maxrange
    private  ArrayList<Integer[]> move(int xIncrement, int yIncrement, int curX, int curY, int destX, int destY,Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        
        ChessPiece pieceAtNewPosition = null; 
        int newX = curX;
        int newY = curY;
        Boolean boolReturn;
        
        while(newX != destX || newY != destY){
            newX = newX + xIncrement;
            newY = newY + yIncrement;
            //Check to make sure new coordinates don't go out of range
            boolReturn = board.isPositionValid(newX, newY);
            
            if(boolReturn == true){
                //Check if the new coordinates are empty
                pieceAtNewPosition = board.getPieceAtPosition(newX, newY);
            
                if(pieceAtNewPosition == null){
                    Integer[] move = new Integer[]{newX,newY};
                    possibleMoves.add(move);
                }else{//New coordinates are not empty so need to check if enemy or friendly
                    ChessPiece pieceAtOldPosition = board.getPieceAtPosition(curX, curY);
                    
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
    
    
    private ArrayList<Integer[]> moveUp(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleUpMoves = new ArrayList();
        
        possibleUpMoves.addAll(move(0,1,curX,curY,destX,destY,board));
        
        return possibleUpMoves;
    }
    
    private ArrayList<Integer[]> moveDown(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleDownMoves = new ArrayList();
        
        possibleDownMoves.addAll(move(0,-1,curX,curY,destX,destY,board));
        
        return possibleDownMoves;
    }
    
    private ArrayList<Integer[]> moveRight(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleRightMoves = new ArrayList();
        
        possibleRightMoves.addAll(move(1,0,curX,curY,destX,destY,board));
        
        return possibleRightMoves;
    }
    
    private ArrayList<Integer[]> moveLeft(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleLeftMoves = new ArrayList();
        
        possibleLeftMoves.addAll(move(-1,0,curX,curY,destX,destY,board));
        
        return possibleLeftMoves;
    }
    
    private ArrayList<Integer[]> moveLeftUpDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleLeftUpDiagonally = new ArrayList();
        
        possibleLeftUpDiagonally.addAll(move(-1,1,curX,curY,destX,destY,board));
        
        return possibleLeftUpDiagonally;
    }
    
    private ArrayList<Integer[]> moveLeftDownDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleLeftDownDiagonally = new ArrayList();
        
        possibleLeftDownDiagonally.addAll(move(-1,-1,curX,curY,destX,destY,board));
        
        return possibleLeftDownDiagonally;
    }
    
    private ArrayList<Integer[]> moveRightUpDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleRightUpDiagonally = new ArrayList();
        
        possibleRightUpDiagonally.addAll(move(1,1,curX,curY,destX,destY,board));
        
        return possibleRightUpDiagonally;
    }
    
    private ArrayList<Integer[]> moveRightDownDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleRightDownDiagonally = new ArrayList();
        
        possibleRightDownDiagonally.addAll(move(1,-1,curX,curY,destX,destY,board));
        
        return possibleRightDownDiagonally;
    }
    
    private ArrayList <Integer[]> pawnPossibleMoves(String colour, int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        temp = new ArrayList();
        //Case1: Move up 2 squares for white pawn if pawn is in its starting position
        if(curY == 2 && colour.equalsIgnoreCase("white")){   
            destX = curX;
            destY = curY + 2;
            temp = moveUp(curX,curY,destX,destY,board);
        }else if(curY == 7 && colour.equalsIgnoreCase("black")){//Case1: Move down 2 squares for black pawn if pawn is in its starting position
            destX = curX;
            destY = curY - 2;
            temp = moveDown(curX,curY,destX,destY,board);
        }else{//Case2: Move up or down 1 square if pawn is not in starting position
            if(colour.equalsIgnoreCase("white")){
                destX = curX;
                destY = curY + 1;
                temp = moveUp(curX,curY,destX,destY,board);
            }else{
                destX = curX;
                destY = curY - 1;
                temp = moveDown(curX,curY,destX,destY,board);
            }
        }
    
        if(temp.size() > 0){
            boolReturn = board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
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
            temp = moveLeftUpDiagonally(curX,curY,destX,destY,board);
        }else{
            destX = curX - 1;
            destY = curY - 1;
            temp = moveLeftDownDiagonally(curX,curY,destX,destY,board);
        }
            
            
        if(temp.size() > 0){
            boolReturn = board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
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
            temp = moveRightUpDiagonally(curX,curY,destX,destY,board);
        }else{
            destX = curX + 1;
            destY = curY - 1;
            temp = moveRightDownDiagonally(curX,curY,destX,destY,board);
        }
            
        if(temp.size() > 0){
            boolReturn = board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
            if(boolReturn == false){
                temp.remove(0);
            }
            possibleMoves.addAll(temp);
        }    
        
        //Case5: Pawn reached the end of the board
            
        return possibleMoves;
    } 
    
    private ArrayList <Integer[]> rookPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        //Case 1: Move up
        temp = new ArrayList();
        destX = curX;
        destY = board.getMaxY();
        
        temp = moveUp(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 2: Move down
        temp = new ArrayList();
        destX = curX;
        destY = board.getMinY();
        
        temp = moveDown(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 3: Move right
        temp = new ArrayList();
        destX = board.getMaxX();
        destY = curY;
        
        temp = moveRight(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 4: Move left
        temp = new ArrayList();
        destX = board.getMinX();
        destY = curY;
        
        temp = moveLeft(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> bishopPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        
        //Case 1: Left up diagonal
        temp = new ArrayList();
        destX = board.getMinX();
        destY = board.getMaxY();
        
        temp = moveLeftUpDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 2: Right up diagonal
        temp = new ArrayList();
        destX = board.getMaxX();
        destY = board.getMaxY();
        
        temp = moveRightUpDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 3: Left down diagonal
        temp = new ArrayList();
        destX = board.getMinX();
        destY = board.getMinY();
        
        temp = moveLeftDownDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Cas3 4: Right down diagonal
        temp = new ArrayList();
        destX = board.getMaxX();
        destY = board.getMinY();
        
        temp = moveRightDownDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> kingPossibleMoves(int curX,int curY, Board board){
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
        temp = moveUp(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 2: move down 1 square
        temp = new ArrayList();
        destX = curX;
        destY = curY - 1;
        temp = moveDown(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 3: Move right 1 square
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY;
        temp = moveRight(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 4: Move left 1 square
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY;
        temp = moveLeft(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 5: Move Left up diagonally 
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY + 1;
        temp = moveLeftUpDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 6: Move right up diagonally
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY + 1;
        temp = moveRightUpDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 7: Move left down diagonally 
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY - 1;
        temp = moveLeftDownDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 8: Move right down diagonally
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY - 1;
        temp = moveRightDownDiagonally(curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> queenPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> rookMoves = new ArrayList();
        ArrayList <Integer[]> bishopMoves = new ArrayList();
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        Boolean boolReturn;
        
        rookMoves = rookPossibleMoves(curX,curY,board);
        bishopMoves = bishopPossibleMoves(curX,curY,board);
        
        possibleMoves.addAll(rookMoves);
        possibleMoves.addAll(bishopMoves);
        return possibleMoves;
    }
    
    private ArrayList <Integer[]> knightPossibleMoves(int curX,int curY, Board board){
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
        
        temp = move(-1,2,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 2: Move Up two squares and 1 to the right
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY + 2;
        
        temp = move(1,2,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 3: Move up one square and two to the left
        temp = new ArrayList();
        destX = curX - 2;
        destY = curY + 1;
        
        temp = move(-2,1,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 4: Move up one square and two to the right
        temp = new ArrayList();
        destX = curX + 2;
        destY = curY + 1;
        
        temp = move(2,1,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 5: Move down one square and two to the left
        temp = new ArrayList();
        destX = curX - 2;
        destY = curY - 1;
        
        temp = move(-2,-1,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 6: Move down one square and two to the right
        temp = new ArrayList();
        destX = curX + 2;
        destY = curY - 1;
        
        temp = move(2,-1,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 7: Move down two squares and one to the left
        temp = new ArrayList();
        destX = curX - 1;
        destY = curY - 2;
        
        temp = move(-1,-2,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        //Case 8: Move down two squares and two to the right
        temp = new ArrayList();
        destX = curX + 1;
        destY = curY - 2;
        
        temp = move(1,-2,curX,curY,destX,destY,board);
        possibleMoves.addAll(temp);
        
        return possibleMoves;
    }
    
    private void printList(ArrayList <Integer[]> possibleMoves,String piece){
        System.out.println(piece);
        for(int i = 0; i < possibleMoves.size(); i++){
            System.out.println("(" + possibleMoves.get(i)[0] + "," + possibleMoves.get(i)[1] + ")" );
        }
    }
    
    //Change invalid move to something more meaningful. 
    private Boolean tryMoveCheck(int curX,int curY,int newX, int newY,Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ChessPiece piece = board.getPieceAtPosition(curX, curY);
        
        if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
            possibleMoves = pawnPossibleMoves(piece.getChessPieceColour().toString(),curX,curY,board);
            //printList(possibleMoves,"Pawn");
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.ROOK){
            possibleMoves = rookPossibleMoves(curX,curY,board);
            //printList(possibleMoves,"Rook");
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.BISHOP){
            possibleMoves = bishopPossibleMoves(curX,curY,board);
            //printList(possibleMoves,"Bishop");
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING){
            possibleMoves = kingPossibleMoves(curX,curY,board);
            //printList(possibleMoves,"King");
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.QUEEN){
            possibleMoves = queenPossibleMoves(curX,curY,board);
            //printList(possibleMoves,"Queen");
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KNIGHT){
            possibleMoves = knightPossibleMoves(curX,curY,board);
            //printList(possibleMoves,"Knight");
        }else{
            System.out.println("ERROR: The coordinates passed to tryMoveCheck did not contain a vanilla chess piece");
            System.exit(1);
        }
        
        
        
        if(possibleMoves.size() == 0){
            setMessage("ERROR: Invalid move");
            return false;
        }else{//Need to check if newX and newY are within the list
            int foundFlag = 0;
            //System.out.println("Found Moves");
            
            for(int i = 0; i < possibleMoves.size();i++){
                if(possibleMoves.get(i)[0] == newX && possibleMoves.get(i)[1] == newY){
                    foundFlag = 1;
                }
                //System.out.println("(" + possibleMoves.get(i)[0] + "," + possibleMoves.get(i)[1] + ")" );
            }
            if(foundFlag == 1){
                return true;
            }else{
                return false;
            }
        }
    }
}   
