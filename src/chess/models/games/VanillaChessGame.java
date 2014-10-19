/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models.games;

import chess.models.Board;
import chess.models.ChessPiece;
import chess.models.GameSetting;
import chess.models.GameType;
import chess.models.User;
import chess.models.VanillaChessBoard;
import chess.models.messages.Message;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Benjin
 */
public final class VanillaChessGame extends Game {
    private User currentMover;
    private User playerWhite;
    private User playerBlack;
    
    public VanillaChessGame () {
        this.supportedSettings.addAll (Arrays.asList (GameSetting.PROMOTION, GameSetting.CASTLING, GameSetting.EN_PASSANT, GameSetting.DRAW_BY_AGREEMENT, GameSetting.STALEMATE, GameSetting.FIFTY_MOVE_RULE));
        this.type = GameType.VANILLA;
        this.maxPlayers = 2;
        createPiecesList ();
        createBoard ();
    }
    /*
    public boolean tryMove (int curX, int curY, int newX, int newY) {
        if (this.getRules ().tryMove (curX, curY, newX, newY)) {
            //////////change this into the game class
            if (((VanillaChessRules) this.getRules ()).isGameOver ()) {
                state = State.GAME_OVER;
            }
            
            return true;
        } else {
            return false;
        }
    }
    */
    
    public User getCurrentMover () {
        return currentMover;
    }
    
