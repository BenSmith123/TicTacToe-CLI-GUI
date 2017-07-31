import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class main {

	private static Scanner keyboard = new Scanner(System.in);
	private static Player player1 = new Player(); // create player 1 & 2 as guests
	private static Player player2 = new Player("Guest2"); // set the same of player 2 to guest
	private static Player currentPlayer; // current player swaps between player 1 and 2 so they both can be used by methods
	private static int input = 0;
	
	public static void main(String args[]){
		
		System.out.println("Welcome to Tik-Tac-Toe!\n");
		
		Accounts.addAccount(player1.getPlayerID(), player1); // add the guest accounts into the accounts hashmap
		Accounts.addAccount(player2.getPlayerID(), player2);

		int currentID = Accounts.loadCurrentUniqueID(); // load the current ID (when creating accounts IDs are not overridden or duplicated)
		Player.setCurrentIDNumber(currentID); // set the current ID (when new player is made it will use the latest ID and update it)
		
		//Accounts.saveAccounts(); // debug
		//Accounts.printPlayerAccounts(); // debug
		
		Accounts.loadAccounts(); // load the accounts from .txt file (if any)

		showMenu();
		
	}

	// print the current players logged in
	private static void printPlayers(){
		System.out.println("Player 1: " + player1.getName());
		System.out.println("Player 2: " + player2.getName() + "\n");
	}
	
	private static void showMenu() {
		
		printPlayers(); // show players logged in
		
		System.out.println("1) PLAY!");
		System.out.println("2) Play as guests (scores will not be saved)");
		System.out.println("3) Sign In");
		System.out.println("4) Create new account");
		System.out.println("5) Show account details");
		System.out.println("6) Exit");
		
		int input = checkIntInput(); // calls method to check input is valid before returning it (try, catch or throw exception)
		
		if (input < 1 || input > 6){
			System.out.println("This number is not an option, please try again.");
	        showMenu();
		}
		
		if (input == 1){
			
			// if player 1 or 2 is guest account, show message
			if (player1.getPlayerID() == 1 && player2.getPlayerID() == 2){
				System.out.println("\nPlayers are currently not signed in to any account, this will not save your scores\n");
			}
			currentPlayer = player1; // set current player to player 1 (player 1 goes first)
			setupGame();
		}
		
		if (input == 2) {
			player1 = Accounts.findAccount(1); // get guest account 1
			player2 = Accounts.findAccount(2); // get guest account 2

			currentPlayer = player1; // player 1 goes first
			setupGame();
		}
		
		if (input == 3){
			signin();
		}
		
		if (input == 4){
			createAccount();
		}
		
		if (input == 5){
			
			System.out.println("--------------------------");
			
			if ((player1.getPlayerID() == 1) || (player1.getPlayerID() == 2)){ // if player 1 account is a guest account
				System.out.println("No details available for "+player1.getName());
			} else {
				System.out.println(player1); // otherwise print the logged in players details
			}
			
			System.out.println("--------------------------");
			
			if ((player2.getPlayerID() == 1) || (player2.getPlayerID() == 2)){
				System.out.println("No details available for "+player2.getName());
			} else {
				System.out.println(player2);
			}
			
			System.out.println("--------------------------\n");
			showMenu();
			
		}
		
		if (input == 6){
			Accounts.saveAccounts(); // save the accounts hashmap in accounts.txt
			System.out.println("Your account information has been saved.\nThank you for playing!");
			System.exit(0); // force exit
		}
		
	}

	private static void selectPlayer(){
		
		System.out.println(currentPlayer.getName() + ", are you player 1 or player 2? (1 or 2)"); // player chooses if player 1 or 2
		
		int input = checkIntInput();
		
		// set the player to p1 or p2
		if (input == 1){player1 = currentPlayer;}
		else if (input == 2){player2 = currentPlayer;}
		else{ // repeat if invalid input
			System.out.println("Invalid input, please try again.");
			selectPlayer();
		}
		
		System.out.println("Successfully signed in!");
		System.out.println("--------------------------\n");
		showMenu(); // show menu after signing in
		
	}

	private static void signin(){
		System.out.println("(enter 0 to go back)");
		System.out.println("Enter your player ID: ");
		
		int input = checkIntInput();
		
		if (input == 0){ // go back to the menu
			showMenu();
		} 
		else {
		
			currentPlayer = Accounts.findAccount(input); // find and return account to currentPlayer
			
			if (currentPlayer == null){
				System.out.println("Account does not exist, please try again.");
				signin();
			}
			
			else{ // if account has been found
				
				if (input == 1 || input == 2){ // guest accounts
					System.out.println("This is a guest account, please try again.");
					signin();
				}
				
				// already signed in
				if ((currentPlayer.getPlayerID() == player1.getPlayerID()) || (currentPlayer.getPlayerID() == player2.getPlayerID())){
					System.out.println("This account is already signed in, please try again.");
					signin();
				}
				
				// if account is found
				System.out.println("Account found.\nPlease enter your password: ");
				
				String passwordInput = keyboard.next();
				
				if (passwordInput.equals(currentPlayer.getPassword())){
					System.out.println("Login Successful!");
					System.out.println("Welcome back, " + currentPlayer.getName() +".");
					selectPlayer();
				}
				else{
					System.out.println("Password incorrect, please try again.");
					signin();
				}
			}
		}
	}


	private static void createAccount(){
	
		System.out.println("(enter 0 to go back)");
		System.out.println("Enter a name: ");
		
		Player aPlayer = new Player(); // create a temp player obj
		
		String currentString = keyboard.next();
		
		// back to main menu
		if (currentString.equals("0")){
			showMenu();
		} else {
			
			 // create a new account
			aPlayer.setName(currentString);
	
			System.out.println("Enter a password: ");
			currentString = keyboard.next();
			aPlayer.setPassword(currentString);
			
			Accounts.addAccount(aPlayer.getPlayerID(), aPlayer); // add player account into accounts hashmap
			
			currentPlayer = aPlayer; // set the currentPlayer to the temporary player obj
			System.out.println("Your account has successfully been created! \n");
			System.out.println("Your account ID is: " + currentPlayer.getPlayerID());
			selectPlayer();
		}
	}

	private static void setupGame() {
			
		System.out.println(player1.getName() + ", what would you like to play as? (O or X)\n(Enter 0 to go back)");
		
		String input = keyboard.next();
		char gameSymbol;
		
		// go to menu
		if (input.equals("0")){
			showMenu();
		} else {
		
			if (input.equalsIgnoreCase("O") || (input.equalsIgnoreCase("X"))){
				
				gameSymbol = input.toUpperCase().charAt(0); // convert input to uppercase char
				player1.setGameSymbol(gameSymbol); // set the players gameSymbol to the input
				
				if (player1.getGameSymbol() == 'X'){
					
					player2.setGameSymbol('O');
				}
				else{
					player2.setGameSymbol('X');}	
				
					System.out.println("--------------------------------------");
					System.out.println("GAME STARTED!");
					System.out.println("Enter 0 to exit game at any time.");
					System.out.println(player1.getName() + " (" + player1.getGameSymbol() + ")  VS  " + player2.getName()+ " (" + player2.getGameSymbol() + ")");
					System.out.println("--------------------------------------");
					
					Board board = new Board(); // create a new empty board
					board.drawBoard(); // show the board
					playerTurn(board); // start the first turn with the new board
				}
		
			else { // if input not == X or O
				System.out.println("Invalid input, please try again.");
				setupGame();
			}
		 
		}
	
	}
	
	private static void playerTurn(Board board){
	
	System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getGameSymbol() +")\n");
	
	System.out.print("Select Row: (1,2,3) ");
	int selectRow = checkIntInput();
	
	if (selectRow == 0){
		System.out.println("--- GAME QUIT ---\n");
		showMenu();
	} else { // if player hasnt quit to menu
	
		if (selectRow < 1 || selectRow > 3){ // if invalid input
			System.out.println("Invalid input, please try again.");
			playerTurn(board);
		}
		else{ // if input is valid
				System.out.print("Select Column: (A,B,C) ");
				char selectColumn = keyboard.next().charAt(0);
				
				if (selectColumn == '0'){
					System.out.println("--- GAME QUIT ---\n");
					showMenu();
					
				} else { // if player hasnt quit to menu
				
					// if select column is upper or lowercase a,b,c
					if ((selectColumn >= 65) && (selectColumn <= 67) || (selectColumn >= 97) && (selectColumn <= 99)){
						
						// mark the place the user has input
						boolean markedPlace = board.markPlace(selectRow, selectColumn, currentPlayer.getGameSymbol());
						
						// if the place is already taken, repeat
						if (markedPlace == false){
							System.out.println("This place is already been taken.");
							playerTurn(board);
						}
						else{ // if place not taken
							board.drawBoard();
						}
						
					}
					else{ // if input isnt a,b,c
						System.out.println("Invalid input, please try again.\n");
						playerTurn(board);
					}
					
					checkForWin(board); // check if the player has won
					
				}
			}
		}
	}
	
	
	private static void checkForWin(Board board){
		
		// if currentPlayer has won
		if (board.playerWinRow(currentPlayer.getGameSymbol()) == true){
			updateScores();
			gameEnd(currentPlayer.getName() + " wins!");
		}
		
		if (board.playerWinColumn(currentPlayer.getGameSymbol()) == true){
			updateScores();
			gameEnd(currentPlayer.getName() + " wins!");
		}
		
		if (board.playerWinAcross(currentPlayer.getGameSymbol()) == true){
			updateScores();
			gameEnd(currentPlayer.getName() + " wins!");
		}
		
		// if player hasnt already won, check if board is full
		if (board.isBoardFull() == true){
			// if board is full = draw
			player1.setDraws(player1.getDraws()+1);
			player2.setDraws(player2.getDraws()+1);
			
			gameEnd("It's a draw.");
		}
		
		// if player hasnt won and board = false, continue game
		if ((board.playerWinRow(currentPlayer.getGameSymbol()) == false) && (board.playerWinColumn(currentPlayer.getGameSymbol()) == false)
		&&	(board.playerWinAcross(currentPlayer.getGameSymbol()) == false) && (board.isBoardFull() == false)){

			// swap the current player to the other player 
			if (currentPlayer == player1){
				currentPlayer = player2;
			}else{
				currentPlayer = player1;
			}
			
			playerTurn(board); // call next turn
		}

	}
	
	
	private static void updateScores(){
		
		if (currentPlayer == player1){ // player1 wins
			player1.setWins(player1.getWins()+1);
			player2.setLoses(player1.getLoses()+1);
		} else { // player2 wins
			player1.setLoses(player1.getLoses()+1);
			player2.setWins(player2.getWins()+1);
		}
		
	}
	
	
	
	private static void gameEnd(String gameEndString){
	
		System.out.println("--------------------------------------");
		System.out.println("GAME END!");
		System.out.println(gameEndString);
		System.out.println("--------------------------------------");
		
		showMenu();
	}

	// take an input and check if its valid, if not repeat this method
	private static int checkIntInput(){
		
		// int input = 0; // have to have static scanner otherwise input is reset for every time this is called
		
		try{
			input = keyboard.nextInt();
			
		} catch (InputMismatchException e) {
	    	System.out.println("A number was not entered, please try again.");
	        keyboard.next(); // clear the scanner
	        checkIntInput();
	        
	    } catch (NoSuchElementException e) {
		    System.out.println("No input received: " + e);
		    keyboard.next();
		    checkIntInput();
		    
		} catch (IllegalStateException e) {
		    System.out.println("Scanner is closed " + e);
		    keyboard.next();
		    checkIntInput();
		}
		
		return input;
	}

} 
