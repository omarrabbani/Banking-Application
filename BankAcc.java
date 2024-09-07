//Author: Omar Rabbani
package coe528.project;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class BankAcc extends Application{
    private Scene firstScene;
    private ManagerAcc manager = new ManagerAcc();
    
    @Override
    public void start(Stage window) throws Exception{
        Label username = new Label("Enter Username: ");
        TextField enterUsername = new TextField();
        Label password = new Label("Enter Password: ");
        TextField enterPassword = new TextField();
        Button login = new Button("Login");
        Button clearButton = new Button("Clear");
        Label failedLogin = new Label("");
        
        login.setOnAction(e ->
        {
            try{
                if (ManagerAcc.logCreds(enterUsername.getText(), enterPassword.getText())){
                    window.setScene(managerDisplay(window));}
                else if (CustomerAcc.logCreds(enterUsername.getText(), enterPassword.getText())){
                    CustomerAcc customer = new CustomerAcc(new File("src/Customers/" + enterUsername.getText() + ".txt"));
                    window.setScene(customerDisplay(window, customer));}
                else{
                    failedLogin.setText("Login invalid. Try again.");
                    System.out.println("Not Authenticated");}
            }
            catch (FileNotFoundException a){
                failedLogin.setText("Login invalid. Try again.");
            }
        });
        
        clearButton.setOnAction(e -> 
        {
            enterUsername.clear();
            enterPassword.clear();
            failedLogin.setText("");
        });
        
        VBox Username = new VBox(15);
        Username.getChildren().addAll(username, enterUsername);
        VBox Password = new VBox(15);
        Password.getChildren().addAll(password, enterPassword);
        HBox UserData = new HBox(20);
        UserData.getChildren().addAll(Username, Password, login, clearButton, failedLogin);
        
        Username.setAlignment(Pos.CENTER);
        Password.setAlignment(Pos.CENTER);
        UserData.setAlignment(Pos.CENTER);
        StackPane display = new StackPane();
        display.getChildren().addAll(UserData);
        
        firstScene = new Scene(display, 500, 500);
        
        window.setScene(firstScene);
        window.setTitle("Bank Account Application");
        window.setMinHeight(600);
        window.setMinWidth(800);
        window.show();
    }
    
    private Scene managerDisplay(Stage window){
        Label customUsername = new Label("Enter Username: ");
        TextField enterCustomName = new TextField();
        Label customPassword = new Label("Enter Password: ");
        TextField enterCustomPass = new TextField();
        Button add = new Button("New Account");
        Button delete = new Button("Remove Account");
        Label update = new Label("");
        Button goBack = new Button("Logout");
        
        add.setOnAction(e ->{
            try{
                if (manager.addCustomer(enterCustomName.getText(), enterCustomPass.getText()))
                    update.setText("New Customer: " + enterCustomName.getText() + " added.");
                else
                    update.setText("New Customer: " + enterCustomName.getText() + " not added.");
            }catch (IOException a){
                update.setText("An input/output error was encountered");
            }
        });
        
        delete.setOnAction(e -> {
           if (manager.deleteCustomer(enterCustomName.getText()))
               update.setText("Customer " + enterCustomName.getText() + " was deleted.");
           else
               update.setText("Customer " + enterCustomName.getText() + " was not deleted.");
        });
        
        goBack.setOnAction(e -> {
            window.setScene(firstScene);
        });
        
        VBox Username = new VBox(15);
        Username.getChildren().addAll(customUsername, enterCustomName);
        VBox Password = new VBox(15);
        Password.getChildren().addAll(customPassword, enterCustomPass);
        VBox UpdateBox = new VBox(15);
        UpdateBox.getChildren().addAll(update);
        VBox button = new VBox(10);
        button.getChildren().addAll(add, delete, goBack);
        HBox UserData = new HBox(20);
        UserData.getChildren().addAll(Username, Password, UpdateBox, button);
        
        
        Username.setAlignment(Pos.CENTER);
        Password.setAlignment(Pos.CENTER);
        UpdateBox.setAlignment(Pos.CENTER);
        button.setAlignment(Pos.CENTER);
        UserData.setAlignment(Pos.CENTER);
        StackPane display = new StackPane();
        display.getChildren().addAll(UserData);
        
        return new Scene(display, 600, 800);
    }
    
    private Scene customerDisplay(Stage window, CustomerAcc customer){
        Label customUsername = new Label("Username: " + customer.getUsername());
        Label fullBalance = new Label("Balance: " + Double.toString(customer.getBalance()));
        Label customLvl = new Label("Level: " + customer.getLvl().toString());
        TextField moneyInput = new TextField();
        Button deposit = new Button("Deposit");
        Button withdraw = new Button("Withdraw");
        Button onlinePurchase = new Button("Online Purchase");
        Button applyChanges = new Button("Update");
        Button goBack = new Button("Logout");
        Label update = new Label("");
        
        deposit.setOnAction(e -> {
            try{
                if (customer.depositMoney(Double.valueOf(moneyInput.getText()))){
                    update.setText("Money deposited.");
                }
                else{
                    update.setText("Money not deposited.");
                }
            }catch (IOException a) {
                update.setText("Enter a valid total.");
            }
        });
        
        withdraw.setOnAction(e -> {
            try{
                if (customer.withdrawMoney(Double.valueOf(moneyInput.getText()))){
                    update.setText("Money withdrawn.");
                }
                else{
                    update.setText("Money not withdrawn.");
                }
            }catch (IOException a){
                update.setText("Enter a valid total.");
            }
        });
        
        onlinePurchase.setOnAction(e -> {
            try {
                if (customer.onlinePurchase(Double.valueOf(moneyInput.getText()))){
                    update.setText("Purchase made.");
                }
                else{
                    update.setText("Purchase not made.");
                }
            }catch(IOException a) {
                update.setText("Enter a valid total.");
            }
        });
        
        applyChanges.setOnAction(e -> {
            customUsername.setText("Username: " + customer.getUsername());
            fullBalance.setText("Balance: " + Double.toString(customer.getBalance()));
            customLvl.setText("Level: " + customer.getLvl().toString());
            update.setText("");
        });
        
        goBack.setOnAction(e -> {
            window.setScene(firstScene);
        });
        
        VBox leftSide = new VBox(10);
        leftSide.getChildren().addAll(customUsername, fullBalance, customLvl, moneyInput, update);
        HBox buttonRow1 = new HBox(10);
        buttonRow1.getChildren().addAll(deposit, withdraw, onlinePurchase);
        HBox buttonRow2 = new HBox(10);
        buttonRow2.getChildren().addAll(applyChanges, goBack);
        VBox rightSide = new VBox(20);
        rightSide.getChildren().addAll(buttonRow1, buttonRow2);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(leftSide, rightSide);
        StackPane display = new StackPane();
        display.getChildren().addAll(layout);
        
        leftSide.setAlignment(Pos.CENTER_LEFT);
        buttonRow1.setAlignment(Pos.CENTER_RIGHT);
        buttonRow2.setAlignment(Pos.CENTER_RIGHT);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        layout.setAlignment(Pos.CENTER);
        
        return new Scene(display, 600, 800);
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
 