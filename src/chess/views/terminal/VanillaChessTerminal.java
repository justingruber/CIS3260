package chess.views.terminal;

import chess.models.*;
import chess.models.ChessPiece;
//import chess.models.VanillaChessRules;

/*
Unicode Characters for chess:

Black Pieces:
- King = \u265A
- Queen = \u265B
- Rook = \u265C
- Bishop = \u265D
- Knight = \u265E
- Pawn = \u265F

White Pieces:
- King = \u2654
- Queen = \u2655
- Rook = \u2656
- Bishop = \u2657
- Knight = \u2658
- Pawn = \u2659

From here:
http://unicode-table.com/en/#supplemental-mathematical-operators

 */
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
    
    private void init() {

        this.setupBoard();
        VanillaChessRules rules = new VanillaChessRules();


        
        rules.createBoard(Board.BoardTypes.VANILLA_CHESS_BOARD);
        board = rules.getBoardInstance();
        this.placePieces(board);
        this.printBoard();
        if (rules.tryMove(2, 2, 2, 3)) {
            updatePieceLocation(2,2,2,3);
            this.printBoard();
            if(rules.tryMove(3, 1, 1, 3)){
                updatePieceLocation(3,1,1,3);
                this.printBoard();
                if(rules.tryMove(4, 2, 4, 4)){
                    updatePieceLocation(4,2,4,4);
                    this.printBoard();
                    if(rules.tryMove(4,1,4,2)){
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
                                placePieceAtXY(j, i, '\u2654');
                            } else if (name == ChessPiece.ChessPieces.QUEEN) {
                                placePieceAtXY(j, i, '\u2655');
                            } else if (name == ChessPiece.ChessPieces.ROOK) {
                                placePieceAtXY(j, i, '\u2656');
                            } else if (name == ChessPiece.ChessPieces.BISHOP) {
                                placePieceAtXY(j, i, '\u2657');
                            } else if (name == ChessPiece.ChessPieces.KNIGHT) {
                                placePieceAtXY(j, i, '\u2658');
                            } else if (name == ChessPiece.ChessPieces.PAWN) {
                                placePieceAtXY(j, i, '\u2659');
                            }
                        } else {
                            if (name == ChessPiece.ChessPieces.KING) {
                                placePieceAtXY(j, i, '\u265A');
                            } else if (name == ChessPiece.ChessPieces.QUEEN) {
                                placePieceAtXY(j, i, '\u265B');
                            } else if (name == ChessPiece.ChessPieces.ROOK) {
                                placePieceAtXY(j, i, '\u265C');
                            } else if (name == ChessPiece.ChessPieces.BISHOP) {
                                placePieceAtXY(j, i, '\u265D');
                            } else if (name == ChessPiece.ChessPieces.KNIGHT) {
                                placePieceAtXY(j, i, '\u265E');
                            } else if (name == ChessPiece.ChessPieces.PAWN) {
                                placePieceAtXY(j, i, '\u265F');
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
        tiles2[currY][currX] = '\u2B1A';
        //System.out.println(tmpChar);
    }

    @Override
    public void printBoard() {
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
                    tiles2[i][j] = '\u250F';
                } else if (i == 0 && j == (tiles2.length - 1)) {
                    tiles2[i][j] = '\u2513';
                } else if (i == (tiles2.length - 1) && j == 0) {
                    tiles2[i][j] = '\u2517';
                } else if ((i > 1 || i < (tiles2.length - 2)) && j == 0) {
                    tiles2[i][j] = '\u2503';
                } else if ((i > 1 || i < (tiles2.length - 2)) && j == (tiles2.length - 1)) {
                    tiles2[i][j] = '\u2503';
                } else if ((j > 0 || j < tiles2.length - 1) && i == 0) {
                    tiles2[i][j] = '\u2501';
                } else if ((j > 0 || j < tiles2.length - 1) && i == (tiles2.length - 1)) {
                    tiles2[i][j] = '\u2501';
                } else {
                    tiles2[i][j] = '\u2B1A';
                }

                if (i == tiles2.length - 1 && j == tiles2.length - 1) {
                    tiles2[i][j] = '\u251B';
                }
                //System.out.print(tiles2[i][j]); 
            }
            //System.out.println();
        }
    }
}