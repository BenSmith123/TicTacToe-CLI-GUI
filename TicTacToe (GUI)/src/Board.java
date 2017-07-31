import javax.swing.JPanel;

public class Board {
	
    private final int rows = 3;
    private final int columns = 3;

    public GameButton[][] board = new GameButton[rows][columns]; // class has a 2D GameButton array

    public Board() {} // default constructor (don't do anything until Board is used)

    // getters and setters
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // returns the GameButton object at param (row, col)
    public GameButton getGameButton(int row, int col){
        return board[row][col];
    }

    // mark the button in the array at (row, col) with the players symbol
    public void markButton(int row, int col, char playerSymbol){
        board[row][col].setState(playerSymbol);
    }

    // create the 2D array of game buttons with a default state ' ' (blank)
    // add the buttons to the param JPanel
    public void makeButtons(JPanel panel){
        for (int col = 0; col < rows; col++){

            for (int row = 0; row < columns; row++){

                board[row][col] = new GameButton(' ', row, col, panel);

            }
        }
    }


    // check to see if the board is full
    public boolean isBoardFull() {

        for (int row = 0; row < rows; row++){

            for (int col = 0; col < columns; col++){

                    if (this.board[row][col].getState() == ' '){
                        return false; // if any empty cells found, board not full
                    }
                }
            }

        return true; // if no empty cells were found
    }


    // if currentPlayer wins (by row)
    public boolean playerWinRow(char playerSymbol) {

        boolean playerWinRow = false;

        for (int row = 0; row < rows; row++){

            if ((this.board[row][0].getState() == playerSymbol) && (this.board[row][1].getState() == playerSymbol) && (this.board[row][2].getState() == playerSymbol)){
                playerWinRow = true;
                break; // if any of the rows are a winning row, break the loop
            }
            else{
                playerWinRow = false; // if there are no winning rows
            }
        }
        return playerWinRow;
    }


    // if currentPlayer wins (by column)
    public boolean playerWinColumn(char playerSymbol) {

        boolean playerWinColumn = false;

        for (int col = 0; col < rows; col++){

            if ((this.board[0][col].getState() == playerSymbol) && (this.board[1][col].getState() == playerSymbol) && (this.board[2][col].getState() == playerSymbol)){
                playerWinColumn = true;
                break;
            }
            else{
                playerWinColumn = false;
            }
        }
        return playerWinColumn;
    }


    // if currentPlayer wins (across)
    public boolean playerWinAcross(char playerSymbol) {

        boolean playerWinAcross = false;

            if ((this.board[0][0].getState() == playerSymbol) && (this.board[1][1].getState() == playerSymbol) && (this.board[2][2].getState() == playerSymbol)){
                playerWinAcross = true;
            }
            else if ((this.board[2][0].getState() == playerSymbol) && (this.board[1][1].getState() == playerSymbol) && (this.board[0][2].getState() == playerSymbol)){
                playerWinAcross = true;
            }

        return playerWinAcross;
    }




}
