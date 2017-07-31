import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class TicTacToeApplication {

    private static Player player1 = new Player(); // create player 1 & 2 as guests
	private static Player player2 = new Player("Guest2"); // set the same of player 2 to guest
	static Player currentPlayer; // current player swaps between player 1 and 2 so they both can be used by methods
	private static MenuLayout layout = new MenuLayout(); // holds all the panels, textFields and the frame

	
	public static void main(String args[]){
            
            Accounts.addAccount(player1.getPlayerID(), player1); // add the guest accounts into the accounts hashmap
            Accounts.addAccount(player2.getPlayerID(), player2);
            
            Accounts.loadDatabase(); // load the existing accounts to be used at runtime (Accounts.hashmap)

            // get the frame and all of its components
            JFrame frame = layout.getFrame();
            GridBagConstraints gbc = layout.getGbc();
            ExitButton exitButton = layout.getExitButton();
            Board board = layout.getBoard();

            JPanel gridButtons = layout.getGridButtons();
            JPanel menuButtons = layout.getMenuButtons();
            JLabel titleText = layout.getTitleText();
            JLabel subTitleText = layout.getSubTitleText();
            JPanel signinPanel = layout.getSigninPanel();
            JPanel newAccountPanel = layout.getNewAccountPanel();

            JTextField idTF = layout.getIDTF();
            JTextField nameTF = layout.getNameTF();
            JTextField passwordTF = layout.getPasswordTF();


            // create the frame and set frame attributes
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(true);
            frame.setTitle("Tic-Tac-Toe");
            frame.setLayout(new GridBagLayout());


            // set the size of the window depending on the resolution
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            frame.setSize(screenSize.width/3, (screenSize.height/2)+80);
            frame.setLocation(screenSize.width/3, screenSize.height/2 - frame.getHeight()/2);


            // Create the grid buttons (in-game buttons)
            gbc.fill = GridBagConstraints.BOTH; // fill all space in their grid
            gbc.weightx = 2.0; // set the weight - size
            gbc.weighty = 2.0;
            gbc.gridx = 0; // horizontal x to 0
            gbc.gridy = 3; // vertical y, set the buttons to below any other components
            board.makeButtons(gridButtons); // create the button objects (2D array - state, row, col)
            // add the buttons to the gridButtons panel
            frame.add(gridButtons, gbc);


            // Create exit button
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.insets = new Insets(10,100,10,100); // space around the exitbutton
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipady = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            frame.add(exitButton,gbc);	


            // create the sign in panel and text fields

            TextFieldEvent tfEvent = new TextFieldEvent(); // create text field event for signin and create account text fields

            JLabel label = new JLabel("Enter your account ID: "); // label for ID textfield
            gbc.insets = new Insets(10,10,10,10); // space between the text fields

            idTF.setName("id");
            idTF.addKeyListener(tfEvent);

            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            signinPanel.add(label,gbc); // add the label to the signin panel
            gbc.gridx = 1;
            signinPanel.add(idTF,gbc); // add the textField to the signin panel

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.BOTH;

            // create a label at the bottom of the panel
            gbc.gridwidth = 2; // to be centered to the other 2 grid cells
            JLabel infoLabel2 = new JLabel("Press enter to create your new account.");
            signinPanel.add(infoLabel2,gbc); // add the label to the panel

            frame.add(signinPanel,gbc); // add the signin panel to the frame


            gbc.gridwidth = 1; // set back to 1 object per grid cell

            // create new accounts label and textfield
            JLabel nameLabel = new JLabel("Enter a name: ");
            nameTF.setName("name");
            nameTF.addKeyListener(tfEvent);

            JLabel passwordLabel = new JLabel("Enter a password: ");
            passwordTF.setName("password");
            passwordTF.addKeyListener(tfEvent);

            gbc.insets = new Insets(10,10,10,10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            newAccountPanel.add(nameLabel,gbc); // add the label to the new account panel
            gbc.gridx = 1;
            newAccountPanel.add(nameTF,gbc); // add the textfield to the new account panel

            gbc.gridy = 1;

            gbc.gridx = 0;
            newAccountPanel.add(passwordLabel,gbc);
            gbc.gridx = 1;
            newAccountPanel.add(passwordTF,gbc);

            // create a label at the bottom of the panel
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            JLabel infoLabel = new JLabel("Press enter to create your new account.");
            newAccountPanel.add(infoLabel,gbc); // add the label to the panel

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipady = 0;

            frame.add(newAccountPanel,gbc); // add the panel and all components to the frame


            // main title text
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.ipady = 0;
            frame.add(titleText, gbc);

            // sub title text
            gbc.gridx = 0;
            gbc.gridy = 2;
            frame.add(subTitleText, gbc);

            // create a new panel for menu buttons
            gbc.gridy = 3;

            // create the menu buttons
            // name of button, xposition, yposition, panel that object is added too
            new MenuButton("PLAY!",0,1,menuButtons);
            new MenuButton("Play as guests (scores will not be saved).",0,2,menuButtons);
            new MenuButton("Sign In",0,3,menuButtons);
            new MenuButton("Create new account",0,4,menuButtons);
            new MenuButton("Show account details",0,5,menuButtons);
            new MenuButton("Exit",0,6,menuButtons);

            frame.add(menuButtons,gbc); // add the menuButtons panel to the frame

            showMenu();
		
	}


	// show the main menu (JPanel)
	public static void showMenu(){
		
            // get all the components from the static MenuLayout object that are used in the main menu
            JLabel titleText = layout.getTitleText();
            JLabel subTitleText = layout.getSubTitleText();
            JPanel menuButtons = layout.getMenuButtons();
            JPanel gridButtons = layout.getGridButtons();
            ExitButton exitButton = layout.getExitButton();
            JPanel signinPanel = layout.getSigninPanel();
            JPanel newAccountPanel = layout.getNewAccountPanel();


            menuButtons.setVisible(true);

            titleText.setVisible(true);
            gridButtons.setVisible(false); // hide the game button panel
            signinPanel.setVisible(false); // hide the signinAccount panel
            newAccountPanel.setVisible(false); // hide the newAccount panel
            exitButton.setVisible(false); // hide the exit button

            titleText.setText("Main Menu");
            subTitleText.setHorizontalAlignment(SwingConstants.CENTER);
            subTitleText.setText("Player 1: " + player1.getName() + "               Player 2: " + player2.getName());
		
	}
	
	
	// sign in menu (JPanel)
	public static void signin(){
		
            JLabel titleText = layout.getTitleText();
            JLabel subTitleText = layout.getSubTitleText();
            JPanel menuButtons = layout.getMenuButtons();
            ExitButton exitButton =  layout.getExitButton();
            JPanel signinPanel = layout.getSigninPanel();

            menuButtons.setVisible(false);
            exitButton.setVisible(true);
            titleText.setVisible(true);
            subTitleText.setVisible(true);
            signinPanel.setVisible(true);

            currentPlayer = new Player();

            titleText.setText("Sign In");
		
	}
	
	
	// invoked by TextFieldEvent, gets the players account (param - player ID)
	public static void signinAccount(String input){
		
            try {

                int accountNumber = Integer.parseInt(input); // convert String input to int

                currentPlayer = Accounts.findAccount(accountNumber); // find and return account to currentPlayer

                if (currentPlayer == null){ // if no account is found
                        JOptionPane.showMessageDialog(null,"Account does not exist, please try again.");
                } else {

                    // if account is already signed in
                    if ((currentPlayer.getPlayerID() == player1.getPlayerID()) || (currentPlayer.getPlayerID() == player2.getPlayerID())){

                        JOptionPane.showMessageDialog(null,"This account is already signed in, please try again.");

                    } else { // account is found

                    String password = JOptionPane.showInputDialog("Account found.\nPlease enter your password: ");

                    if (!(password == null)){ // stops nullpointerexception if exiting the input dialog

                        if (password.equals(currentPlayer.getPassword())){ // sign player in
                            JOptionPane.showMessageDialog(null,"Login Successful! \nWelcome back, " + currentPlayer.getName() +".");
                            selectPlayer();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Incorrect password, please try again.");
                        }	
                    }
                }
            }

        } catch (NumberFormatException e){
                // catches exception so no error is thrown
        } 
    }

	
	// create account menu (JPanel)
	public static void createAccount(){
		
            JLabel titleText = layout.getTitleText();
            JLabel subTitleText = layout.getSubTitleText();
            JPanel menuButtons = layout.getMenuButtons();
            ExitButton exitButton =  layout.getExitButton();
            JPanel accountPanel = layout.getNewAccountPanel();

            menuButtons.setVisible(false);
            exitButton.setVisible(true);
            titleText.setVisible(true);
            subTitleText.setVisible(true);
            accountPanel.setVisible(true);

            titleText.setText("Create New Account");

            currentPlayer = new Player();
		
	}
	
	
	public static void makeNewAccount(){
		
            // create the new account
            if (currentPlayer.getName().length() >= 3 && currentPlayer.getName().length() <= 12 && 
                    currentPlayer.getPassword().length() >= 3 && currentPlayer.getPassword().length() <= 12) {

                Accounts.addAccount(currentPlayer.getPlayerID(), currentPlayer); // add player account into accounts hashmap
                JOptionPane.showMessageDialog(null, "Account successfully created! \nYour account ID is: " + currentPlayer.getPlayerID());

                selectPlayer();

            }
            else { // if name or password isn't between 3-12 characters
                JOptionPane.showMessageDialog(null, "Name and Password must be 3 - 12 characters long.");
            }
	
	}
	
	
	
	// sign in a player to player1 or player2
	private static void selectPlayer(){
		
            String[] options = {"Player 1","Player 2"};

            int choice = JOptionPane.showOptionDialog(null,
                "What would you like to sign in as?",
                null,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[1]);

            if (choice == 0) {player1 = currentPlayer;} else { player2 = currentPlayer;}

            showMenu();
		
	}
	
	
	// set both players to guest1 and guest2
	public static void setPlayersGuest(){
            TicTacToeApplication.player1 = Accounts.findAccount(1);
            TicTacToeApplication.player2 = Accounts.findAccount(2);
	}
	
	
	public static void showAccountDetails() {
				
            String[] options = {"Player 1","Player 2"};

            int choice = JOptionPane.showOptionDialog(null,
                "Show account details for: ",
                null,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[1]);

            if (choice == 0){
                    currentPlayer = player1;
            } else {
                    currentPlayer = player2;
            }

		
            if ((currentPlayer.getPlayerID() == 1) || (currentPlayer.getPlayerID() == 2)){ // if player account is a guest account

                JOptionPane.showMessageDialog(null, "No details available for "+player1.getName()); // don't show guest account details


            } else {

                // show the current players details
                JOptionPane.showMessageDialog(null,"--------------------------\n" + currentPlayer + "\n--------------------------");

            }

	}
	

	// show the warning message for not having accounts signed in
	public static void pregameMessage(){
		
            if (player1.getPlayerID() == 1 && player2.getPlayerID() == 2){
                JOptionPane.showMessageDialog(null, "Players are currently not signed in to any account, this will not save your scores");
            }

            setupGame();
		
	}
	
	
	public static void setupGame(){
		
            JLabel titleText = layout.getTitleText();
            JLabel subTitleText = layout.getSubTitleText();
            JPanel menuButtons = layout.getMenuButtons();
            JPanel gridButtons = layout.getGridButtons();
            ExitButton exitButton =  layout.getExitButton();
            Board board = layout.getBoard();

            gridButtons.removeAll(); // remove all the old buttons
            board.makeButtons(gridButtons); // add new buttons (erase the old board and start a new blank one)

            gridButtons.setVisible(true);
            menuButtons.setVisible(false);
            exitButton.setVisible(true);
            titleText.setVisible(true);
            subTitleText.setVisible(true);
            subTitleText.setText(" "); // set the text blank until the first turn

            currentPlayer = player1; // set current player to player 1 (player 1 goes first)

            titleText.setText(player1.getName() + " (" + player1.getGameSymbol() + ")           VS          " + player2.getName()+ " (" + player2.getGameSymbol() + ")");

            setPlayers();

            playerTurn(); // start the first turn with the new board
		
	}

	
	// set the players game symbol
	public static void setPlayers(){
		
            String[] options = {"X","O"};

            int choice = JOptionPane.showOptionDialog(null,
                player1.getName() + ", what would you like to play as?",
                null,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[1]);

            if (choice == 0) {
                player1.setGameSymbol('X');
                player2.setGameSymbol('O');
            } else {
                player1.setGameSymbol('O');
                player2.setGameSymbol('X');
            }
		
	}

	
	private static void playerTurn(){
	
            JLabel subTitleText = layout.getSubTitleText();

            subTitleText.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getGameSymbol() +")");

            // wait for user to click something (calls another method through the button event)
		
	}
	

	public static void checkForWin() {
		
            Board board = layout.getBoard();

            // if player has won (down across or diagonal)
            if ((board.playerWinRow(currentPlayer.getGameSymbol()) == true) || 
            (board.playerWinColumn(currentPlayer.getGameSymbol()) == true) ||
            (board.playerWinAcross(currentPlayer.getGameSymbol()) == true)){

                if (currentPlayer == player1){ // player1 wins
                    player1.setWins(player1.getWins()+1);
                    player2.setLoses(player1.getLoses()+1);
                } else { // player2 wins
                    player1.setLoses(player1.getLoses()+1);
                    player2.setWins(player2.getWins()+1);
                }

                gameEnd(currentPlayer.getName() + " wins!"); // call gameEnd method with message string

            }

            // if game isn't already won, check if board is full
            else if (board.isBoardFull() == true){
                player1.setDraws(player1.getDraws()+1);
                player2.setDraws(player2.getDraws()+1);

                gameEnd("It's a draw.");
            }

            // if game isn't already won and board isn't full, swap the players turn
            if ((board.isBoardFull() == false) && 
            (board.playerWinRow(currentPlayer.getGameSymbol()) == false) && 
            (board.playerWinColumn(currentPlayer.getGameSymbol()) == false) &&
            (board.playerWinAcross(currentPlayer.getGameSymbol()) == false)) {

                swapTurns();

            }
	}
	
		
	// swap the current player to the other player 
	private static void swapTurns() {
	
	if (currentPlayer == player1){
            currentPlayer = player2;
	}else{
            currentPlayer = player1;
	}
	
	playerTurn(); // call the next turn for the player
            
	}
	
	
	// shows the game over message (param) then return to the menu
	private static void gameEnd(String gameEndMsg){
		
            JOptionPane.showMessageDialog(null, "GAME OVER! \n" + gameEndMsg);

            showMenu();
	}
	
	
} // end of main
