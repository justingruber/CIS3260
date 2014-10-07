package chess.views.terminals;

import chess.models.*;
import chess.models.ChessPiece;
import chess.models.games.VanillaChessGame;

public class VanillaChessTerminal extends ChessTerminal {
    
    
    public VanillaChessTerminal () {
        this.helpLines.add ("legend  - Shows what the characters on the board represent");
        this.helpLines.add ("a1 a2   - Moves the piece from a1 to a2. Change to the piece you actually want.");
    }
    
    public void update (User currentMover, VanillaChessGame.State state) {
        this.showMessages ();
        
        if (state == VanillaChessGame.State.NORMAL) {
            
        } else if (state == VanillaChessGame.State.GAME_OVER) {
            
        }
        
        System.out.println ("Player " + currentMover.getColour () + "/" + currentMover.getUsername () + "'s turn.");
        System.out.println ("What do you want to do?");
    }
    
    public void displayBoard () {
        System.out.println ();
        printBoard ();
        System.out.println ();
    }
    
    public void displayLegend () {
        System.out.println ("===============LEGEND===============");
        System.out.println ("The first letter of each pair represents the color. W is white. B is black.");
        System.out.println ("The second letter of each pair represents the type of piece.");
        System.out.println ("K - King");
        System.out.println ("Q - Queen");
        System.out.println ("B - Bishop");
        System.out.println ("N - Knight");
        System.out.println ("R - Rook");
        System.out.println ("P - Pawn");
        System.out.println ("====================================");
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
        String abc = "ABCDEFGH";
        System.out.print (" " + String.valueOf (VanillaChessTerminal.VERTICAL));
        
        for (int i = 0; i < abc.length (); i++) {
            System.out.print (abc.charAt (i) + " " + VanillaChessTerminal.VERTICAL);
        }
        
        System.out.println ();
        System.out.println (" " + repeatChar (VanillaChessTerminal.HORIZONTAL, 25));
        
        Board board = this.getBoard ();
        
        for (int j = 1; j <= 8; j++) {
            System.out.print (j + String.valueOf (VanillaChessTerminal.VERTICAL));
            
            for (int i = 1; i <= 8; i++) {
                ChessPiece piece = board.getPieceAtPosition (i, j);
                String color = " ";
                String pieceChar = " ";
                
                if (piece != null) {
                    color = (piece.getChessPieceColour () == ChessPiece.Colours.BLACK) ? "B" : "W";
                    pieceChar = " ";
                    ChessPiece.ChessPieces name = piece.getChessPieceName ();
                    
                    if (name == ChessPiece.ChessPieces.KING) {
                        pieceChar = String.valueOf (VanillaChessTerminal.KING);
                    } else if (name == ChessPiece.ChessPieces.QUEEN) {
                        pieceChar = String.valueOf (VanillaChessTerminal.QUEEN);
                    } else if (name == ChessPiece.ChessPieces.ROOK) {
                        pieceChar = String.valueOf (VanillaChessTerminal.ROOK);
                    } else if (name == ChessPiece.ChessPieces.BISHOP) {
                        pieceChar = String.valueOf (VanillaChessTerminal.BISHOP);
                    } else if (name == ChessPiece.ChessPieces.KNIGHT) {
                        pieceChar = String.valueOf (VanillaChessTerminal.KNIGHT);
                    } else if (name == ChessPiece.ChessPieces.PAWN) {
                        pieceChar = String.valueOf (VanillaChessTerminal.PAWN);
                    }
                }
                
                System.out.print (color + pieceChar + String.valueOf (VanillaChessTerminal.VERTICAL));
            }
            
            System.out.print (j);
            System.out.println ();
        }
        
        System.out.println (" " + repeatChar (VanillaChessTerminal.HORIZONTAL, 25));
        System.out.print (" " + String.valueOf (VanillaChessTerminal.VERTICAL));
        
        for (int i = 0; i < abc.length (); i++) {
            System.out.print (abc.charAt (i) + " " + VanillaChessTerminal.VERTICAL);
        }
        
        System.out.println ();
    }
    
    //http://unicode-table.com/en/#supplemental-mathematical-operators
    private static final char KING = 'K';
    private static final char QUEEN = 'Q';
    private static final char ROOK = 'R';
    private static final char BISHOP = 'B';
    private static final char KNIGHT = 'N';
    private static final char PAWN = 'P';
    
    private static final char HORIZONTAL = '-';
    private static final char VERTICAL = '|';
    
    
}