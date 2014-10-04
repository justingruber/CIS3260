package chess.views.terminals;

import chess.models.*;
import chess.models.messages.Message;
import chess.models.ChessPiece;

public class VanillaChessTerminal extends ChessTerminal {

    private char[][] tiles2 = new char[10][10];
    private Board board = null;

    public static void main(String[] args) {
        VanillaChessTerminal newBoard = new VanillaChessTerminal();
        newBoard.init();

    }
    
    @Override
    public void update () {
        setupBoard ();
        placePieces (this.getBoard ());
        printBoard ();
    }
    
    @Override
    public void showMessage (Message message) {
        System.out.println (message.getType () + ": " + message.getText ());
    }
    
    private void init() {

        this.setupBoard();
        VanillaChessRules rules = new VanillaChessRules();


        
        rules.createBoard(Board.BoardTypes.VANILLA_CHESS_BOARD);
        board = rules.getBoardInstance();
        this.placePieces(board);
        this.printBoard();
        if (rules.tryMove(ChessPiece.Colours.BLACK, 2, 2, 2, 3)) {
            updatePieceLocation(2,2,2,3);
            this.printBoard();
            if(rules.tryMove(ChessPiece.Colours.BLACK, 3, 1, 1, 3)){
                updatePieceLocation(3,1,1,3);
                this.printBoard();
                if(rules.tryMove(ChessPiece.Colours.BLACK, 4, 2, 4, 4)){
                    updatePieceLocation(4,2,4,4);
                    this.printBoard();
                    if(rules.tryMove(ChessPiece.Colours.BLACK, 4,1,4,2)){
                        updatePieceLocation(4,1,4,3);
                        this.printBoard();
                    }
                }
            }
            
            this.printBoard();
        } else {
            System.out.println("false");
        }


    }

    @Override
    public void placePieces(Board board) {

        for (int i = 1; i <= 8; i++) {

            for (int j = 1; j <= 8; j++) {
                ChessPiece piece = board.getPieceAtPosition(j, i);


                if (piece == null) {
                } else {
                    if (piece.getState() != 1) {
                        ChessPiece.ChessPieces name = piece.getChessPieceName();
                        ChessPiece.Colours colour = piece.getChessPieceColour();
                        if (colour == ChessPiece.Colours.WHITE) {
                            if (name == ChessPiece.ChessPieces.KING) {
                                placePieceAtXY(j, i, VanillaChessTerminal.WHITE_KING);
                            } else if (name == ChessPiece.ChessPieces.QUEEN) {
                                placePieceAtXY(j, i, VanillaChessTerminal.WHITE_QUEEN);
                            } else if (name == ChessPiece.ChessPieces.ROOK) {
                                placePieceAtXY(j, i, VanillaChessTerminal.WHITE_ROOK);
                            } else if (name == ChessPiece.ChessPieces.BISHOP) {
                                placePieceAtXY(j, i, VanillaChessTerminal.WHITE_BISHOP);
                            } else if (name == ChessPiece.ChessPieces.KNIGHT) {
                                placePieceAtXY(j, i, VanillaChessTerminal.WHITE_KNIGHT);
                            } else if (name == ChessPiece.ChessPieces.PAWN) {
                                placePieceAtXY(j, i, VanillaChessTerminal.WHITE_PAWN);
                            }
                        } else {
                            if (name == ChessPiece.ChessPieces.KING) {
                                placePieceAtXY(j, i, VanillaChessTerminal.BLACK_KING);
                            } else if (name == ChessPiece.ChessPieces.QUEEN) {
                                placePieceAtXY(j, i, VanillaChessTerminal.BLACK_QUEEN);
                            } else if (name == ChessPiece.ChessPieces.ROOK) {
                                placePieceAtXY(j, i, VanillaChessTerminal.BLACK_ROOK);
                            } else if (name == ChessPiece.ChessPieces.BISHOP) {
                                placePieceAtXY(j, i, VanillaChessTerminal.BLACK_BISHOP);
                            } else if (name == ChessPiece.ChessPieces.KNIGHT) {
                                placePieceAtXY(j, i, VanillaChessTerminal.BLACK_KNIGHT);
                            } else if (name == ChessPiece.ChessPieces.PAWN) {
                                placePieceAtXY(j, i, VanillaChessTerminal.BLACK_PAWN);
                            }
                        }
                    }
                }

            }
        }
    }

