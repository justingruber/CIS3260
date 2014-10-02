package chess;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Chess {
    
    

    public static void main(String[] args) {
        VanillaChessRules rules = new VanillaChessRules();
        
        rules.createBoard(ChessEnums.BoardTypes.VANILLA_CHESS_BOARD);
        Board board = rules.getBoardInstance();
        
        System.out.println(board.isPosistionOcuppied(1, 3)); 
        
        //ChessPiece piece = board.getPieceAtPosition(1, 1);
        
        //System.out.println(piece.getChessPieceName());
        
        //piece.setChessPieceName(VanillaChessRules.ChessPieces.KING);
        
        //System.out.println(board.getPieceAtPosition(1, 1).getChessPieceName());
        //ChessEnums.BoardTypes boardType = board.getBoardType();
        
        //System.out.println(boardType);
        
        //boardType = ChessEnums.BoardTypes.HELLO;
        //System.out.println(boardType);
        
        /*ArrayList <ChessEnums.BoardTypes> boardTypes = new ArrayList();
        
        boardTypes = rules.getBoardTypes();
        System.out.println(boardTypes);
        boardTypes.add(ChessEnums.BoardTypes.HELLO);
        boardTypes.add(ChessEnums.BoardTypes.HELLO);
        
        ArrayList <ChessEnums.BoardTypes> boardTypes1 = new ArrayList();
        
        boardTypes1 = rules.getBoardTypes();
        
        System.out.println(boardTypes1);*/

        //rules.createBoard(ChessEnums.BoardTypes.VANILLA_CHESS_BOARD);
        
        //Board board = rules.getBoardInstance();
        
        //System.out.println(board.getPieces().get(0).getChessPieceName());
        
        
    }
}
