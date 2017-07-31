import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

// this class is a custom JButton object (GameButton)
// used for the tic-tac-toe game 
public class GameButton extends JButton {

    private char state;
    private int row;
    private int column;
    private ImageIcon imageIcon = new ImageIcon("blank.png");;

    // constructor creates a GameButton and adds a listener and adds the game button to the param JPanel
    public GameButton(char state, int row, int column, JPanel panel){

        this.setState(state);
        this.setRow(row);
        this.setColumn(column);
        panel.add(this);

        GameButtonEvent listener = new GameButtonEvent(); // create a new listener for current GameButton
        this.addActionListener(listener); // add new listener to GameButton

    }

    // changes the state of the GameButton with row/col and sets it to the current players game symbol
    public void changeState(int row, int col){

        if (this.state == ' '){ // if space is blank, mark it with the currentPlayers symbol

            this.setState(TicTacToeApplication.currentPlayer.getGameSymbol()); // set the current button state to the players symbol
            updateIcon(row,col); // update the current GameButtons icon
        }

        // if state isn't blank (already taken), nothing happens.. wait for input again

    }

    // updates the icon image depending on the GameButton state
    public void updateIcon(int row, int col) {

        if (this.getState() == ' '){
            imageIcon = new ImageIcon("blank.png");
        }

        if (this.getState() == 'X'){
            imageIcon = new ImageIcon("cross.png");
        }

        if (this.getState() == 'O'){
            imageIcon = new ImageIcon("naught.png");
        }

        // scale the icon to the GameButton
        int width = (imageIcon.getIconWidth())/2;

        this.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(width,-1,java.awt.Image.SCALE_SMOOTH)));
        // call the next step for the game to continue
        TicTacToeApplication.checkForWin(); // swap to next turn if symbol has been placed

    }

    // getters and setters
    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
