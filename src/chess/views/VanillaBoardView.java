package chess.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




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


	public static void main(String[] args) {
		VanillaBoardView newBoard = new VanillaBoardView();
		newBoard.demoBoard();
	}

	@Override
	public void placePieces(){

	}

	@Override
	public void printBoard(){

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
				System.out.print(tiles2[i][j]); 
			}
			System.out.println();
		}
	}


	@Override
	public void demoBoard(){

		
		





		//Single char array to display board
		
		/*
		char tiles[] = new char[10*10+10];
		Arrays.fill(tiles, '\u2B1A');
		for (int x = 0; x < 10; x++){
			if(x == 0){
				tiles[x] = '\u250F'; // Top left corner
			}
			else if(x > 1 || x < 8){
				tiles[x] = '\u2501'; // Top bars
				tiles[x*11] = '\u2503'; // Left bars
				tiles[x*11+9] = '\u2503'; // Right bars
				tiles[99] = '\u2517';	// Bottom left corner
				tiles[108] = '\u251B'; // Bottom right corner
				tiles[109-x] = '\u2501'; // Bottom row
			}
			if(x == 9){
				tiles[x] = '\u2513'; // Top right corner
			}
			tiles[x*11+10] = '\n';
		}


		System.out.println(tiles);
		*/
	}
}