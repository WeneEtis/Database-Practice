//Dont know
import java.sql.*;

public class localhostTester{
  public static void main(String[] args) {
    System.out.println("MySQL Connect Example.");
    Connection conn = null;
    String url = "jdbc:mysql://localhost/";
    String dbName = "fbfb3";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root"; 
    String password = "";
    try
    {
      //Load the database driver
      Class.forName(driver).newInstance();
      //Get a connection to the dtabase
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
      ResultSet rs = stmt.executeQuery( "SELECT * FROM blog_users" ) ;

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