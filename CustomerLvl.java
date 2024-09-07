//Author: Omar Rabbani
package coe528.project;

import java.io.IOException;

public abstract class CustomerLvl {
    
    public static void currentLvl(CustomerAcc customer){
        if (customer.getBalance() < 10000){
            customer.setLvl(new Silver());
            System.out.println("Your level is silver");
        }
        else if (customer.getBalance() >= 10000 && customer.getBalance() < 20000){
            customer.setLvl(new Gold());
            System.out.println("Your level is gold");
        }
        else{
            customer.setLvl(new Platinum());
            System.out.println("Your level is platinum");
        }
    }
    protected abstract boolean onlinePurchase(double amount, CustomerAcc customer) throws IOException;
    
    @Override
    public abstract String toString();
}
