public abstract class GameView {

	public static void main(String[] args) {
		
		int x = 0;
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
		}
	}
	
	abstract void printBoard(); 
	abstract void placePieces();

}