    private void placePieceAtXY(int x, int y, char pieceID) {
        tiles2[y][x] = pieceID;
    }
    private void updatePieceLocation(int currX, int currY, int newX, int newY){
        //char tmpChar = tiles2[currY][currX];
        tiles2[newY][newX] = tiles2[currY][currX];
        tiles2[currY][currX] = VanillaChessTerminal.BLANK;
        //System.out.println(tmpChar);
    }

    @Override
    public void printBoard() {
        String abc = "ABCDEFGH";
        
        for (int i = 0; i < abc.length (); i++) {
            System.out.print (abc.charAt (i));
        }
        
        System.out.println ();
        
        for (int i = 0; i < tiles2.length; i++) {

            for (int j = 0; j < tiles2.length; j++) {
                System.out.print(tiles2[i][j]);
            }
            System.out.println();
        }
    }

    private void setupBoard() {

        //Dynamic method of displaying the board

        int i = 0, j = 0;


        for (i = 0; i < tiles2.length; i++) {
            for (j = 0; j < tiles2.length; j++) {


                if (i == 0 && j == 0) {
                    tiles2[i][j] = VanillaChessTerminal.TOP_LEFT;
                } else if (i == 0 && j == (tiles2.length - 1)) {
                    tiles2[i][j] = VanillaChessTerminal.TOP_RIGHT;
                } else if (i == (tiles2.length - 1) && j == 0) {
                    tiles2[i][j] = VanillaChessTerminal.BOTTOM_LEFT;
                } else if ((i > 1 || i < (tiles2.length - 2)) && j == 0) {
                    tiles2[i][j] = VanillaChessTerminal.VERTICAL;
                } else if ((i > 1 || i < (tiles2.length - 2)) && j == (tiles2.length - 1)) {
                    tiles2[i][j] = VanillaChessTerminal.VERTICAL;
                } else if ((j > 0 || j < tiles2.length - 1) && i == 0) {
                    tiles2[i][j] = VanillaChessTerminal.HORIZONTAL;
                } else if ((j > 0 || j < tiles2.length - 1) && i == (tiles2.length - 1)) {
                    tiles2[i][j] = VanillaChessTerminal.HORIZONTAL;
                } else {
                    tiles2[i][j] = VanillaChessTerminal.BLANK;
                }

                if (i == tiles2.length - 1 && j == tiles2.length - 1) {
                    tiles2[i][j] = VanillaChessTerminal.BOTTOM_RIGHT;
                }
                //System.out.print(tiles2[i][j]); 
            }
            //System.out.println();
        }
    }
    
    //http://unicode-table.com/en/#supplemental-mathematical-operators
    private static final char BLACK_KING = 'k';
    private static final char BLACK_QUEEN = 'q';
    private static final char BLACK_ROOK = 'r';
    private static final char BLACK_BISHOP = 'b';
    private static final char BLACK_KNIGHT = 'n';
    private static final char BLACK_PAWN = 'p';
    
    private static final char WHITE_KING = 'K';
    private static final char WHITE_QUEEN = 'Q';
    private static final char WHITE_ROOK = 'R';
    private static final char WHITE_BISHOP = 'B';
    private static final char WHITE_KNIGHT = 'N';
    private static final char WHITE_PAWN = 'P';
    
    private static final char TOP_LEFT = '\u250F';
    private static final char TOP_RIGHT = '\u2513';
    private static final char BOTTOM_LEFT = '\u2517';
    private static final char BOTTOM_RIGHT = '\u251B';
    private static final char HORIZONTAL = '\u2501';
    private static final char VERTICAL = '\u2503';
    private static final char BLANK = '.';
}