/*
 * Copyright (c) 2005-2010 Substance Fayimora Femi-Balogun. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


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
