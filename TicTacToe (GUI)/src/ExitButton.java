import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

// this class is a custom JButton object (ExitButton) that implements ActionListener
// used in menu/game layouts to return to the main menu
public class ExitButton extends JButton implements ActionListener {
		
    // default constructor 
    public ExitButton(){ 
        this.setText(null);
    }

    // constructor that takes String to set the ExitButton text and creates a new listener object to add to the button
    public ExitButton(String buttonText){

        this.setText(buttonText);

        ExitButton listener = new ExitButton();
        this.addActionListener(listener);
    }

    // when button is pressed, ask to confirm exiting
    @Override
    public void actionPerformed(ActionEvent arg0) {

        Object[] options = {"Yes","No"};

        int choice = JOptionPane.showOptionDialog(null,
            "Exit to Main Menu?",
            null,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.DEFAULT_OPTION,
            null,
            options,
            options[1]);


        if (choice == 0) { // exit and go to the main menu
                TicTacToeApplication.showMenu();
        }

    }

}
