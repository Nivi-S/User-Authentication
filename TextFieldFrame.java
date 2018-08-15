// Fig. 11.9: TextFieldFrame.java
// Demonstrating the JTextField class.
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
// import javax.swing.JFrame;
//import javax.swing.JTextField;
//import javax.swing.JPasswordField;
// import javax.swing.JOptionPane;

public class TextFieldFrame extends JFrame 
{
   private JLabel nameLabel, passwordLabel;
   private JTextField textField; // text field constructed with text

   private JPasswordField passwordField; // password field with text
   private JButton goButton;
   private GridLayout gridLayout;

   // TextFieldFrame constructor adds JTextFields to JFrame
   public TextFieldFrame()
   {
      super( "Testing JTextField and JPasswordField" );
      gridLayout = new GridLayout (3, 2);
   //   setLayout( new FlowLayout() ); // set frame layout
      setLayout (gridLayout);
     

      // construct textfield with 10 columns
 

      // construct textfield with default text
      nameLabel = new JLabel ("Name: ");
      add (nameLabel);
      textField = new JTextField( 10 );
      add( textField ); // add textField2 to JFrame

      // construct textfield with default text and 21 columns
      passwordLabel = new JLabel ("Password: " );
      add (passwordLabel);

      // construct passwordfield with default text
      passwordField = new JPasswordField( "Hidden text" );
      add( passwordField ); // add passwordField to JFrame
      
      goButton = new JButton ("Go");
      int width = 5, height = 5;
      goButton.setSize(width, height);
      
      add (goButton);
  

      ButtonHandler handler2 = new ButtonHandler();
      goButton.addActionListener (handler2);
      // register event handlers
           
      TextFieldHandler handler = new TextFieldHandler();
 
      textField.addActionListener( handler );
 
      passwordField.addActionListener( handler );
   } // end TextFieldFrame constructor

   private class ButtonHandler implements ActionListener {
 	  public void actionPerformed (ActionEvent e){
 		  String userName [] = {"Adam", "Bill", "Charlie", "David", "Ed"};
 		  String password [] = {"Alice", "Betty", "Cindy", "Diane", "Eileen"};
 		  
 		  String user; 
 		  char [] pass;
 		  int unidx = 0; // user name index
 		  
 		  user = textField.getText();
 		  pass = passwordField.getPassword();
 	//	  System.out.println ("Test field is " + textField);
 	//	  System.out.println ("User name typed as " + user);
 	//	  String s = new String (pass);
 		  
 	//	  System.out.println ("password typed as " + s);
 		  // Search for user name in the userName array
 		  boolean found = false;
 		  for (int i = 0; i < userName.length; i ++  )
 		  {
 		    if (user.compareTo(userName[i])== 0)
 		    		{
 		    			found = true;
 		    			unidx = i;
 		    			System.out.println ("Found user name in index " + i);
 		    			break;
 		    		}
 		  }
 		//  if(!found)
 		//	  System.out.println ("user name not found");
 		  if (found) // check if password matches
 		  {
 			  String s = new String (pass);
 			  String s2 = new String (password[unidx]);
 			  if (s.compareTo(s2)!= 0)
 				  found = false;  // Set foudn to false again if password not matching
 			  
 		  }
 		
 		  if (found)
 			  System.out.println ("Congratulations! You logged in");
 		  else
 			  System.out.println ("Either user name or password is not right");

 		  
 		  
 		  
 //		  System.out.println ("Button clicked");
 	    
 	  }
   }
   // private inner class for event handling
   private class TextFieldHandler implements ActionListener 
   {
      // process textfield events
      public void actionPerformed( ActionEvent event )
      {
         String string = ""; // declare string to display

         // user pressed Enter in JTextField textField1
 //        if ( event.getSource() == textField1 )
 //           string = String.format( "textField1: %s",
 //              event.getActionCommand() );

         // user pressed Enter in JTextField textField2
         if ( event.getSource() == textField )
            string = String.format( "textField: %s",
               event.getActionCommand() );

         // user pressed Enter in JTextField textField3
    
         // user pressed Enter in JTextField passwordField
         else if ( event.getSource() == passwordField )
            string = String.format( "passwordField: %s", 
               new String( passwordField.getPassword() ) );

         // display JTextField content
         JOptionPane.showMessageDialog( null, string ); 
      } // end method actionPerformed
   } // end private inner class TextFieldHandler
} // end class TextFieldFrame

/**************************************************************************
 * (C) Copyright 1992-2005 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
