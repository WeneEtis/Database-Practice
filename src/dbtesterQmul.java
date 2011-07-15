/**
 * @author Fayimora Femi-Balogun
 */
import java.sql.*;

public class dbtesterQmul{

    public static void main(String[] args) {
    System.out.println("MySQL Connect Example.");
    Connection conn = null;
<<<<<<< HEAD
    String url = "jdbc:mysql://fayimoracom2.ipagemysql.com/";
    String dbName = "mywebsite1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "fayimoracom2"; 
    String password = "fayimora100%";
    
    
    
=======
    String url = "jdbc:mysql:// host goes here/";
    String dbName = "";
    String driver = "com.mysql.jdbc.Driver";
    String userName = ""; 
    String password = "";
>>>>>>> a37de026338ec760c8f2fad54c1474c9a7db2a98
    try
    {
      //Load the database driver
      Class.forName(driver).newInstance();
      //Get a connection to the database
      System.out.println("bout to connect");
      conn = DriverManager.getConnection(url+dbName,userName,password);
      System.out.println("Connected to the database");
     /*****************************************************************************************/ 
      // Print all warnings
      for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
         {
          System.out.println( "SQL Warning:" ) ;
          System.out.println( "State  : " + warn.getSQLState()  ) ;
          System.out.println( "Message: " + warn.getMessage()   ) ;
          System.out.println( "Error  : " + warn.getErrorCode() ) ;
         }
      /*****************************************************************************************/
      
      // Get a statement from the connection
      Statement stmt = conn.createStatement() ;

      // Execute the query
      ResultSet rs = stmt.executeQuery( "SELECT * FROM blog_posts ;" ) ;

      // Loop through the result set
      while( rs.next() )
         System.out.println( rs.getString(2) ) ;

      // Close the result set, statement and the connection
      rs.close() ;
      stmt.close() ;
      conn.close() ;
      
      //Disconnect from the database
      System.out.println("Disconnected from database");
    } catch (Exception e) {
        System.out.println(e);
    }
  }
}