    @Override
    protected void playerAdded (User user) {
        if (playerWhite == null) {
            playerWhite = user;
            user.setColour (ChessPiece.Colours.WHITE);
            currentMover = user;
        } else {
            playerBlack = user;
            user.setColour (ChessPiece.Colours.BLACK);
            //currentMover = user;
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private Boolean isCheckmate = false;
    private Boolean isCheck = false;
    private Boolean isStatemate = false;
    private ChessPiece lastPieceToMove = null;
    private Boolean isEnPassant = false;
    private Boolean castleKingSide = false;
    private Boolean castleQueenSide = false;
    private int whiteMoveCountForFifyMoveRule = 0;
    private int blackMoveCountForFifyMoveRule = 0;
    
    @Override
    //Description of the function is in the rules class
    public ArrayList<Message> getMessages(){
        return this.messages;
    }
    
    public Boolean isFiftyMoveDraw(){
        if(this.whiteMoveCountForFifyMoveRule >= 50 && this.blackMoveCountForFifyMoveRule >= 50){
            return true;
        }else{
            return false;
        }
    }
    
    //Description of the function is in the rules class
    public Boolean isGameOver(){
        if(isCheckMate() == true || isStaleMate() == true){
            //addToMessages(Message.Type.INFO , "GAME OVER");
            return true;
        } 
        return false;
    }
    
    //Create a copy of pieces?
    
    //Description of the function is in the rules class
    @Override
    protected void createBoard(){
        this.board = new VanillaChessBoard();
        this.board.setPieces (this.pieces);
    }
    
    //Description of the function in the rules class
    public Boolean tryMove(int curX, int curY, int newX, int newY){
        Boolean boolReturns = null;
        ChessPiece.Colours friendlyColour;
        ChessPiece.Colours enemyColour;
        ChessPiece piece = null;
        Boolean checkMate = null;
        Boolean staleMate = null;
        Boolean takeReturn = null;
        Boolean isOccupied = null;
        //System.out.println(getSelectedSettings());
        
        this.messages = new ArrayList();
        
        //Make sure it's not game over
        if(isGameOver() == true){
           //addToMessages(Message.Type.INFO,"GAME OVER: Can't move any piece");
           return false;
        }
        
        //Check to see if the input is valid
        boolReturns = validateInput(this.getCurrentMover ().getColour (),curX,curY,newX,newY);
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
        
        isOccupied = board.isPosistionOcuppied(newX, newY);
        
        if(boolReturns == true){//If the new coordinates given are within the pieces possible moves
            takeReturn = take(curX,curY,newX,newY,this.board);
            
            if(takeReturn == false){
                return false;
            }
            
            checkMate = checkForCheckMate(enemyColour,this.board);
            //staleMate = checkForStaleMate(enemyColour,this.board);
            //checkForStaleMate(friendlyColour,this.board);
    
            if(checkMate == true){
                this.messages = new ArrayList();
                addToMessages(Message.Type.INFO,"GAME OVER: Checkmate");
                this.setState (VanillaChessGame.STATE_GAME_OVER);
            }
            
            /*if(staleMate == true){
                this.messages = new ArrayList();
                addToMessages(Message.Type.INFO,"GAME OVER: Stalemate");
            }*/
            
            if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN || isOccupied == true){
                resetMoveCounts();
            }else{
                incrementMoveCount(friendlyColour);
            }
            
            currentMover = currentMover == playerWhite ? playerBlack : playerWhite;
            return true;
        }else{//The new coordinates given are not a valid move for the piece
            String abc = " ABCDEFGH";
            addToMessages(Message.Type.ERROR,"You can't move that piece to " + abc.charAt (newX) + newY + ".");
            return false;
        }
    }
    
    private void resetMoveCounts(){
        this.blackMoveCountForFifyMoveRule = 0;
        this.whiteMoveCountForFifyMoveRule = 0;
    }
    
    private void incrementMoveCount(ChessPiece.Colours colour){
        if(colour == ChessPiece.Colours.WHITE){
            this.whiteMoveCountForFifyMoveRule = this.whiteMoveCountForFifyMoveRule + 10;
        }else{
            this.blackMoveCountForFifyMoveRule = this.blackMoveCountForFifyMoveRule + 10;
        }
    }
    
    
    /*
     * Name: initializeWhitePieces
     * Description: The function creates all the required white pieces for a standard chess game
     *              and sets each piece's position to its starting position.
     * Returns: ArrayList <ChessPiece>
     */
    private ArrayList <ChessPiece> initializeWhitePieces(){
        ArrayList <ChessPiece> whitePieces = new ArrayList();
        
        ChessPiece whiteKing = new ChessPiece(ChessPiece.ChessPieces.KING,ChessPiece.Colours.WHITE,5,1); 
        whitePieces.add(whiteKing);
        ChessPiece whiteQueen = new ChessPiece(ChessPiece.ChessPieces.QUEEN,ChessPiece.Colours.WHITE,4,1);
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
        
        for(int i = 1; i <= 8; i++){
            ChessPiece pawn = new ChessPiece(ChessPiece.ChessPieces.PAWN, ChessPiece.Colours.WHITE,i,2);
            whitePieces.add(pawn);
        }
        //whitePieces.add (new ChessPiece(ChessPiece.ChessPieces.PAWN, ChessPiece.Colours.WHITE,4,2));
        return whitePieces;
    }
    
    /*
     * Name: initializeBlackPieces
     * Description: The function creates all the required black pieces for a standard chess game
     *              and sets each piece's position to its starting position.
     * Returns: ArrayList <ChessPiece>
     */
    private ArrayList <ChessPiece> initializeBlackPieces(){
        ArrayList <ChessPiece> blackPieces = new ArrayList();
        
        ChessPiece blackKing = new ChessPiece(ChessPiece.ChessPieces.KING,ChessPiece.Colours.BLACK,5,8); 
        blackPieces.add(blackKing);
        ChessPiece blackQueen = new ChessPiece(ChessPiece.ChessPieces.QUEEN,ChessPiece.Colours.BLACK,4,8);
        blackPieces.add(blackQueen);
        //blackQueen = new ChessPiece(ChessPiece.ChessPieces.QUEEN,ChessPiece.Colours.BLACK,1,4);
        //blackPieces.add(blackQueen);
        //blackQueen = new ChessPiece(ChessPiece.ChessPieces.QUEEN,ChessPiece.Colours.BLACK,6,8);
        //blackPieces.add(blackQueen);
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
        
        for(int i = 1; i <= 8; i++){
            ChessPiece pawn = new ChessPiece(ChessPiece.ChessPieces.PAWN, ChessPiece.Colours.BLACK,i,7);
            blackPieces.add(pawn);
        }
        return blackPieces;
    }
    
    /*
     * Name: createPiecesList
     * Description: The funciton merges the ArrayLists returned by initializeWhitePieces() 
     *              and initializeBlackPieces() to create one ArrayList that contains all 
     *              the pieces for a standard chess game and their initial positions.
     */
    private void createPiecesList(){
        ArrayList <ChessPiece> whitePieces = initializeWhitePieces();
        ArrayList <ChessPiece> blackPieces = initializeBlackPieces();
        
        pieces.addAll(whitePieces);
        pieces.addAll(blackPieces);
    }
    
    
    /*
     * Name: addToMessages
     * Description: The function creates a Message object and calls setMessages() to add it 
     *              to the list of Message objects.
     */
    private void addToMessages(Message.Type type, String text){
        Message message = new Message(type, text);
        setMessages(message);
    }
    
    /*
     * Name: setMessages
     * Description: The function adds a Message object to an ArrayList that contains a list of Message
     *              objects. 
     */
    private void setMessages(Message message){
        this.messages.add(message);
    }
    
    /*
     * Name: isCheck
     * Description: The function takes a given colour and board and calls checkForCheck() to see if
     *              the given colour is in check
     * Returns: - true if a given colour is in check
     *          - false if a given colour is not in check
     */
    private Boolean isCheck(ChessPiece.Colours colour,Board board){
        Boolean boolReturn = null;
        boolReturn = checkForCheck(colour,board);
        return boolReturn;
    }
    
    /*
     * Name: isCheckMate
     * Description: Checks an instance variable to see if there is a checkmate
     * Returns: - true if a there is a checkmate
     *          - false if there is no checkmate
     * Note: This function doesn't determine if there is a checkmate. It only uses a instance variable that
     *       is set by checkForCheckMate(). If you want to see if a colour is in checkmate then call
     *       checkForCheckMate()
     */
    private Boolean isCheckMate(){
        if(this.isCheckmate == true){
            return true;
        }
        return false;
    }
    
    /*
     * Name: isStaleMate
     * Description: Checks an instance variable to see if there is a stalemate
     * Returns: - true if a there is a stalemate
     *          - false if there is no stalemate
     * Note: This function doesn't determine if there is a stalemate. It only uses a instance variable that
     *       is set by checkForStaleMate(). If you want to see if a colour is in stalemate then call
     *       checkForStaleMate()
     */
    private Boolean isStaleMate(){
        if(this.isStatemate == true){
            return true;
        }
        
        return false;
    }
    
    /*
     * Name: isDraw
     * Description: Checks an instance variable to see if there is a draw
     * Returns: - true if a there is a draw
     *          - false if there is no draw
     * Note: The function isn't done so it only returns false
     */
    private Boolean isDraw(){
        //Both are stalemated and no pieces left to do checkmate
        return false;
    }
    
    /*
     * Name: checkForCheckMate
     * Description: The function determines if a given colour on a given board is in checkmate
     * Returns: - true if the given colour is in checkmate
     *          - false if the given colour is not in checkmate
     * Note: Checkmate occurs when the colour is in check and cannot make any legal moves
     */
    private Boolean checkForCheckMate(ChessPiece.Colours colour, Board board){
        Boolean boolReturn;
        Boolean check;
        
        check = isCheck(colour,board);
        
        //Checkmate can only occur if the player with the given colour is in check
        if(check == false){
            return false;
        }
        
        boolReturn = canAPieceMove(colour, board);
        
        //If there is no legal move and the given colour is in check then checkmate!
        if(boolReturn == false){
            this.isCheckmate = true;
            return true;
        }else{
            return false;
        }
    }
    
    /*
     * Name: checkForStaleMate
     * Description: The function determins if a given colour on a given board is in a stalemate
     * Returns: - true if the given colour is in a stalemate
     *          - false if the given colour is not in a stalemate
     * Note: Stalemate can only occur if the given colour is not in check and has no legal moves
     */
    private Boolean checkForStaleMate(ChessPiece.Colours colour, Board board){
        Boolean boolReturn;
        Boolean check;
        
        check = isCheck(colour,board);
        
        //The colour cannot be in check for a stalemate to occur 
        if(check == true){
            return false;
        }
        
       boolReturn = canAPieceMove(colour,board);
       
       //If the player is not in check and has no legal moves then stalemate
       if(boolReturn == false){
           this.isStatemate = true;
            return true;
       }else{
           return false;
       }
    }
    
    /*
     * Name: canAPieceMove
     * Description: The function creates a temporary board and copies the piece positons from the 
     *              original board. It then plays out all possible moves to see if any piece of given colour 
     *              can make a legal move. 
     * Returns: - true if there exists one valid move for a given colour. 
     *          - false if there are zero valid moves for a given colour
     */
    private boolean canAPieceMove(ChessPiece.Colours colour, Board board){
        ArrayList <Integer[]> tempArray = new ArrayList();
        int curX = 0;
        int curY = 0;
        Board tempBoard = new VanillaChessBoard();
        Boolean boolReturn;
        ArrayList<Message> tempMessages = new ArrayList(this.messages);
        
        this.messages = new ArrayList();
        
        //Create a temporary board and set piece positions from the original board
        tempBoard.setPieces(copyPiecesList(board.getPieces()));
        
        //Get all possible moves for each piece
        for(ChessPiece piece: board.getPieces()){
            curX = piece.getX();
            curY = piece.getY();
            if(piece.getChessPieceColour() == colour && piece.getState() == 0){
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
            
            if(tempArray.size() != 0 && tempArray != null && piece.getState() == 0 && piece.getChessPieceColour() == colour){
                for(int i = 0; i < tempArray.size(); i++){//Play out every move for that piece on the temp board
                    //System.out.println("from can a piece move");
                    boolReturn = take(piece.getX(),piece.getY(),tempArray.get(i)[0],tempArray.get(i)[1],tempBoard);
                    System.out.print("("+ piece.getX() + "," + piece.getY()+ ")");
                    System.out.println("to ("+ tempArray.get(i)[0] + "," + tempArray.get(i)[1]+ ")");
                    if(boolReturn == true){ //Only need to find one valid move
                        this.messages = new ArrayList(tempMessages);
                        return true;
                    }
                }
            }
       }
        this.messages = new ArrayList(tempMessages);
        return false;
    }
    
    /*
     * Name: checkForCheck
     * Description: The function gets the coordinates of a king and gets all the possible moves 
     *              of all pieces of opposite colour from the king. If a piece has coordinates 
     *              equal to the king's current coordinates then the king is in check.
     * Returns: - true if the king of a given colour is in check
     *          - false if the king of a given colour is not in check
     */
    private Boolean checkForCheck(ChessPiece.Colours friendlyColour,Board board){
        ArrayList <ArrayList> possibleMoves; 
        ArrayList <Integer[]> tempArray;
        ChessPiece king;
        ChessPiece.Colours enemyColour;
        
        if(friendlyColour  == ChessPiece.Colours.WHITE){
            enemyColour = ChessPiece.Colours.BLACK;
        }else{
            enemyColour = ChessPiece.Colours.WHITE;
        }

        king = getKing(friendlyColour,board); 
        
        possibleMoves = getAllPossibleMoves(enemyColour,board);
        
        //Go through the enemy's possible moves
        for(int i = 0; i < possibleMoves.size();i++){
            for(int j = 0; j < possibleMoves.get(i).size();j++){
                tempArray = new ArrayList(possibleMoves.get(i));
                //If coordinates match the king's coordinates then he is in check
                if(king.getX() == tempArray.get(j)[0] && king.getY() == tempArray.get(j)[1]){
                    this.isCheck = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
     * Name: getAllPossibleMoves
     * Description: The function gets all the possiblem moves for a given colour on a given board.
     * Returns: - ArrayList<ArrayList> 
     *          - The ArrayList<ArrayList> will be empty if there are no possible moves
     */
    private ArrayList <ArrayList> getAllPossibleMoves(ChessPiece.Colours colour,Board board){
        ArrayList <ArrayList> possibleMoves = new ArrayList(); 
        ArrayList <Integer[]> tempArray = null;
        int curX = 0;
        int curY = 0;
        
        for(ChessPiece piece: board.getPieces()){
            curX = piece.getX();
            curY = piece.getY();
            
            if(piece.getChessPieceColour() == colour){
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
                    tempArray = new ArrayList(pawnPossibleMoves(piece.getChessPieceColour().toString(),curX,curY,board));
                    possibleMoves.add(tempArray);
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.ROOK){
                    tempArray = new ArrayList(rookPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.BISHOP){
                    tempArray = new ArrayList(bishopPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING){
                    tempArray = new ArrayList(kingPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.QUEEN){
                    tempArray = new ArrayList(queenPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KNIGHT){
                    tempArray = new ArrayList(knightPossibleMoves(curX,curY,board));
                    possibleMoves.add(tempArray);
                }else{
                    System.out.println("ERROR: The coordinates passed to tryMoveCheck did not contain a vanilla chess piece");
                    System.exit(1);
                }
           }
       }
        return possibleMoves;
    }
    
    /*
     * Name: 
     * Description: The function gets the king piece from a given board
     * Returns: ChessPiece object
     */
    private ChessPiece getKing(ChessPiece.Colours colour,Board board){
        for(ChessPiece piece: board.getPieces()){
           if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING && piece.getChessPieceColour() == colour){
               return piece;
           }
       }
        return null;
    }
    
    /*
     * Name: validateInput
     * Description: Validates that the input entered to make a move is correct
     * Returns: - true if the input is correct
     *          - false if the input is incorrect
     */
    private Boolean validateInput(ChessPiece.Colours colour, int curX, int curY, int newX, int newY){
        Boolean boolReturns;
        String abc = " ABCDEFGH";
        
        //Check to see if current coordinates and new coordinates are equal
        if(curX == newX && curY == newY){
            addToMessages(Message.Type.ERROR,"You entered the same coordinates twice.");
            return false;
        }
      
        //Make sure that there is a piece at current x and y coordinates
        ChessPiece checkCurLocationPiece = this.board.getPieceAtPosition(curX, curY);
        if(checkCurLocationPiece == null){
            addToMessages(Message.Type.ERROR,"There's no piece at " + abc.charAt (curX) + curY + ".");
            return false;
        }
        
        //Make sure that the colour entered is correct
        if (checkCurLocationPiece.getChessPieceColour () != colour) {
            addToMessages (Message.Type.ERROR,"That piece doesn't belong to you.");
            return false;
        }
        
        //Check if new coordinates are within the board's ranges
        boolReturns = this.board.isPositionValid(newX,newY);
        if(boolReturns != true){ 
            addToMessages(Message.Type.ERROR,abc.charAt (newX) + newY + " is out of bounds.");
            return false;
        }
        
        //Check if there is a friendly piece is on the new coordinates
        boolReturns = this.board.isPosistionOcuppied(newX, newY);
        if(boolReturns != false){
            ChessPiece curLocationPiece = this.board.getPieceAtPosition(curX, curY);
            ChessPiece newLocationPiece = this.board.getPieceAtPosition(newX, newY);
          
            if(curLocationPiece != null && newLocationPiece != null && curLocationPiece.getChessPieceColour() == newLocationPiece.getChessPieceColour()){
                addToMessages(Message.Type.ERROR,"One of your pieces is already at " + abc.charAt (newX) + newY + ".");
                return false;
            }   
        }
        return true;
    }
    
    /*
     * Name: copyPiecesList
     * Description: The function takes an ArrayList and creates a deep copy
     * Returns: ArrayList<ChessPiece>
     */
    private ArrayList <ChessPiece> copyPiecesList(ArrayList<ChessPiece> oldPiecesList){
        ArrayList <ChessPiece> copyPiecesList = new ArrayList();
        ChessPiece tempPiece;
        
        for(int i = 0; i < oldPiecesList.size(); i++){
            tempPiece = new ChessPiece(oldPiecesList.get(i));
            copyPiecesList.add(tempPiece);
        }
        
        return copyPiecesList;
    }
    
    /*
     * Name: take
     * Description: The function is does two things. First, it determines if a move is legal(will not let  
     *              a player move a piece if it puts him/her in check). Second, it moves and takes pieces.
     * Returns: - true if the piece moved to the new coordinates successfully
     *          - false if the piece tried to make an illegal move
     */
    private Boolean take(int curX, int curY, int newX, int newY,Board board){
        Boolean boolReturn;
        Boolean check;
        Boolean checkForInitialCheck;
        Boolean checkForEnPassant;
        ArrayList <ChessPiece> copyOfPieces;
        ChessPiece pieceFromCopy;
        ChessPiece.Colours friendlyColour;
        ChessPiece.Colours enemyColour;
        Board tempBoard = new VanillaChessBoard();
        ChessPiece tempPiece = null;
        
        ChessPiece piece = board.getPieceAtPosition(curX, curY);
        
        friendlyColour = piece.getChessPieceColour();
        
        if(friendlyColour == ChessPiece.Colours.WHITE){
            enemyColour = ChessPiece.Colours.BLACK;
        }else{
            enemyColour = ChessPiece.Colours.WHITE;
        }
        checkForInitialCheck = isCheck(friendlyColour,board);//Check to see if the moving player is in check
        copyOfPieces = copyPiecesList (board.getPieces());
        tempBoard.setPieces(copyOfPieces);
        boolReturn = null;
        
        //Take the enemy piece if the new coordinates are occupied
        boolReturn = tempBoard.isPosistionOcuppied(newX, newY);
        tempPiece = tempBoard.getPieceAtPosition(curX, curY);
        
        if(boolReturn == false && tempPiece.getChessPieceName() == ChessPiece.ChessPieces.PAWN && this.isEnPassant == true){
            if(tempPiece.getChessPieceColour() == ChessPiece.Colours.WHITE){
                checkForEnPassant = tempBoard.isPosistionOcuppied(newX, newY-1);
                if(checkForEnPassant == true){
                    ChessPiece takePiece = tempBoard.getPieceAtPosition(newX, newY-1);
                    takePiece.setState(1);
                    takePiece.setX(0);
                    takePiece.setY(0);
                }
                this.isEnPassant = false;
            }else if(tempPiece.getChessPieceColour() == ChessPiece.Colours.BLACK){
                checkForEnPassant = tempBoard.isPosistionOcuppied(newX, newY+1);
                if(checkForEnPassant == true){
                    ChessPiece takePiece = tempBoard.getPieceAtPosition(newX, newY+1);
                    takePiece.setState(1);
                    takePiece.setX(0);
                    takePiece.setY(0);
                }
                this.isEnPassant = false;
            }
        }else if(boolReturn == true){
            ChessPiece takePiece = tempBoard.getPieceAtPosition(newX, newY);
            takePiece.setState(1);
            takePiece.setX(0);
            takePiece.setY(0);       
        }
        
        //Move the piece
        pieceFromCopy = tempBoard.getPieceAtPosition(curX, curY);
        ChessPiece rook = null;
        if(checkForInitialCheck == false && pieceFromCopy.getChessPieceName() == ChessPiece.ChessPieces.KING && this.castleKingSide == true || this.castleQueenSide == true){
            //System.out.println("kingside = " + this.castleKingSide);
            //System.out.println("queenside =" + this.castleQueenSide);
            if(newX == curX+2 && newY == curY){
                rook = tempBoard.getPieceAtPosition(8, curY);
//                System.out.println("From take inside");
//                System.out.println("Rook = " + rook.getX() + ","+ rook.getY());
                rook.setPreviousX(8);
                rook.setPreviousY(curY);
                rook.setX(6);
                rook.setY(curY);
//                System.out.println("From take inside");
//                System.out.println("Rook = " + rook.getX() + ","+ rook.getY());
                this.castleKingSide = false;
                //System.out.println("Castle king side");
            }else if(newX == curX-2 && newY == curY){
                rook = tempBoard.getPieceAtPosition(1, curY);
                rook.setPreviousX(1);
                rook.setPreviousY(curY);
                rook.setX(4);
                rook.setY(curY);
                this.castleQueenSide = false;
                //System.out.println("Castle queen side");
            }      
        }else if(checkForInitialCheck == true && pieceFromCopy.getChessPieceName() == ChessPiece.ChessPieces.KING && (newX == curX+2 || newX ==curX-2 )){
            addToMessages(Message.Type.ERROR,"Can't castle when in check");
            this.isEnPassant = false;
            this.castleKingSide = false;
            this.castleQueenSide = false;
            return false;
        }
        //System.out.println("From take");
        //System.out.println("Rook outside = " + rook.getX() + ","+ rook.getY());
        pieceFromCopy.setPreviousX(curX);
        pieceFromCopy.setPreviousY(curY);
        pieceFromCopy.setX(newX);
        pieceFromCopy.setY(newY);

        //Is the moving player in check after the move?
        check = isCheck(friendlyColour,tempBoard);
        
        if(check == true){
            if(checkForInitialCheck  == true){
                addToMessages(Message.Type.ERROR,"The move will leave you in check");
            }else{
                addToMessages(Message.Type.ERROR,"The move will place you in check");
            }
            this.isEnPassant = false;
            this.castleKingSide = false;
            this.castleQueenSide = false;
            return false;
        }else{
            board.setPieces(copyOfPieces);
            this.lastPieceToMove = board.getPieceAtPosition(newX,newY);
            
            //Check to see if the move put the enemy in check
            check = isCheck(enemyColour,board);
            if(check == true){
               //System.out.println("King is in check" + enemyColour.toString());
               addToMessages(Message.Type.INFO,enemyColour.toString() + " king is in Check");
            }
            //addToMessages(Message.Type.SUCCESS,"Move was successful");
            this.isEnPassant = false;
            this.castleKingSide = false;
            this.castleQueenSide = false;
            return true;
        }
    }
    
    /*
     * Name: move
     * Description: The move function returns an ArrayList containing all the possible coordinates given an
     *               x and y increment, current location, and a destination. 
     * Returns: ArrayList<Integer[]>
     */
    private  ArrayList<Integer[]> move(int xIncrement, int yIncrement, int curX, int curY, int destX, int destY,Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ChessPiece pieceAtNewPosition = null; 
        int newX = curX;
        int newY = curY;
        Boolean boolReturn;
        
        //Start at current coordinates and increment them until destination is reached
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
    
    /*
     * Name: moveUp
     * Description: Determines the increments needed to move a piece up and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveUp(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleUpMoves = new ArrayList();
        
        possibleUpMoves.addAll(move(0,1,curX,curY,destX,destY,board));
        
        return possibleUpMoves;
    }
    
    /*
     * Name: moveDown
     * Description: Determines the increments needed to move a piece down and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveDown(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleDownMoves = new ArrayList();
        
        possibleDownMoves.addAll(move(0,-1,curX,curY,destX,destY,board));
        
        return possibleDownMoves;
    }
    
    /*
     * Name: moveRight
     * Description: Determines the increments needed to move a piece right and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveRight(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleRightMoves = new ArrayList();
        
        possibleRightMoves.addAll(move(1,0,curX,curY,destX,destY,board));
        
        return possibleRightMoves;
    }
    
    /*
     * Name: moveLeft
     * Description: Determines the increments needed to move a piece left and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveLeft(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleLeftMoves = new ArrayList();
        
        possibleLeftMoves.addAll(move(-1,0,curX,curY,destX,destY,board));
        
        return possibleLeftMoves;
    }
    
    /*
     * Name: moveLeftUpDiaginally
     * Description: Determines the increments needed to move a piece moveLeftUpDiaginally and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveLeftUpDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleLeftUpDiagonally = new ArrayList();
        
        possibleLeftUpDiagonally.addAll(move(-1,1,curX,curY,destX,destY,board));
        
        return possibleLeftUpDiagonally;
    }
    
    /*
     * Name: moveLeftDownDiagonally
     * Description: Determines the increments needed to move a piece moveLeftDownDiaginally and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveLeftDownDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleLeftDownDiagonally = new ArrayList();
        
        possibleLeftDownDiagonally.addAll(move(-1,-1,curX,curY,destX,destY,board));
        
        return possibleLeftDownDiagonally;
    }
    
    /*
     * Name: moveRightUpDiagonally
     * Description: Determines the increments needed to move a piece moveRightUpDiaginally and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveRightUpDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleRightUpDiagonally = new ArrayList();
        
        possibleRightUpDiagonally.addAll(move(1,1,curX,curY,destX,destY,board));
        
        return possibleRightUpDiagonally;
    }
    
    /*
     * Name: moveRightDownDiagonally
     * Description: Determines the increments needed to move a piece moveRightDownDiaginally and then calls the 
     *              move function with the increments to determine all the possible moves in that direction
     * Returns: ArrayList<Integer[]>
     */
    private ArrayList<Integer[]> moveRightDownDiagonally(int curX, int curY, int destX, int destY, Board board){
        ArrayList <Integer[]> possibleRightDownDiagonally = new ArrayList();
        
        possibleRightDownDiagonally.addAll(move(1,-1,curX,curY,destX,destY,board));
        
        return possibleRightDownDiagonally;
    }
    
    /*
     * Name: pawnPossibleMoves
     * Description: The function goes through every move case for a pawn
     * Returns: - ArrayList<Integer>
     *          - An empty ArrayList<Integer> if there are no possible moves
     */
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
    
        //A pawn cannot claim a piece that is infront of it so remove the coordinates if they are occupied
        if(temp.size() == 2){
            boolReturn = board.isPosistionOcuppied(temp.get(1)[0], temp.get(1)[1]);
                
            if(boolReturn == true){
                temp.remove(1);
            }
            possibleMoves.addAll(temp); 
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
            
        //Remove the diagnal coordinates if no enemy occupies the spot   
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
        //Remove the diagnal coordinates if no enemy occupies the spot   
        if(temp.size() > 0){
            boolReturn = board.isPosistionOcuppied(temp.get(0)[0], temp.get(0)[1]);
                
            if(boolReturn == false){
                temp.remove(0);
            }
            possibleMoves.addAll(temp);
        }    
        
        
        
        
        //Case5: En passant 
        //Check if there is a pawn that moved 2 squares next to it
        if(colour.equalsIgnoreCase("white") && curY == 5 && this.lastPieceToMove.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
            boolReturn = board.isPosistionOcuppied(curX+1, curY);
            //Check the right of the pawn
            if(boolReturn == true){
                piece = board.getPieceAtPosition(curX+1,curY);
                
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN && curX+1 == this.lastPieceToMove.getX() && curY == this.lastPieceToMove.getY()){
                    if(this.lastPieceToMove.getPreviousX() == curX+1 && this.lastPieceToMove.getPreviousY() == curY+2){
                        temp = moveRightUpDiagonally(curX,curY,curX+1,curY+1,board);
                        possibleMoves.addAll(temp);
                        this.isEnPassant = true;
                    }
                }
            }
            
            boolReturn = board.isPosistionOcuppied(curX-1, curY);
            //Check the left of the pawn
            if(boolReturn == true){
                piece = board.getPieceAtPosition(curX-1,curY);
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN && curX-1 == this.lastPieceToMove.getX() && curY == this.lastPieceToMove.getY()){
                    if(this.lastPieceToMove.getPreviousX() == curX-1 && this.lastPieceToMove.getPreviousY() == curY+2){
                        temp = moveLeftUpDiagonally(curX,curY,curX-1,curY+1,board);
                        possibleMoves.addAll(temp);
                        this.isEnPassant = true;
                    }
                }
            }
        }else if(colour.equalsIgnoreCase("black") && curY == 4 && this.lastPieceToMove.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
            boolReturn = board.isPosistionOcuppied(curX+1, curY);
            //Check the right of the pawn
            if(boolReturn == true){
                piece = board.getPieceAtPosition(curX+1,curY);
                
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN && curX+1 == this.lastPieceToMove.getX() && curY == this.lastPieceToMove.getY()){
                    if(this.lastPieceToMove.getPreviousX() == curX+1 && this.lastPieceToMove.getPreviousY() == curY-2){
                        temp = moveRightDownDiagonally(curX,curY,curX+1,curY-1,board);
                        possibleMoves.addAll(temp);
                        this.isEnPassant = true;
                    }
                }
            }
            
            boolReturn = board.isPosistionOcuppied(curX-1, curY);
            //Check the left of the pawn
            if(boolReturn == true){
                piece = board.getPieceAtPosition(curX-1,curY);
                if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN && curX-1 == this.lastPieceToMove.getX() && curY == this.lastPieceToMove.getY()){
                    if(this.lastPieceToMove.getPreviousX() == curX-1 && this.lastPieceToMove.getPreviousY() == curY-2){
                        temp = moveLeftDownDiagonally(curX,curY,curX-1,curY-1,board);
                        possibleMoves.addAll(temp);
                        this.isEnPassant = true;
                    }
                }
            }
        }
        
        //Move the pawn left and right and see what the move function returns
        //If it returns something then check to see if it is a pawn
        // If pawn then check for previous x,y
        // If everything matches up then move up diagonally
        // The actual checking of whose turn and only on next move should be done by take?
        return possibleMoves;
    } 
    
    /*
     * Name: rookPossibleMoves
     * Description: The function goes through every move case for a rook
     * Returns: - ArrayList<Integer>
     *          - An empty ArrayList<Integer> if there are no possible moves
     * NOTE: Rook can potentialy move to the max x or min x and max y or min y so the destination 
     *       is going to be the max coordinates in the direction specified.
     */
    private ArrayList <Integer[]> rookPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece piece = null; 
        int destX = 0;
        int destY = 0;
        
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
    
    /*
     * Name: bishopPossibleMoves
     * Description: The function goes through every move case for a bishop
     * Returns: - ArrayList<Integer>
     *          - An empty ArrayList<Integer> if there are no possible moves
     * NOTE: Bishop can potentialy move to the max x or min x and max y or min y so the destination 
     *       is going to be the max coordinates in the direction specified.
     */
    private ArrayList <Integer[]> bishopPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp; 
        int destX = 0;
        int destY = 0;
        
        
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
    
    /*
     * Name: kingPossibleMoves
     * Description: The function goes through every move case for a king
     * Returns: - ArrayList<Integer>
     *          - An empty ArrayList<Integer> if there are no possible moves
     */
    private ArrayList <Integer[]> kingPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        ChessPiece king;
        int destX = 0;
        int destY = 0;
        Boolean isOccupied;
        Boolean isCheck;
        Boolean boolReturn;
        Board tempBoard = new VanillaChessBoard();
        ArrayList <ChessPiece> copyOfPieces;
        ArrayList<Message> tempMessages = new ArrayList(this.messages);
        Boolean kingSide  = false;
        Boolean queenSide = false;
        
        this.messages = new ArrayList();
        
        king = board.getPieceAtPosition(curX,curY);
        
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
        
        //Case 9: Castling king side 
        ChessPiece rook = board.getPieceAtPosition(8,king.getY());
        if(rook != null && rook.getPreviousX() == 0 && rook.getPreviousY() == 0 && rook.getState() == 0){
            if(king.getPreviousX() == 0 && king.getPreviousY() == 0){
                temp = moveRight(curX,curY,curX+2,curY,board);
                if(temp.size() == 2 && temp.get(1)[0] == curX+2 && temp.get(1)[1] == curY){
                    //System.out.println(king.getChessPieceColour());
                    for(Integer[] coordinates : temp){
                        isOccupied = board.isPosistionOcuppied(coordinates[0], coordinates[1]);
                        if(isOccupied == false){
                            tempBoard = new VanillaChessBoard();
                            copyOfPieces = copyPiecesList (board.getPieces());
                            tempBoard.setPieces(copyOfPieces);
//                            System.out.println("curx/cury ("+ curX+ ","+curY +")");
//                            System.out.println("coordinates ("+ coordinates[0]+ ","+coordinates[1] +")");
                              //System.out.println("From king possible moves");
                              boolReturn = take(curX, curY, coordinates[0],coordinates[1],tempBoard);
//                            System.out.println("take return = " + boolReturn);
                            if(boolReturn == false){
                               kingSide = false;
                               break;
                            }else{
                               kingSide = true;
                            }  
                        }else{
                            kingSide = false;
                            break;
                        }
                    }
                    
                    if(kingSide == true){
                        possibleMoves.add(temp.get(1));
                        this.castleKingSide = kingSide;
                    }
                    
                }
            }
        }
        
        //Case 10: Castling queen side
        rook = board.getPieceAtPosition(1,king.getY());
        if(rook != null && rook.getPreviousX() == 0 && rook.getPreviousY() == 0 && rook.getState() == 0){
            if(king.getPreviousX() == 0 && king.getPreviousY() == 0){
                temp = moveLeft(curX,curY,curX-2,curY,board);
                if(temp.size() == 2 && temp.get(1)[0] == curX-2 && temp.get(1)[1] == curY){
                    //System.out.println(king.getChessPieceColour());
                    for(Integer[] coordinates : temp){
                        isOccupied = board.isPosistionOcuppied(coordinates[0], coordinates[1]);
                        if(isOccupied == false){
                            tempBoard = new VanillaChessBoard();
                            copyOfPieces = copyPiecesList (board.getPieces());
                            tempBoard.setPieces(copyOfPieces);
//                            System.out.println("curx/cury ("+ curX+ ","+curY +")");
//                            System.out.println("coordinates ("+ coordinates[0]+ ","+coordinates[1] +")");
                              boolReturn = take(curX, curY, coordinates[0],coordinates[1],tempBoard);
//                            System.out.println("take return = " + boolReturn);
                            if(boolReturn == false){
                               queenSide = false;
                               break;
                            }else{
                               queenSide = true;
                            }  
                        }else{
                            queenSide = false;
                            break;
                        }
                    }
                    
                    if(queenSide == true){
                        possibleMoves.add(temp.get(1));
                        this.castleQueenSide = queenSide;
                    }
                    
                }
            }
        }
        this.messages = new ArrayList(tempMessages);
        return possibleMoves;
    }
    
    /*
     * Name: queenPossibleMoves
     * Description: The function goes through every move case for a queen
     * Returns: - ArrayList<Integer>
     *          - An empty ArrayList<Integer> if there are no possible moves
     * Note: Queen behaves like a bishop and rook
     */
    private ArrayList <Integer[]> queenPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> rookMoves = new ArrayList();
        ArrayList <Integer[]> bishopMoves = new ArrayList();
        int destX = 0;
        int destY = 0;
        
        rookMoves = rookPossibleMoves(curX,curY,board);
        bishopMoves = bishopPossibleMoves(curX,curY,board);
        
        possibleMoves.addAll(rookMoves);
        possibleMoves.addAll(bishopMoves);
        return possibleMoves;
    }
    
    /*
     * Name: knightPossibleMoves
     * Description: The function goes through every move case for a knight
     * Returns: - ArrayList<Integer>
     *          - An empty ArrayList<Integer> if there are no possible moves
     */
    private ArrayList <Integer[]> knightPossibleMoves(int curX,int curY, Board board){
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ArrayList <Integer[]> temp;
        int destX = 0;
        int destY = 0;
        
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
    
    /*
     * Name: printList
     * Description: Prints an ArrayList<Integer[]> to the terminal 
     */
    private void printList(ArrayList <Integer[]> possibleMoves,String piece){
        System.out.println(piece);
        for(int i = 0; i < possibleMoves.size(); i++){
            System.out.println("(" + possibleMoves.get(i)[0] + "," + possibleMoves.get(i)[1] + ")" );
        }
    }
    
    /*
     * Name: tryMoveCheck
     * Description: The function determines whether the new coordinates are a valid move for a given piece
     * Returns: - true: The piece can move to the new coordinate
     *          - false: The new coordinates are not in the list of possible moves for the piece
     */
    private Boolean tryMoveCheck(int curX,int curY,int newX, int newY,Board board){
        int foundFlag = 0;
        ArrayList <Integer[]> possibleMoves = new ArrayList();
        ChessPiece piece = board.getPieceAtPosition(curX, curY);
        
        if(piece.getChessPieceName() == ChessPiece.ChessPieces.PAWN){
            possibleMoves = pawnPossibleMoves(piece.getChessPieceColour().toString(),curX,curY,board);
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.ROOK){
            possibleMoves = rookPossibleMoves(curX,curY,board);
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.BISHOP){
            possibleMoves = bishopPossibleMoves(curX,curY,board);
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KING){
            possibleMoves = kingPossibleMoves(curX,curY,board);
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.QUEEN){
            possibleMoves = queenPossibleMoves(curX,curY,board);
        }else if(piece.getChessPieceName() == ChessPiece.ChessPieces.KNIGHT){
            possibleMoves = knightPossibleMoves(curX,curY,board);
        }else{
            System.out.println("ERROR: The coordinates passed to tryMoveCheck did not contain a vanilla chess piece");
            System.exit(1);
        }
        
        if(possibleMoves.size() == 0){
            return false;
        }else{//Check if the new coordinates are within the list of possible moves for that piece
            //Loop through the possible moves to see if the new x and new y are in that list
            for(int i = 0; i < possibleMoves.size();i++){
                if(possibleMoves.get(i)[0] == newX && possibleMoves.get(i)[1] == newY){
                    foundFlag = 1;
                }
            }
            if(foundFlag == 1){
                return true;
            }else{
                return false;
            }
        }
    }
    
    public static final int STATE_NORMAL = 1;
    public static final int STATE_GAME_OVER = 2;
}
