import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JPanel;

// this class is a custom JButton object (MenuButton)
// used in the menu layout
public class MenuButton extends JButton {

    private GridBagConstraints gbc = new GridBagConstraints();

    // constructor sets the text of the button and positions it (x,y) and the adds the button to the param JPanel
    public MenuButton(String buttonText, int x, int y, JPanel panel) {

        this.setText(buttonText);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.ipady = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL; // fill the grid space horizontally with the button

        panel.add(this,gbc); // add the button and its constraints to the param panel

        // create a new listener for each button and add it to the button
        MenuButtonEvent listener = new MenuButtonEvent();
        this.addActionListener(listener);

    }
	
}
