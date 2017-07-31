import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public abstract class Accounts {
	
    // store the player accounts in a hashmap
    // key = player uniqueID
    // value = player object
    static HashMap<Integer, Player> playerAccounts = new HashMap <Integer, Player>();
    
    // username and password for the database
    static final String USERNAME = "username";
    static final String PASSWORD = "password";

    // add a new account into the hashmap
    public static void addAccount(int uniqueID, Player aPlayer){
        playerAccounts.put(uniqueID, aPlayer);
    }

    // finds a player account ID that matches the param input to the hashmap Key
    public static Player findAccount(int input) {

        Set<Entry<Integer, Player>> set = playerAccounts.entrySet(); // get the set of player accounts
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
        
    
    public static void loadDatabase() {

    try {
        
        // connect to the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/TictactoeDB;",USERNAME,PASSWORD);

        // query the database
        Statement statement = conn.createStatement(); // create statement object with connection to database
        String sql = "SELECT ACCOUNTID, PLAYERNAME, PASSWORD, WINS, LOSSES, DRAWS FROM ACCOUNTS"; // get all database attributes
        ResultSet rs = statement.executeQuery(sql); // create a results set to query database and get results
        
        // get data from result set
        while(rs.next()){

            Player player = new Player(); // create a new player for each account in the database

            // get the account attributes
            player.setPlayerID(rs.getInt("ACCOUNTID"));
            player.setName(rs.getString("PLAYERNAME"));
            player.setPassword(rs.getString("PASSWORD"));
            player.setWins(rs.getInt("WINS"));
            player.setWins(rs.getInt("LOSSES"));
            player.setWins(rs.getInt("DRAWS"));

            addAccount(player.getPlayerID(),player); // add that account to the hashmap to be used at runtime
            System.out.print(player);
        }
         
        rs.close(); // close the result set
        statement.close(); // close the statement
        conn.close(); // close the connection
         
      }catch(SQLException se){ // handle errors for JDBC
         databaseError();
      }catch(Exception e){ //Handle errors for Class.forName
         databaseError();
      }
         
   }

    
    
    // add the hashmap of accounts into the database
    public static void updateDatabase(){
        
        try {
            // connect to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/TictactoeDB",USERNAME,PASSWORD);
            
            // create statement object with connection to database
            Statement statement = conn.createStatement();
            
            // overide the current ACCOUNTS table with new accounts
            statement.executeUpdate("DROP TABLE ACCOUNTS"); // drop the current table
            
            // create the new table
            String sqlCreateTable="CREATE TABLE ACCOUNTS (ACCOUNTID INT, PLAYERNAME VARCHAR(12), PASSWORD VARCHAR(12), WINS INT, LOSSES INT, DRAWS INT)";

            statement.executeUpdate(sqlCreateTable); // create new table
            
            // iterate through the hashmap and add each object (and attributes to the database)
            Set<Entry<Integer, Player>> set = playerAccounts.entrySet(); // get the set of player accounts
            Iterator i = set.iterator(); // create Iterator

            while(i.hasNext()) {
               Map.Entry me = (Map.Entry)i.next();

               Player player = ((Player) me.getValue()); // player object for each object in the hashmap
               
               if (player.getPlayerID() > 2){ // if account is not guest account
                    // add each player and their attributes into the database
                    statement.executeUpdate("INSERT INTO ACCOUNTS (ACCOUNTID, PLAYERNAME, PASSWORD, WINS, LOSSES, DRAWS) VALUES ("
                    +player.getPlayerID()+",'"+player.getName()+"','"+player.getPassword()+"',"+player.getWins()+","+player.getLoses()+","+player.getDraws()+")");

                System.out.println(player);
               }

            }
            
        statement.close(); // close the statement
        conn.close(); // close the connection
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            databaseError();
        }
    }
    
    // if any exception is thrown because of database, notify user
    private static void databaseError(){
        JOptionPane.showMessageDialog(null,"An error has occurred with the database.");
    }


}
