//Author: Omar Rabbani
package coe528.project;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerAcc extends UserCreds{
    private double total;
    private File accDetails;
    private CustomerLvl lvlStatus;
    
    public CustomerAcc(File customerCheck) throws FileNotFoundException{
        this.accDetails = customerCheck;
        username = accDetails.getName();
        Scanner scan = new Scanner(accDetails);
        password = scan.nextLine();
        total = scan.nextDouble();
        CustomerLvl.currentLvl(this);
        scan.close();
    }
    public static boolean logCreds(String username, String password) throws FileNotFoundException{
        File file = new File("src/Customers/" + username + ".txt");
        if (file.exists() == true){
            Scanner scan = new Scanner(file);
            if (scan.nextLine().equals(password) == true){
                System.out.println("The " + file + " is correct and authenticated with username " + username + " and password" + password);
                return true;
            }
            else{
                System.out.println("The file of user " + username + " is correct but password doesn't match.");
                return false;
            }
        }
        else{
            System.out.println("The file of user " + username + " doesn't exist.");
            return false;
        }  
    }
    public void fileStatus() throws IOException{
        FileWriter input = new FileWriter(accDetails);
        input.write(password + "\n" + total);
        input.close();
    }
    public boolean depositMoney(double amount) throws IOException{
        if (amount < 0){
            System.out.println("Can't deposit negative money silly.");
            return false;
        }
        else{
            total += amount;
            lvlStatus.currentLvl(this);
            this.fileStatus();
            System.out.println("$ " + amount + " was deposited.");
            return true;
        }
    }
    public boolean withdrawMoney(double amount) throws IOException{
        if ((total - amount) < 0 || amount < 0){
            System.out.println("Can't withdraw more than you have brokey.");
            return false;
        }
        else{
            total -= amount;
            lvlStatus.currentLvl(this);
            this.fileStatus();
            System.out.println("Withdrawal success. New Balance: " + total);
            return true;
        }
    }
    //Uses state pattern to alter behaviour through internal state change
    public boolean onlinePurchase(double amount) throws IOException{
        boolean status = lvlStatus.onlinePurchase(amount, this); //Uses state method handler 
        return status;
    }
    
    public double getBalance(){
        return total;
    }
    public void setBalance(double total) throws IOException{
        this.total = total;
        lvlStatus.currentLvl(this);
        this.fileStatus(); //Update for new balance
    }
    public void setLvl(CustomerLvl lvlStatus){
        this.lvlStatus = lvlStatus;
    }
    public CustomerLvl getLvl(){
        return lvlStatus;
    }
    @Override
    public String toString() {
        return(accDetails + ", " + username + ", " + password + ", " + total + ", " + lvlStatus);
    }
}
