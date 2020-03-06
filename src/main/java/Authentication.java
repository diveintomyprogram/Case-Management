package src.main.java;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olivia
 */
class AuthenticationGUI extends JFrame implements ActionListener, FocusListener {
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
   
   //default constructor
   AuthenticationGUI () 
   {
   }
   
   //main class
    public static void main(String[] args) {
        //create a new frame to store text field and button
        f = new JFrame("Authentication");
        
        //create a label to display text
        l = new JLabel("nothing entered");
        
        //create a new button
        SignInBtn = new JButton("submit");
        
        // create a object of the AuthenticationGUI class
        AuthenticationGUI au = new AuthenticationGUI();
        
        //addActionLister to button
        SignInBtn.addActionListener(au);
        
        //create a object of JTextField with 16 columns and inital text
        Username = new JTextField("enter username", 16);
        
        //create a object of passwordField with 16 columns
        pass = new JPasswordField(16);
        
        //add FocusListener to passwordField
        pass.addFocusListener((FocusListener) au);
        
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
  
        f.show(); 
    } 
    // flag to set the text to blank for the first time when the component gets focus 
    boolean flag = true; 
  
    // events of focus listener 
    // when focus is gained 
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
    	
    } 
  
    // if the button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
        if (s.equals("submit")) { 

            if(Username.getText().equals("Cop123") && String.valueOf(pass.getPassword()).equals("cmsc123")) {
            // set the text of the label to the text of the field 
            l.setText("Welcome to The Police Database");
            }
            else 
                l.setText("Incorrect Username and/or Password");
                System.out.println("Username: " + Username.getText());
            	System.out.println("Password: " + String.valueOf(pass.getPassword()));
    }
}
    }