/**
 * @author Fayimora Femi-Balogun
 */

import java.sql.*;
public class derbyTest
{
    public static void main(String[] args) {
    System.out.println("MySQL Connect Example.");
    Connection conn = null;
    String url = "jdbc:derby://localhost:1527/derbyTester";
    String dbName = "";
    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    String userName = "root"; 
    String password = "root";
    try
    {
      //Load the database driver
      Class.forName(driver).newInstance();
      //Get a connection to the dtabase
      conn = DriverManager.getConnection(url+dbName, userName, password);
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
      //Statement stmt = conn.createStatement();
      //int val = stmt.executeUpdate( "INSERT INTO APP.derbyTable VALUES('fayi', 20)");
      conn.close();
      
      //Disconnect from the database
      System.out.println("Disconnected from database");
    } catch (Exception e) {
        System.out.println(e);
    }
  }
}

/**
 * END END
 */
