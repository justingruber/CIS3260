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
    
    public VanillaChessTerminal () {
        this.helpLines.add ("a1 a2   - Moves the piece from a1 to a2. Change to the piece you actually want.");
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
        tiles2[newY][newX] = tiles2[currY][currX];
        tiles2[currY][currX] = VanillaChessTerminal.BLANK;
    }
    
    private String repeatChar (char c, int n) {
        String s = "";
        
        for (int i = 0; i < n; i++) {
            s += c;
        }
        
        return s;
    }
    
    @Override
    public void printBoard() {
        int cellWidth = 2;
        String abc = "ABCDEFGH";
        System.out.print (" |");
        
        for (int i = 0; i < abc.length (); i++) {
            System.out.print (abc.charAt (i) + " " + VanillaChessTerminal.VERTICAL);
        }
        
        System.out.println ();
        System.out.println (" " + repeatChar (VanillaChessTerminal.HORIZONTAL, 25));
        
        Board board = this.getBoard ();
        
        for (int j = 1; j <= 8; j++) {
            System.out.print (j + "|");
            
            for (int i = 1; i <= 8; i++) {
                ChessPiece piece = board.getPieceAtPosition (i, j);
                String color = " ";
                String pieceChar = " ";
                
                if (piece != null) {
                    color = (piece.getChessPieceColour () == ChessPiece.Colours.BLACK) ? "B" : "W";
                    pieceChar = " ";
                    ChessPiece.ChessPieces name = piece.getChessPieceName ();
                
                    if (name == ChessPiece.ChessPieces.KING) {
                        pieceChar = "K";
                    } else if (name == ChessPiece.ChessPieces.QUEEN) {
                        pieceChar = "Q";
                    } else if (name == ChessPiece.ChessPieces.ROOK) {
                        pieceChar = "R";
                    } else if (name == ChessPiece.ChessPieces.BISHOP) {
                        pieceChar = "B";
                    } else if (name == ChessPiece.ChessPieces.KNIGHT) {
                        pieceChar = "N";
                    } else if (name == ChessPiece.ChessPieces.PAWN) {
                        pieceChar = "P";
                    }
                }
                
                System.out.print (color + pieceChar + "|");
            }
            
            System.out.print (j);
            System.out.println ();
        }
        
        /*for (int i = 0; i < tiles2.length; i++) {
            
            if (i > 0 && i < 9) {
                System.out.print(i);
            }
            else{
                System.out.print(" ");
            }
            
            for (int j = 0; j < tiles2.length; j++) {
                
                System.out.print(tiles2[i][j]);
            }
            
            if (i > 0 && i < 9) {
                System.out.print(i);
            }
            else{
                System.out.print(" ");
            }
            
            System.out.println();
        }*/
        
        System.out.println (" " + repeatChar (VanillaChessTerminal.HORIZONTAL, 25));
        System.out.print (" |");
        
        for (int i = 0; i < abc.length (); i++) {
            System.out.print (abc.charAt (i) + " " + VanillaChessTerminal.VERTICAL);
        }
        
        System.out.println ();
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
                    //Left Col
                    tiles2[i][j] = VanillaChessTerminal.VERTICAL;
                } else if ((i > 1 || i < (tiles2.length - 2)) && j == (tiles2.length - 1)) {
                    //Right Col
                    tiles2[i][j] = VanillaChessTerminal.VERTICAL;
                } else if ((j > 0 || j < tiles2.length - 1) && i == 0) {
                    //Top row
                    tiles2[i][j] = VanillaChessTerminal.HORIZONTAL;
                } else if ((j > 0 || j < tiles2.length - 1) && i == (tiles2.length - 1)) {
                    //Bottom Row
                    tiles2[i][j] = VanillaChessTerminal.BOTTOMLINE;
                } else {
                    tiles2[i][j] = VanillaChessTerminal.BLANK;
                }

                if (i == tiles2.length - 1 && j == tiles2.length - 1) {
                    tiles2[i][j] = VanillaChessTerminal.BOTTOM_RIGHT;
                }
            }
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
    
    private static final char TOP_LEFT = ' ';
    private static final char TOP_RIGHT = ' ';
    private static final char BOTTOM_LEFT = ' ';
    private static final char BOTTOM_RIGHT = ' ';
    private static final char HORIZONTAL = '-';
    private static final char VERTICAL = '|';
    private static final char BLANK = '.';
    private static final char BOTTOMLINE = '-';
}