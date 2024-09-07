//Author: Omar Rabbani
package coe528.project;

import java.io.IOException;

public class Platinum extends CustomerLvl{
    @Override
    public boolean onlinePurchase(double amount, CustomerAcc customer) throws IOException{
        if (customer.getBalance() >= amount && amount >= 50){
            customer.setBalance(customer.getBalance() - amount);
            System.out.println("Platinum customer purchase made. New Balance: " + customer.getBalance());
            currentLvl(customer);
            return true;
        }
        else{
            System.out.println("Platinum customer purchase unsuccessful.");
            return false;
        }
    }
    
    @Override
    public String toString() {
        return ("Platinum");
    }
}
