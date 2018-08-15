import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.regex.*;

public class registration extends JFrame
{
	// textField objects
	   private JTextField userNameField1; // text field with set size
	   private JPasswordField passwordField1; // password field with text
	   private JPasswordField passwordField2; // password field with text
	   
	// label objects
	   private JLabel userName; // JLabel with just text
	   private JLabel password1; // JLabel constructed with text
	   private JLabel password2; // JLabel with text
	   
	   private JButton enterButton; // button object
	   
	   private GridBagLayout layout; // layout of this frame
	   private GridBagConstraints constraints; // constraints of this layout
	   
	   private boolean checker = false;
	   // set up GUI
	   public registration()
	   {
	      super( "Registration Information Required" );
	      layout = new GridBagLayout();
	      setLayout( layout ); // set frame layout
	      constraints = new GridBagConstraints();

	      // Create GUI components
	      userNameField1 = new JTextField(10); // create textfield with 10 columns shown
	      passwordField1 = new JPasswordField( "12345678" ); // create 1st password input
	      passwordField2 = new JPasswordField( "12345678" ); // create 2nd password input
	      userName = new JLabel("User Name:"); // create userName label
	      password1 = new JLabel("Password:"); // create 1st password labell
	      password2 = new JLabel("Retype Password:"); // create 2nd password label
	      enterButton = new JButton("Register"); // create enterButton 
	   
	      /* Using GridBagLayout, you find that the sizes are with respect to the number of components avaliable, so for example
	       * if you wish to create a button that occupies two columns but there is only components that occupy 2 columns, then button 
	       * will only occupy two columns
	       */
	      
	      // weightx and weighty for all components is 0: the default
	      //constraints.weightx = 1;
	      //constraints.weighty = 1;
	      // anchor for all components is CENTER: the default
	      constraints.fill=GridBagConstraints.BOTH; // resizes the component in BOTH directions: when space is available
	      addComponent(userName, 0, 0, 1, 1); // add component: row 0, column 0; occupies: 1 row, 1 column
	      addComponent(userNameField1, 0, 1, 1 , 1); // add component: row 0, column 1; occupies: 1 row, 1 column
	      addComponent(password1, 2, 0, 1 , 1); // add component: row 2, column 0; occupies: 1 row, 1 column
	      addComponent(passwordField1, 2, 1, 1, 1); // add component: row 2, column 1; occupies: 1 row, 1 column
	      addComponent(password2, 4, 0 , 1, 1); // add component: row 4, column 0; occupies: 1 row, 1 column
	      addComponent(passwordField2, 4, 1 , 1, 1); // add component: row 4, column 1; occupies: 1 row, 1 column
	      addComponent(enterButton, 6, 0 , 2, 1); // add component: row 6, column 1; occupies: 1 row, 2 column
	     
	      // register event handlers
	      TextFieldHandler handler = new TextFieldHandler();
	      enterButton.addActionListener( handler);
	   } // end TextFieldFrame constructor

	   // private inner class for event handling
	   private class TextFieldHandler implements ActionListener 
	   {
	      // process textfield events
	      public void actionPerformed( ActionEvent event )
	      {
	         String stringName = "";
	         String display = "";
	         stringName = userNameField1.getText();
	         System.out.println("userNameField1: "+ stringName);
	         
	         if(stringName.length() >= 5 && stringName.length() <= 8 && alphabeticalMatching(stringName) != true)
	         {
	        	 char [] passwordIn = passwordField1.getPassword();
	        	 char [] passwordCheck = passwordField2.getPassword();
        		 String stringPasswordIn = new String(passwordIn);
        		 String stringPasswordCheck = new String(passwordCheck);
        		 System.out.println("passwordField1: "+ stringPasswordIn);
        		 System.out.println("passwordField2: "+ stringPasswordCheck);
        		 
	        	 if(passwordIn.length >= 5 && passwordIn.length <= 8 && alphanumericallMatching(stringPasswordIn) != true && stringPasswordCheck.contentEquals(stringPasswordIn))
	        	 {
	        		 writeMyFile(stringName);
	        		 if(checker != true)
	        		 {
	        			 display = "Success. Username and password have been saved. Thank you!";
			        	 JOptionPane.showMessageDialog( null, display );	
	        		 }
	        			
	        		 else
	        		 {
	        			 display = "Error!!! Username is not unique.";
			        	 JOptionPane.showMessageDialog( null, display );
			        	 checker=false;
	        		 }
	        	 }
	        	 
	        	 else
	        	 {
	        		 display = "Error!!! Please check that your password is between 5 to 8 characters, contains only alphabnumerical characters, and matches in both fields.";
		        	 JOptionPane.showMessageDialog( null, display );	 
	        	 }
	         }
	         
	         else
	         {
	        	 display = "Error!!! Please check that your username is between 5 to 8 characters and contains only alphabetic characters.";
	        	 JOptionPane.showMessageDialog( null, display );
	         }
	        
	      } // end method actionPerformed
	   } // end private inner class TextFieldHandler
	   
	   private void addComponent( Component component, int row, int column, int width, int height)
	   {
		   constraints.gridx = column; // set gridx
		   constraints.gridy = row; // set gridy
		   constraints.gridwidth = width; // set gridwidth
		   constraints.gridheight = height; // set gridheight
		   layout.setConstraints(component, constraints); // set constraints
		   add (component);
	   } // end method addComponent
	
	   private boolean alphabeticalMatching(String stringIn)
	   {
		   Pattern p = Pattern.compile("[0-9[^\\w]]");
      	   return  p.matcher(stringIn).find();   
	   }
	   
	   private boolean alphanumericallMatching(String stringIn)
	   {
		   Pattern p = Pattern.compile("[^\\w]");
      	   return  p.matcher(stringIn).find();   
	   }
	   
	   void writeMyFile(String check) 
		{ 	        
	        String record1 = null;
	        int recCount = 0, i=0; 
	        try { 
	        
	        	// The following method writes the input to buffer then outputs to buffer
	           FileReader fr     = new FileReader("username.txt"); // read from text
	           BufferedReader br = new BufferedReader(fr);
	      
		       FileWriter fw     = new FileWriter("username.txt"); // write to text
	           BufferedWriter bw = new BufferedWriter(fw);

	           record1 = new String();
	           
	           while(( record1=br.readLine())!= null) // checks if string inputted already exists
	           {
	        	 if(record1.contentEquals(check))
	        	 {
	        		 checker = true; 
	        		 break;
	        	 }
	        	 else
	        		 recCount++;
	           }
	           
	           while(( record1=br.readLine())!= null && checker != true) // if the break was not performed and checker is true
	           {
	        	 if(i < recCount)
	        	 {
	        		 i++;
	        		 bw.write(record1);
	                 bw.newLine();
	        	 }
	        	 else
	        	 {
	        		 bw.write(check);
	                 bw.newLine();
	        	 }
	        		 
	           }
	            
	           bw.close(); // closes writebuffer
	           br.close(); // closes readbuffer
	   
	           //System.out.println ("File closed"); // writes to console
	           
	        } // end of try 
	        catch (IOException e) 
	        { 
	           // catch possible io errors from readLine()
	           System.out.println("Uh oh, got an IOException error!");
	           e.printStackTrace(); // prints from error directory
	           
	        } // end of catch

	     } // end of readMyFile()
	   
	} // end class TextFieldFrame
