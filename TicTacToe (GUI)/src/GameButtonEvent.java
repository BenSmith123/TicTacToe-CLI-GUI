import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// event class for GameButton objects (implements ActionListener)
// object of this class is added to every GameButton
public class GameButtonEvent implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        GameButton aButton = (GameButton) event.getSource(); // get the GameButton object that was pressed

        aButton.changeState(aButton.getRow(), aButton.getColumn()); // change the state of the GameButton object

    }

}
