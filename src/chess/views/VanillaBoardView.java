package chess.views;

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




public class VanillaBoardView extends GameView{
	
	private char[][] tiles2 = new char[10][10];
        private Board board = null;
	public static void main(String[] args) {
              VanillaBoardView newBoard = new VanillaBoardView();
              newBoard.init();
		
	}
        
        
        private void init(){
            
            this.setupBoard();
            VanillaChessRules rules = new VanillaChessRules();
           
            
            
            rules.createBoard(ChessEnums.BoardTypes.VANILLA_CHESS_BOARD);
            board = rules.getBoardInstance();
            this.placePieces(board);
            this.printBoard();
            if(rules.tryMove(1, 2, 1, 3)){
               
            }
            
            
        }

	@Override
	public void placePieces(Board board){
            
            for(int i = 1; i <= 8; i++){
                
                for(int j = 1; j <= 8; j++){
                    ChessPiece piece = board.getPieceAtPosition(j, i);
                    
                   
                   if(piece == null){
                   } else {
                        if(piece.getState() != 1){
                            VanillaChessRules.ChessPieces name = piece.getChessPieceName();
                            ChessPiece.Colours colour = piece.getChessPieceColour();
                            if(colour == ChessPiece.Colours.WHITE){
                                if(name == VanillaChessRules.ChessPieces.KING){
                                    placePieceAtXY(j,i,'\u2654');
                                }else if(name == VanillaChessRules.ChessPieces.QUEEN){
                                    placePieceAtXY(j,i,'\u2655');  
                                }else if(name == VanillaChessRules.ChessPieces.ROOK){
                                    placePieceAtXY(j,i,'\u2656');
                                }else if(name == VanillaChessRules.ChessPieces.BISHOP){
                                    placePieceAtXY(j,i,'\u2657');   
                                }else if(name == VanillaChessRules.ChessPieces.KNIGHT){
                                    placePieceAtXY(j,i,'\u2658');   
                                }else if(name == VanillaChessRules.ChessPieces.PAWN){
                                    placePieceAtXY(j,i,'\u2659');  
                                }
                            } else{
                                if(name == VanillaChessRules.ChessPieces.KING){
                                    placePieceAtXY(j,i,'\u265A');
                                }else if(name == VanillaChessRules.ChessPieces.QUEEN){
                                    placePieceAtXY(j,i,'\u265B');  
                                }else if(name == VanillaChessRules.ChessPieces.ROOK){
                                    placePieceAtXY(j,i,'\u265C');
                                }else if(name == VanillaChessRules.ChessPieces.BISHOP){
                                    placePieceAtXY(j,i,'\u265D');   
                                }else if(name == VanillaChessRules.ChessPieces.KNIGHT){
                                    placePieceAtXY(j,i,'\u265E');   
                                }else if(name == VanillaChessRules.ChessPieces.PAWN){
                                    placePieceAtXY(j,i,'\u265F');  
                                }
                            }
                        }
                    }
                    
                }
            }
	}

        
        private void placePieceAtXY(int x, int y, char pieceID){
            tiles2[y][x] = pieceID;
        }
        
        
	@Override
        public void printBoard(){
            for(int i = 0; i < tiles2.length; i++){
                
                for(int j = 0; j < tiles2.length; j++){
                    System.out.print(tiles2[i][j]);
                }
                System.out.println();   
            }
        }
        
        
        
	private void setupBoard(){

		//Dynamic method of displaying the board

		int i = 0,j = 0;


		for (i = 0; i < tiles2.length; i++){
			for (j = 0; j < tiles2.length; j++){
					

				if(i == 0 && j == 0){
					tiles2[i][j] = '\u250F';
				}
				else if(i == 0 && j == (tiles2.length - 1)){
					tiles2[i][j] = '\u2513';
				}
				else if(i == (tiles2.length - 1) && j == 0){
					tiles2[i][j] = '\u2517';
				}
				else if((i > 1 || i < (tiles2.length - 2)) && j == 0){
					tiles2[i][j] = '\u2503';
				}
				else if((i > 1 || i < (tiles2.length - 2)) && j == (tiles2.length - 1)){
					tiles2[i][j] = '\u2503';
				}
				else if((j > 0 || j < tiles2.length - 1) && i == 0){
					tiles2[i][j] = '\u2501';
				}
				else if((j > 0 || j < tiles2.length - 1) && i == (tiles2.length - 1)){
					tiles2[i][j] = '\u2501';
				}
				else{
					tiles2[i][j] = '\u2B1A';
				}
				if(i == tiles2.length - 1 && j == tiles2.length - 1){
					tiles2[i][j] = '\u251B';
				}
				//System.out.print(tiles2[i][j]); 
			}
			//System.out.println();
		}
	}
}