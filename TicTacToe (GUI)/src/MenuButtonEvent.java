import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

// event class for MenuButton objects (implements ActionListener)
// object of this class is added to every MenuButton
public class MenuButtonEvent implements ActionListener{

    public void actionPerformed(ActionEvent event) { // when a button is pressed

        String buttonText = ((MenuButton) event.getSource()).getText(); // get the string of the button that was pressed

        // compare the string (name of the button) to choose which method to perform (depending on what button was pressed)
        if (buttonText.equalsIgnoreCase("PLAY!")) {
            TicTacToeApplication.pregameMessage(); // display message if players are using guest account, then start game
        }

        if (buttonText.equalsIgnoreCase("Play as guests (scores will not be saved).")){
            TicTacToeApplication.setPlayersGuest(); // set both the players to guest accounts
            TicTacToeApplication.setupGame(); // set up the game
        }

        if (buttonText.equalsIgnoreCase("Sign In")){
            TicTacToeApplication.signin();
        }

        if (buttonText.equalsIgnoreCase("Create new account")){
            TicTacToeApplication.createAccount();
        }

        if (buttonText.equalsIgnoreCase("Show account details")){
                TicTacToeApplication.showAccountDetails();
        }

        if (buttonText == "Exit"){
            
            JOptionPane.showMessageDialog(null,"Your account information will automatically be saved.\nThank you for playing!");
            
            Accounts.updateDatabase(); // save all player accounts into the database

            System.exit(0); // force exit
            
        }

    }

}
