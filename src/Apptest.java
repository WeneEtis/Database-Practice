/**
 *
 * @author Fayimora
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Apptest
{
    private String tableValues="";
    public static void main(String[] param)
    {
        new Apptest().run();
        
    }
    
    void run()
    {
        JFrame frame = new JFrame("THE APPLICATION");
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        JLabel nameLabel = new JLabel("Name");
        final JTextField name = new JTextField();
        
        JLabel ageLabel = new JLabel("Age");
        final JTextField age = new JTextField();
        
        JButton clear = new JButton("Clear");
        JButton ok = new JButton("Ok");
        JButton show = new JButton("Show");
        
        clear.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
               name.setText("");
               age.setText("");
           }
        });
        
        ok.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
                Connection conn = null;
                String url = "jdbc:derby://localhost/derbyTester";
                String dbName = "";
                String driver = "org.apache.derby.jdbc.EmbeddedDriver";
                String userName = "root"; 
                String password = "";
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
                  
                      // Execute the query
//                      ResultSet rs = stmt.executeQuery( "INSERT INTO derbyTable SET name="+ name.getText() + ", age="+ age.getText() ) ;
                      try{
                          Statement stmt = conn.createStatement();
                          int val = stmt.executeUpdate( "INSERT INTO APP.derbyTable VALUES('"+ name.getText() + "'," + age.getText() + ")");
                      }catch(SQLException s){
                          System.out.println(s);
                      }
                  conn.close();

                  //Disconnect from the database
                  System.out.println("Disconnected from database");
                } catch (Exception err) {
                    System.out.println(err);
                }
                       }
        });
        
        
        show.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
               Connection conn = null;
                String url = "jdbc:derby://localhost/derbyTester";
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
                      JOptionPane.showMessageDialog(null, "State  : " + warn.getSQLState()  ) ;
                      JOptionPane.showMessageDialog(null, "Message: " + warn.getMessage()   ) ;
                      JOptionPane.showMessageDialog(null,  "Error  : " + warn.getErrorCode() ) ;
                     }
                  /*****************************************************************************************/
                  
                  Statement stmt = conn.createStatement() ;
                  ResultSet rs = stmt.executeQuery( "select * from APP.DERBYTABLE" ) ;
                    
                  System.out.println( "NAME\t\t\tAGE" ) ;
                  while( rs.next() ){
                      tableValues += rs.getString(1) + "\t\t\t" + rs.getString(2) + "\n";
                      JOptionPane.showMessageDialog(null, rs.getString(1) + "\t\t\t" + rs.getString(2) + "\n");
                  }
                       
                     

                  // Close the result set, statement and the connection
                  rs.close() ;
                  stmt.close() ;
                  conn.close();
                  //Disconnect from the database
                  System.out.println("Disconnected from database");
                } catch (Exception err) {
                    System.out.println(err);
                }
                  

           }
           
        });
        
        
        JTextArea display = new JTextArea();
        display.append(tableValues);
        JComponent panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));
        
        panel.add(nameLabel);
        panel.add(name);
        panel.add(ageLabel);
        panel.add(age);
        panel.add(clear);
        panel.add(ok);
        panel.add(show);
        panel.add(display);
        
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }
}
