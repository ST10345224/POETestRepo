/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.kanbanboardpoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kev
 */
public class LoginTest {
           
    public LoginTest() {             
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testCheckUserName() {
        //Test valid username
        Login instance1 = new Login("kyl_1", "P@ssw0rd");
        assertTrue(instance1.checkUserName());

        //Test invalid username 
        Login instance2 = new Login("kyle!!!!!!!", "P@ssw0rd");
        assertFalse(instance2.checkUserName());
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testCheckPasswordComplexity() {
        // Test valid password
        Login instance1 = new Login("kyl_1", "Ch&&sec@ke99!");
        assertTrue(instance1.checkPasswordComplexity());

        // Test invalid password - too short
        Login instance2 = new Login("kyl_1", "password");
        assertFalse(instance2.checkPasswordComplexity());
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testRegisterUser() {
        //Test valid registration
        Login instance1 = new Login("kyl_1", "Ch&&sec@ke99!");
        String expected1 = "User successfully registered!";
        assertEquals(expected1, instance1.registerUser());

        //Test invalid username
        Login instance2 = new Login("kyle!!!!!!!", "Ch&&sec@ke99!");
        String expected2 = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        assertEquals(expected2, instance2.registerUser());

        //Test invalid password
        Login instance3 = new Login("kyl_1", "password");
        String expected3 = "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a captial letter, a number and a special character.";
        assertEquals(expected3, instance3.registerUser());
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testLoginUser() {
        //Test successful login
        Login instance1 = new Login("kyl_1", "Ch&&sec@ke99!");
        assertTrue(instance1.loginUser("kyl_1", "Ch&&sec@ke99!"));

        //Test failed login - incorrect username
        Login instance2 = new Login("kyl_1", "Ch&&sec@ke99!");
        assertFalse(instance2.loginUser("kyle!!!!!!!", "Ch&&sec@ke99!"));

        //Test failed login - incorrect password
        Login instance3 = new Login("kyl_1", "Ch&&sec@ke99!");
        assertFalse(instance3.loginUser("kyl_1", "password"));
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testReturnLoginStatus() {
        //Test successful login
        Login instance1 = new Login("kyl_1", "Ch&&sec@ke99!");
        String expected1 = "Welcome Firstname Lastname it is great to see you.";
        assertEquals(expected1, instance1.returnLoginStatus(true, "Firstname", "Lastname"));

        //Test failed login
        Login instance2 = new Login("kyl_1", "password");
        String expected2 = "Username or password incorrect, please try again.";
        assertEquals(expected2, instance2.returnLoginStatus(false, "Firstname", "Lastname"));
    }
   
}