/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.kanbanboardpoe;

/**
 *
 * @author Kev
 */
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class KanbanBoardPOE {

    String username, password, firstname, lastname; //global variables

    //method to handle the startup JPanel. The Panel will direct to either the RegisterPanel() or the LoginPanel()
    public void StartUp() {

        Object[] startOptions = { "Register", "Login" }; //Object[] is used to make custom button labels
        JPanel startupPanel = new JPanel(); //constructor for the JPanel
        startupPanel.add(new JLabel("What would you like to do?")); //Label to prompt the user for their choice
        
        //integer for the JOptionPane button selection to be used in the switch statement.
        int startupResult = JOptionPane.showOptionDialog(null, startupPanel, "Welcome to Kanban Board!",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, startOptions, null);
        
        //switch statement for the buttons, case 0(Register Button) executes RegisterPanel().
        /*
        case 1(Login Button) will execute the LoginPanel() but will display a temporary error message as it will not work until a registration is performed first. 
        LoginPanel() is left in as it will work if during a valid Login attempt the user selects "Cancel" to execute StartUp() and then selects "Login" again, 
        the Login will still execute properly as it will be the same Run. Temporary error message will be removed once an account database is implemented to store accounts
        between runs, either with FileWriter or DriveManager.
        */
        switch (startupResult) {
            case 0:
                RegisterPanel();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "WARNING: There is no account database implemented as yet! \nLogin may not work until a registration is done.", 
                "Account database coming soon.", JOptionPane.WARNING_MESSAGE);
                LoginPanel();
                break;
        }
    }
    //end of StartUp()

    //method to handle the Registration panel. The panel contains textfields for user input for the registration details.
    public void RegisterPanel() {

        Object[] regOptions = { "Register", "Cancel" }; //Object[] is used to make custom button labels.

        JPanel regPanel = new JPanel(); //constructor for the JPanel
        regPanel.setLayout(new GridLayout(0, 1)); //uses grid layout to make sure the correct labels are alligned with the correct textfields.
        regPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); //sets a border from the edge of the panel for components to allign to.
        regPanel.setVisible(true);
        
        //Labels for the textfields.
        JLabel fnLabel = new JLabel("First Name: ");
        JLabel lnLabel = new JLabel("Last Name: ");
        JLabel userLabel = new JLabel("Username: (5 characters or less, must contain an underscore and can only contain letters or digits)");
        JLabel passLabel = new JLabel("Password: (at least 8 characters long and contain a capital letter, a number, and a special character)");

        //Textfields for user input.
        JTextField fnField = new JTextField();
        JTextField lnField = new JTextField();
        JTextField userField = new JTextField();
        JTextField passField = new JTextField();

        //components added to the panel in the order that they must appear.
        regPanel.add(fnLabel);
        regPanel.add(fnField);
        regPanel.add(lnLabel);
        regPanel.add(lnField);
        regPanel.add(userLabel);
        regPanel.add(userField);
        regPanel.add(passLabel);
        regPanel.add(passField);

        //integer for the JOptionPane button selection to be used in the switch statement.
        int result = JOptionPane.showOptionDialog(null, regPanel, "Registration", JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, regOptions, null);

        //switch statement for the buttons. 
        switch (result) {
            case 0: //case 0(Register Button) will collect the text and compare them against methods from Login class to verfiy it's format
                //getText() used to assign the text from the textfields to the corresponding variable.
                firstname = fnField.getText();
                lastname = lnField.getText();
                username = userField.getText();
                password = passField.getText();

                Login user = new Login(username, password); //Login class constructor to create a new object of Login to use the methods from the class.
                //Login object needs to be created within the case as the username and password variables only get their strings at this point.

                //if statements to check if the username format and the password format/complexity are valid, using checkUserName() and
                //checkPasswordComplexity() to return booleans. registerUser() will return the appropriate string for a successful or failed reginstration.
                if (!user.checkUserName() || !user.checkPasswordComplexity()) { //checks if username OR password are NOT valid strings

                    JOptionPane.showMessageDialog(null, user.registerUser());

                    RegisterPanel(); //will execute the RegisterPanel() again, ensuring that this if statement will loop if registration is unsuccessful.
                }

                if (user.checkUserName() && user.checkPasswordComplexity()) { //checks if username AND password are valid strings
                    JOptionPane.showMessageDialog(null, user.registerUser());

                    LoginPanel(); //on a successful registration, LoginPanel() will be executed.
                }

                break;
            case 1://case 1(Cancel Button) will take the user back to the startup panel.
                StartUp();
                break;
        }
    }
    //end of RegisterPanel()

    //method to handle the Login Panel. Panel will allow the user to enter their login details and have it verfied.
    public void LoginPanel() {
        //temporary username and password input strings to be compared to the global username and password strings from registration.
        String loguser = "";
        String logpass = "";

        //constructor for Login Class. Object can be created at this point as global username and password have strings from registration.
        Login user = new Login(username, password); 

        Object[] loginOptions = { "Login", "Cancel" }; //Object[] is used to make custom button labels.

        //creating the Login JPanel with the grid layout for the proper component allignment.
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 1));
        loginPanel.setVisible(true);

        //a standard textfield is used for username, a JPasswordField is used for the password field to censor user input.
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JLabel prompt = new JLabel("Enter your user name and password:");

        //the components are added to the JPanel in the order that they must appear.
        loginPanel.add(prompt);
        loginPanel.add(userField);
        loginPanel.add(passField);

        //integer for the JOptionPane button selection to be used in the switch statement.
        int result = JOptionPane.showOptionDialog(null, loginPanel, "Login", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, loginOptions, null);

        //switch statement for the buttons.
        switch (result) {
            case 0: //case 0(Login Button) will collect text from the text fields into the temporary variables and compare them with the global variables.
                loguser = userField.getText();
                logpass = passField.getText(); //getPassword() is preffered for the JPasswordField but does not work with all versions of Java, getText() still works for all versions.

                //if statements to check if username and password entered matches the ones from registration.
                if (user.loginUser(loguser, logpass)) { //if loginUser() yields TRUE
                    JOptionPane.showMessageDialog(null,
                            user.returnLoginStatus(user.loginUser(loguser, logpass), firstname, lastname)); //returnLoginStatus() will return the appropriate string.
                } 
                else if (!user.loginUser(loguser, logpass)) { //if loginUser() yields FALSE
                    JOptionPane.showMessageDialog(null,
                            user.returnLoginStatus(user.loginUser(loguser, logpass), firstname, lastname)); //returnLoginStatus() will return the appropriate string.

                    LoginPanel(); //in the case of a failed login, LoginPanel() will be executed again, ensuring this process loops until a successful login or a cancel.
                }
                break;
            case 1: //case 1(Cancel Button) will execute StartUp() to bring up the start up panel.
                StartUp();
                break;
        }
    }
    //end of LoginPanel()

    //main()
    public static void main(String[] args) {

        KanbanBoardPOE instance = new KanbanBoardPOE(); //object of the class is created to use the method.

        instance.StartUp(); //executes StartUp() to create a start up panel for the user to navigate to either the registration or login.
    }
}
