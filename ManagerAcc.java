//Author: Omar Rabbani
package coe528.project;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class ManagerAcc extends UserCreds{
    
    // Overview: This is the immutable ManagerAcc class, which authenticates login credentials, 
    // as well as makes new and deletes pre-existing customer account files. 
    //
    // The abstraction function is:
    // AF(c) = c.username and c.password are equivalent to an abstract ManagerAcc A's A.username and A.password
    //
    // The rep invariant is:
    // Only store data if c.username = "admin" and c.password = "admin"
    //
    
    
    public ManagerAcc(){
        /**
        * EFFECTS: Creates a ManagerAcc object where username and password are both set to "admin"
         */
        username = "admin";
        password = "admin";
    }
    
    public static boolean logCreds(String username, String password){
        /**
        * REQUIRES: String username != null & String password != null
        * EFFECTS: Creates a ManagerAcc object where username and password are both set to "admin", returning true.
        * If username or password is not "admin", then return false.
        */
        if (username.equals("admin") && password.equals("admin")){
            return true;}
        return false;
    }
    public boolean addCustomer(String username, String password) throws IOException {
        /**
        * REQUIRES: String username != null & String password != null
        * MODIFIES: File "username.txt"
        * EFFECTS: Creates new username.txt file if file doesn't exist in src/Customers, writing password
        * on first line and starting balance of 100.0 on second line, returning true. If file with provided username
        * pre-exists, do not modify its contents, then return false. 
         */
        File file = new File("src/Customers/" + username + ".txt");
        if (file.exists() == false){
            file.createNewFile();
            FileWriter input = new FileWriter(file);
            input.write(password + "\n" + "100.0");
            input.close();
            System.out.println("The " + file + " was successfully made.");
            return true;
        }
        else{
            System.out.println("The file " + file + " was not added as username already exists.");
            return false;
        }
    }
    public boolean deleteCustomer(String username){
        /**
        * REQUIRES: String username != null
        * MODIFIES: File "username.txt"
        * EFFECTS: Delete file username.txt from src/Customers if it exists within directory, returning true.
        * If a file that doesn't have a stored username is used, don't delete and return false.
         */
        File file = new File("src/Customers/" + username + ".txt");
        if (file.delete()){
            System.out.println("The " + file + " was successfully deleted.");
            return true;
        }
        else{
            System.out.println("The " + file + " was not deleted as such user doesn't exist.");
            return false;
        }
    }
    public boolean repOk() {
        /**
        * EFFECTS: Returns true if the rep invariant holds for this object; otherwise returns false
         */
        if (username.equals("admin") && password.equals("admin")){ 
             return true;
         }
        else{
            return false;
        }
    }
    @Override
    public String toString() {
        /**
         * EFFECT: Return string representation of the abstract Manager object
         */
         return ("Username: " + username + ", Password: " + password);
    }
}
