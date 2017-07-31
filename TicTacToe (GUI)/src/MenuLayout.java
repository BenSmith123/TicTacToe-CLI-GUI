import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// class that holds all the objects used in main
// the frame, panel and components
public class MenuLayout {

    private JFrame frame;
    private GridBagConstraints gbc;
    private JLabel titleText;
    private JLabel subTitleText;
    private JPanel menuButtons;
    private JPanel gridButtons;
    private ExitButton exitButton;
    private Board board;
    private JPanel newAccountPanel;
    private JPanel signinPanel;
    private JTextField idTF;
    private JTextField nameTF;
    private JTextField passwordTF;

    public MenuLayout(){

        frame = new JFrame("Tic-Tac-Toe"); // the frame
        gbc = new GridBagConstraints(); // constraints for all frame components

        menuButtons = new JPanel(new GridBagLayout()); // panel for menu buttons (main menu)
        gridButtons = new JPanel(new GridLayout(0,3)); // panel for the tictactoe GameButtons
        newAccountPanel = new JPanel(new GridBagLayout()); // panel for creating new account menu
        signinPanel = new JPanel(new GridBagLayout()); // panel for signin account menu

        exitButton = new ExitButton("< Quit to Main Menu"); // exit button for panels to use

        titleText = new JLabel("Main Menu", JLabel.CENTER); // title of the panel being used
        subTitleText = new JLabel(); // subtitle of panel being used

        board = new Board(); // board object used throughout application

        // text fields for entering in an ID, name or password
        idTF = new JTextField(10);
        nameTF = new JTextField(10);;
        passwordTF = new JTextField(10);

    }

    // GETTERS FOR ALL CLASS OBJECTS

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JLabel getTitleText() {
        return titleText;
    }

    public JLabel getSubTitleText() {
        return subTitleText;
    }

    public void setSubTitleText(JLabel subTitleText) {
        this.subTitleText = subTitleText;
    }

    public JPanel getMenuButtons() {
        return menuButtons;
    }

    public JPanel getGridButtons() {
        return gridButtons;
    }

    public void setTitleText(JLabel titleText) {
        this.titleText = titleText;
    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    public Board getBoard() {
        return board;	
    }

    public JPanel getNewAccountPanel() {
        return newAccountPanel;
    }

    public JPanel getSigninPanel() {
        return signinPanel;
    }

    public JTextField getIDTF() {
        return idTF;
    }

    public JTextField getNameTF(){
        return nameTF;
    }

    public JTextField getPasswordTF(){
        return passwordTF;
    }

}
