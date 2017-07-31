import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

//event class for TextField objects used in main (implements ActionListener)
public class TextFieldEvent implements KeyListener {

    @Override
    public void keyReleased(KeyEvent event) {

        JTextField currentButton = (JTextField) event.getSource();

        String input = null;

        // every time key is typed, update the name / password
        if (currentButton.getName() == "name") {
            TicTacToeApplication.currentPlayer.setName(currentButton.getText());
        }

        if (currentButton.getName() == "password") {
            TicTacToeApplication.currentPlayer.setPassword(currentButton.getText());
        }

        if (currentButton.getName() == "id") {
            input = currentButton.getText();
        }

        // when enter is pressed:
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {

            // call create new account method if the button being used is Name or Password button
            if (currentButton.getName() == "name" || currentButton.getName() == "password") {
                TicTacToeApplication.makeNewAccount();
            }

            if (currentButton.getName() == "id") {
                TicTacToeApplication.signinAccount(input);
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

}
