//Author: Omar Rabbani
package coe528.project;

import java.io.IOException;

public class Silver extends CustomerLvl{
    @Override
    public boolean onlinePurchase(double amount, CustomerAcc customer) throws IOException{
        if (customer.getBalance() >= (amount + 20) && amount >= 50){
            customer.setBalance(customer.getBalance() - amount - 20);
            System.out.println("Silver customer purchase made. New Balance: " + customer.getBalance());
            currentLvl(customer);
            return true;
        }
        else{
            System.out.println("Silver customer purchase unsuccessful.");
            return false;
        }
    }
    
    @Override
    public String toString() {
        return ("Silver");
    }
}
