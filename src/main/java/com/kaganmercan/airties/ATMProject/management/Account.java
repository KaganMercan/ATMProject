package com.kaganmercan.airties.ATMProject.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.kaganmercan.airties.ATMProject.management.Menu.*;
import static com.kaganmercan.airties.ATMProject.operations.FileOperations.*;

/**
 * @author kaganmercan
 */

public class Account extends Customer implements Menu {
    Scanner sc = new Scanner(System.in);
    List<Account> accountList = new ArrayList<>();
    String username, password, iban;
    double balance;

    public Account() {
        super();

    }

    public Account(String username, String password, String iban, double balance) {
        super(username, password, iban, balance);
    }

    public void fillAccountList() throws IOException {
        String customerFile = fileReader();
        String[] accountArray = stringParser(customerFile);
        String[] accountProperties;
        if (customerFile.equals("")) {
            addAccount();
        } else {
            for (String s : accountArray) {
                accountProperties = s.split("\\|");
                username = accountProperties[0];
                password = accountProperties[1];
                iban = accountProperties[2];
                balance = Double.parseDouble(accountProperties[3]);
                Account account = new Account(username, password, iban, balance);
                accountList.add(account);
            }
        }
    }

    public void welcomeScreen() throws IOException {
        boolean exit = true;
        while (exit) {
            mainMenu();
            int selection = sc.nextInt();
            System.out.println();
            switch (selection) {
                case 1:
                    adminPanel();
                    break;
                case 2:
                    exit = false;
                    Login.loginChecker(accountList);
                    break;
            }
        }
    }

    public void adminPanel() throws IOException {
        while (true) {
            adminPanelMenu();
            int selection = sc.nextInt();
            switch (selection) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    displayAccounts();
                    System.out.println();
                    break;
                case 3:
                    welcomeScreen();
                    break;
            }
        }
    }

    public void addAccount() throws IOException {
        boolean exit = true;
        while (exit) {
            addCustomerMenu();
            int selection = sc.nextInt();
            switch (selection) {
                case 1:
                    // Creating new customers with given inputs from user...
                    enterCustomerName(accountList);
                    enterCustomerPassword();
                    enterCustomerIban();
                    enterCustomerBalance();
                    Account account = new Account(username, password, iban, balance);
                    // Add customers to customers.txt...
                    String accountString = account.getUsername() + "|" + account.getPassword() + "|" +
                            account.getIban() + "|" + account.getBalance();
                    saveToFile(accountString, true);
                    accountList.add(account);
                    break;
                case 2:
                    exit = false;
                    adminPanel();
                    break;
            }
        }
    }

    public void displayAccounts() {
        // Reading from customers.txt and displaying all created customers...
        String customerFile = fileReader();
        if (!customerFile.isEmpty()) {
            String[] accountArray = stringParser(customerFile);
            String[] accountProperties;
            for (String s : accountArray) {
                accountProperties = s.split("\\|");
                username = accountProperties[0];
                password = accountProperties[1];
                iban = accountProperties[2];
                balance = Double.parseDouble(accountProperties[3]);
                System.out.println("---------------------------------------------------");
                System.out.printf("Customer username: %s", username);
                System.out.println();
                System.out.printf("Customer iban: %s", iban);
                System.out.println();
                System.out.println("---------------------------------------------------");
                System.out.println();
            }
        }
    }

    public void updateAccountStatus(List<Account> accountList, String username, String password, String iban, double balance) throws IOException {
        String customerFile = fileReader();
        if (!customerFile.isEmpty()) {
            String[] accountArray = stringParser(customerFile);
            String[] accountProperties;
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getUsername().equals(username)) {
                    Account oldAccount = new Account(username, password, iban, accountList.get(i).getBalance());
                    Account account = new Account(username, password, iban, balance);
                    String oldAccountString = oldAccount.getUsername() + "|" + oldAccount.getPassword() + "|" +
                            oldAccount.getIban() + "|" + oldAccount.getBalance();
                    String newAccountString = account.getUsername() + "|" + account.getPassword() + "|" +
                            account.getIban() + "|" + account.getBalance();
                    updateFile(oldAccountString, newAccountString);
                }
            }
        }
    }

    // Customer Name initialization
    private void enterCustomerName(List<Account> accountList) {
        try {
            sc.nextLine();
            System.out.print("Enter customer username: ");
            this.username = sc.nextLine();
            for (int i = 0; i < accountList.size(); i++) {
                if (username.equals(accountList.get(i).getUsername())) {
                    System.out.println("This user exist in our database. Try another...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Customer Password initialization
    private void enterCustomerPassword() {
        try {
            System.out.print("Enter customer password: ");
            password = sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Customer Iban initialization
    private void enterCustomerIban() {
        try {
            System.out.print("Enter customer IBAN: ");
            iban = sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Customer Balance initialization
    private void enterCustomerBalance() {
        try {
            System.out.print("Enter customer balance: ");
            balance = sc.nextDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
