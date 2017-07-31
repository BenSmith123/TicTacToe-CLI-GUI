
public class Player {

	private static int uniqueID = 1;
	
	private int playerID;
	private String name;
	private String password;
	
	private int wins;
	private int loses;
	private int draws;
	private char gameSymbol;
	
	// default constructor
	public Player(){
		this.name = "Guest1";
		this.password = "password";
		this.wins = 0;
		this.draws = 0;
		this.loses = 0;
		this.gameSymbol = 'X';
		assignID(); // assign the player a unique id that will not be repeated for any other player objects
	}
	
	// constructor with name param
	public Player(String name){
		this.name = name;
		this.password = "password";
		this.wins = 0;
		this.draws = 0;
		this.loses = 0;
		this.gameSymbol = 'O';
		assignID(); // assign the player a unique id that will not be repeated for any other player objects
		//Accounts.addAccount(this.getPlayerID(), this); // automatically add the player account into hashmap
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getPlayerID(){
		return playerID;
	}
	
	public void setPlayerID(int playerID){
		this.playerID = playerID;
	}
	
	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}
	
	public char getGameSymbol() {
		return gameSymbol;
	}

	public void setGameSymbol(char gameSymbol) {
		this.gameSymbol = gameSymbol;
	}
	
	// METHODS ///////////////////////
	
	public void assignID(){
		this.playerID = uniqueID; // set the current ID to this accounts ID
		uniqueID += 1; // then add one to the current ID
	}
	
	public static int getCurrentIDNumber(){ // get the ID number its up to
		return uniqueID;
	}
	
	public static void setCurrentIDNumber(int currentIDNumber){ // set the currentID number (used when loading from .txt)
		uniqueID = currentIDNumber;
	}
	
	public String toString(){
		return "Player ID: " + getPlayerID() +"\nName: " + getName() + "\nWins: " + getWins() + "\nTies: " + getDraws() + "\nLoses: " + getLoses();
	}

}
