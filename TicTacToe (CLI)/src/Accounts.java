import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public abstract class Accounts {
	
	// store the player accounts in a hashmap
	// key = player uniqueID
	// value = player object
	static HashMap<Integer, Player> playerAccounts = new HashMap <Integer, Player>();

	// add a new account into the hashmap
	public static void addAccount(int uniqueID, Player aPlayer){
		playerAccounts.put(uniqueID, aPlayer);
	}

	// finds a player account ID that matches the param input to the hashmap Key
	public static Player findAccount(int input) {
			
		Set<Entry<Integer, Player>> set = playerAccounts.entrySet();  // get the set of player accounts
	    Iterator i = set.iterator(); // create Iterator
	    
	    while(i.hasNext()) {
	       Map.Entry me = (Map.Entry)i.next();
	       
	       // get the value of the current (Player) Object 
	       // get the player objects ID and compare it to the input
	       if ((((Player) me.getValue()).getPlayerID() == input)){
	    	   return (Player) me.getValue(); // if input matches the current playerID then return the player object
	       }

		}
		    
		return null; // if no accountID matches the input, return null
	}
	
	
	// USED ONLY FOR DEBUGGING
	// prints out the entire hashmap of player accounts
	public static void printPlayerAccounts(){
		
		Set<Entry<Integer, Player>> set = playerAccounts.entrySet();
	    
	    Iterator i = set.iterator(); // get an iterator
	    
	    while(i.hasNext()) { // display elements
	       Map.Entry me = (Map.Entry)i.next();
	       System.out.println();
	       System.out.println(me.getValue());
	      
	    }

	}

	
	public static void saveAccounts() {
		
		FileOutputStream fos;
		try {
			
			fos = new FileOutputStream("accounts.txt");
		
			PrintWriter outputStream = new PrintWriter(fos);
	
			Set<Entry<Integer, Player>> set = playerAccounts.entrySet();
		    
		    Iterator i = set.iterator(); // get an iterator
		    
		    outputStream.println("UNIQUE ID" + Player.getCurrentIDNumber());
	    
		    while(i.hasNext()) {
		       Map.Entry me = (Map.Entry)i.next();

		       Player aPlayer = (Player) me.getValue();
		       
		       if (aPlayer.getPlayerID() > 2){
		    	   
		    	   outputStream.println("NEW ACCOUNT");
			       outputStream.println("1" + aPlayer.getPlayerID());
			       outputStream.println("2" + aPlayer.getName());
			       outputStream.println("3" + aPlayer.getPassword());
			       outputStream.println("4" + aPlayer.getWins());
			       outputStream.println("5" + aPlayer.getDraws());
			       outputStream.println("6" + aPlayer.getLoses());
			       
			       outputStream.println(); 
		       }
		    }
			
			outputStream.close();
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("File not found, cannot load existing accounts.");
			}	
			
	}
	

	public static int loadCurrentUniqueID(){
		
		try 
		{
			FileReader fr=new FileReader("accounts.txt");
	        BufferedReader inputStream = new BufferedReader(fr);
	        String line = null;
	        
	        while((line=inputStream.readLine())!=null) {
	        	
	        	if (line.contains("UNIQUE ID")){
	        		line = line.replaceAll("\\D+",""); // replace all characters from the string to return the id number
	        		return Integer.parseInt(line);
	        	}
	        	
	        }
		}
		
	    catch(FileNotFoundException e1){
	    	System.out.println("File not found, cannot load existing accounts.");
	    }catch(IOException e){
	       System.out.println("Error reading from file accounts.txt");
	    }  
		
		return 4;
	}
	
	
	public static void loadAccounts(){
		
		Player aPlayer = new Player();
		
		try
	    {
	        FileReader fr=new FileReader("accounts.txt");
	        BufferedReader inputStream = new BufferedReader(fr);
	        String line = null;
	        
	        boolean createPlayer = false;
	        
	        while((line=inputStream.readLine())!=null) {

	        	if (line.equals("NEW ACCOUNT")){
	        		createPlayer = true;
	        	}
	        	
	        	if (createPlayer = true) {
	        		
	        		if (!(line.isEmpty())) {
	        			
	        			if (aPlayer.getPlayerID() > 2){ // dont load the guest accounts
	        				
							if (line.charAt(0) == '1') {aPlayer.setPlayerID(Integer.parseInt(line.substring(1)));}
							if (line.charAt(0) == '2') {aPlayer.setName(line.substring(1));}
							if (line.charAt(0) == '3') {aPlayer.setPassword(line.substring(1));}
							if (line.charAt(0) == '4') {aPlayer.setWins(Integer.parseInt(line.substring(1)));}
							if (line.charAt(0) == '5') {aPlayer.setDraws(Integer.parseInt(line.substring(1)));}
							if (line.charAt(0) == '6') {aPlayer.setLoses(Integer.parseInt(line.substring(1)));}
	
							Accounts.addAccount(aPlayer.getPlayerID(), aPlayer);
							createPlayer = false;
						}	
					}
	        	}
	        }
	        		
	        inputStream.close();         
	    }
		
	    catch(FileNotFoundException e1){
	    	System.out.println("File not found, cannot load existing accounts.");
	    }
		
	    catch(IOException e){
	       System.out.println("Error reading from file accounts.txt");
	    }  
	
	}

	
	
	
}
