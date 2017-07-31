
public class Board {
	
	private final int rows = 3;
	private final int columns = 3;
	
	public char[][] board = new char[rows][columns];

	// getters and setters
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	// default constructor creates a new array with empty string value
	public Board() {
		
		for (int row = 0; row < rows; row++){
		
			for (int col = 0; col < columns; col++){
				
				this.board[row][col] = ' ';
				
			}
		}
	}
	
	// draw the current board
	public void drawBoard() {
		
		for (int row = 0; row < rows; row++){
			
			if (row == 0){
				System.out.println("   A   B   C");
			}
			System.out.print(row+1 +" ");
			
			for (int col = 0; col < columns; col++){
				
				System.out.print("[" + board[row][col] + "] ");
				
			}
			System.out.println();
		}
		System.out.println();	
	}
	

	public boolean markPlace(int row, char column, char playerSymbol) {
		
		int col = 0;
		
		// if input column is lowercase, set it to uppercase
		if (column > 67) {
			column -= 32;
		}
		
		// set input column to match array col
		if (column == 'A'){col = 1;}
		if (column == 'B'){col = 2;}
		if (column == 'C'){col = 3;}
		
		// if space isnt already taken
		if (board[row-1][col-1] == ' '){ // -1 because array starts at 0
			
			board[row-1][col-1] = playerSymbol; // set the board position to the playerSymbol
			return true;
		}
		
		return false; // if space is taken
	}
	
	
	public boolean isBoardFull() {
		
		boolean isBoardFull = false;
		
		for (int row = 0; row < rows; row++){
		
			for (int col = 0; col < columns; col++){
				
				if (this.board[row][col] == ' '){
					isBoardFull = false;
					break; // if a empty space is found, break loop
				}
				else{
					isBoardFull = true; // if no empty space is found
				}
			}
		}
		
		return isBoardFull;
	}
	
	
	public boolean playerWinRow(char playerSymbol) {
		
		boolean playerWinRow = false;

		// check through rows to see if theres any winning rows according to the current playerSymbol
		for (int row = 0; row < rows; row++){
			
			if ((this.board[row][0] == playerSymbol) && (this.board[row][1] == playerSymbol) && (this.board[row][2] == playerSymbol)){
				playerWinRow = true;
				break; // if any of the rows are a winning row, break the loop
			}
			else{
				playerWinRow = false; // if there are no winning rows
			}
		}
		return playerWinRow;
	}
	
	
	public boolean playerWinColumn(char playerSymbol) {
		
		boolean playerWinColumn = false;

		for (int col = 0; col < rows; col++){
			
			if ((this.board[0][col] == playerSymbol) && (this.board[1][col] == playerSymbol) && (this.board[2][col] == playerSymbol)){
				playerWinColumn = true;
				break;
			}
			else{
				playerWinColumn = false;
			}
		}
		return playerWinColumn;
	}
	
	
	
	public boolean playerWinAcross(char playerSymbol) {
		
		boolean playerWinAcross = false;

			if ((this.board[0][0] == playerSymbol) && (this.board[1][1] == playerSymbol) && (this.board[2][2] == playerSymbol)){
				playerWinAcross = true;
			}
			else if ((this.board[2][0] == playerSymbol) && (this.board[1][1] == playerSymbol) && (this.board[0][2] == playerSymbol)){
				playerWinAcross = true;
			}
		return playerWinAcross;
	}
	
	
	

}
