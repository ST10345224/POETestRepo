/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kanbanboardpoe;

/**
 *
 * @author Kev
 */

/*
REFERENCES:
Author: w3schools
Title: Java Regular Expressions 
Website: https://www.w3schools.com/java/java_regex.asp
Date of Access: 28 April 2023
*/
import java.util.regex.*;

public class Login {

    private String username;
    private String password;

    //constructor requiring 2 strings for username and password. 
    //equates the strings the private strings in the class.
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //checkUserName() to verify that the username string input from registration complies with the formatting requirements and return true/false
    //username is checked for length first to short circuit the process, followed by characters and then underscore.
    public boolean checkUserName() {
        String strPattern = "^[a-zA-Z0-9_]+$";
        if (username.length() > 5) {
            return false;
        }
        if (!Pattern.matches(strPattern, username)) {
            return false;
        }
        if (!username.contains("_")) {
            return false;
        } else {
            return true;
        }
    }

    //checkPasswordComplexity() for the same purpose as checkUserName() but for passwords
    //length is checked first, then for uppercase characters, then for digits, then for special characters.
    public boolean checkPasswordComplexity() {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()-_=+].*")) {
            return false;
        } else {
            return true;
        }
    }

    //registerUser() will return a string to let the user know if the username or password format is not viable, or if registration was successful.
    //it will return an error string if either checkUserName() or checkPasswordComplexity() are false.
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a captial letter, a number and a special character.";
        }
        return "User successfully registered!";
    }

    //loginUser() will use the username and password string inputs to compare against the username and password inputs held from a successful registration
    //to return a true/false
    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    //returnLoginStatus() will use the boolean from loginUser() to return a string for if the login was successful or not.
    //the firstname and lastname strings are used for the welcome message on a successful login.
    public String returnLoginStatus(boolean loginUser, String firstname, String lastname) {
        if (loginUser) {
            return "Welcome " + firstname + " " + lastname + " it is great to see you.";
        }
        return "Username or password incorrect, please try again.";
    }
}
