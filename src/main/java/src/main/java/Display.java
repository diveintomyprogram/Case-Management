package src.main.java;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Display extends JFrame implements ActionListener, FocusListener {
	 /**
	 * Display class is the main container window of the UI. 
	 */
	private static final long serialVersionUID = 1L;
	
	//JTextField
    static JTextField Username;
       //JPAsswordfield
    static JPasswordField pass;
    //JFrame
    static JFrame f;
    //create buttons
    static JButton SignInBtn;
       //label to display text
    static JLabel l;
    static src.main.java.Database db;
    
    Display() {
    	//Stub Constructor
    }
 // flag to set the text to blank for the first time when the component gets focus 
    boolean flag = true; 

	public static void main(String[] args) {
		//create a new frame to store text field and button
        f = new JFrame("Authentication");
        //create a label to display text
        l = new JLabel("nothing entered");
        //create a new button
        SignInBtn = new JButton("submit");
		
        Display display = new Display();
        
        SignInBtn.addActionListener(display);
               
        //create a object of JTextField with 16 columns and inital text
        Username = new JTextField("enter username", 16);
        
        //create a object of passwordField with 16 columns
        pass = new JPasswordField(16);
        
        //add FocusListener to passwordField
        pass.addFocusListener((FocusListener) display);
        
        // set the echo character of the password field
        pass.setEchoChar((char)0);
  // set initial text for password field 
        pass.setText("enter password"); 
  
        // set the echo character of the password field 
        // create an object of font type 
        Font fo = new Font("Serif", Font.ITALIC, 20); 
  
        // set the font of the textfield 
        Username.setFont(fo); 
  
        // create a panel to add buttons and textfield 
        JPanel p = new JPanel(); 
  
        // add buttons and textfield to panel 
        p.add(Username); 
        p.add(pass); 
        p.add(SignInBtn); 
        p.add(l); 
  
        // add panel to frame 
        f.add(p); 
  
        // set the size of frame 
        f.setSize(300, 300); 
        
		String dbHost = System.getenv("DATABASE_HOST");
		if (dbHost == null) {
			// Set default value
			dbHost = "192.168.99.100";
		}
		db = new src.main.java.Database(dbHost, "case", "CMSC495", "SuperSecret");
		db.seed();
		Inmate new_inmate = new Inmate(456, "Steve", "Rogers", "03/21/76", 73, 235, "blue", "blonde", 0, "src/main/resources/8375.jpg");
		System.out.println(db.createInmate(new_inmate));
		ArrayList<Inmate> inmates = new ArrayList<Inmate>();
		inmates.addAll(db.searchInmates("john"));
		inmates.addAll(db.searchInmates(456));
		
		for (Inmate inmate : inmates) {
			System.out.println(inmate);
		}
		
		f.setVisible(true);
	}
	public void focusGained(FocusEvent e) 
    { 
        if (flag) { 
            // set a definite echo char 
            pass.setEchoChar('*'); 
  
            // only set the text to blank for 1st time 
            // set the text to blank 
            pass.setText(""); 
            flag = false; 
        }
    } 
  
    // when the focus is lost 
    public void focusLost(FocusEvent e) 
    { 
    	flag = true;
    } 
  
    // if the button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
        if (s.equals("submit")) { 

        	if(db.adminLogin(Username.getText(), String.valueOf(pass.getPassword()))) {
        		l.setText("Welcome to The Police Database");
        		System.out.println("Username: " + Username.getText() + " Logged in");
        	}
            else 
                l.setText("Incorrect Username and/or Password");
        }
    }
}
