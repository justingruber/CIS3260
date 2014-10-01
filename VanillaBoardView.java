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



*/











public class VanillaBoardView extends GameView{
	
	public static void main(String[] args) {
		VanillaBoardView newBoard = new VanillaBoardView();
		newBoard.demoBoard();
	}

	@Override
	public void placePieces(){

	}

	@Override
	public void printBoard(){

	}


	@Override
	public void demoBoard(){

				
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


		/*int x = 0;
		int y = 0;
		for (x = 0; x < 8; x++){
			System.out.print("___");
		}
		System.out.println();
		for (x = 0; x < 8; x++) {
			System.out.print("|");				

			for(y = 0; y < 8; y++){
				System.out.print("__|");
			}
			System.out.println();
		}*/
	}


}