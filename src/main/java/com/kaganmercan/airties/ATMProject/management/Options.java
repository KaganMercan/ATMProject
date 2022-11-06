package com.kaganmercan.airties.ATMProject.management;

import org.slf4j.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;


/**
 * @author kaganmercan
 */
public class Options implements Menu {
    static Scanner sc = new Scanner(System.in);
    static DecimalFormat doubleFormatter = new DecimalFormat("0.0#");
    private final static Logger log = LoggerFactory.getLogger(Options.class);

    public static void menuScreen(List<Account> accountList, String username) {

        while (true) {
            System.out.print("Enter a number for operation: ");
            int selection = sc.nextInt();
            try {
                switch (selection) {
                    case 0 -> {
                        displayBalance(accountList, username);
                        System.out.println();
                    }
                    case 1 -> {
                        depositMoney(accountList, username);
                        System.out.println();
                    }
                    case 2 -> {
                        withdrawMoney(accountList, username);
                        System.out.println();
                    }
                    case 3 -> {
                        makeMoneyOrder(accountList, username);
                        System.out.println();
                    }
                    case 4 -> {
                        eft(accountList, username);
                        System.out.println();
                    }
                    case 5 -> {
                        System.out.println("Don't forget you card...");
                        log.info("Don't forget you card...");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Your selection is not suitable...");
                        log.info("Your selection is not suitable...");
                    }
                }
            } catch (Exception e) {
                System.out.println("Selection type was not suitable...");
                log.info("Selection type was not suitable");
                log.warn("System Error\t\nException: ", e);
                System.exit(0);
            }
        }
    }

    // Money operations
    public static void displayBalance(List<Account> accountList, String username) {
//        System.out.printf("Balance: %d", customer.getBalance());
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username)) {
                System.out.printf("Balance: %s$", doubleFormatter.format(accountList.get(i).getBalance()));
                System.out.println();
                log.info("Balance: " + doubleFormatter.format(accountList.get(i).getBalance()) + "$");
            }
        }
    }

    public static void depositMoney(List<Account> accountList, String username) throws IOException {
        Account account = new Account();
        System.out.print("Enter amount to deposit: ");
        double depositAmount = sc.nextDouble();
        System.out.println();
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username)) {
                if (depositAmount > 0) {
                    double newBalance = accountList.get(i).getBalance() + depositAmount;
                    account.updateAccountStatus(accountList, accountList.get(i).getUsername(),
                            accountList.get(i).getPassword(), accountList.get(i).getIban(), newBalance);
                    accountList.get(i).setBalance(newBalance);
                    System.out.printf("You deposit %s$", doubleFormatter.format(depositAmount));
                    System.out.println();
                    log.info("You deposit " + doubleFormatter.format(depositAmount) + "$");
                    System.out.printf("New balance: %s$", doubleFormatter.format(newBalance));
                    System.out.println();
                    log.info("New balance: " + doubleFormatter.format(newBalance) + "$");

                } else {
                    System.out.printf("You cannot enter amount %s$ to deposit...",
                            doubleFormatter.format(depositAmount));
                    log.info("You cannot enter amount: " + doubleFormatter.format(depositAmount) + "$");
                }
            }
        }
    }

    public static void withdrawMoney(List<Account> accountList, String username) throws IOException {
        Account account = new Account();
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = sc.nextDouble();
        System.out.println();
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username)) {
                if (accountList.get(i).getBalance() == 0) {
                    System.out.printf("Your balance is %s$, you should deposit money...",
                            doubleFormatter.format(accountList.get(i).getBalance()));
                    System.out.println();
                    depositMoney(accountList, username);
                }
                if (withdrawAmount <= accountList.get(i).getBalance()) {
                    double newBalance = accountList.get(i).getBalance() - withdrawAmount;
                    account.updateAccountStatus(accountList, accountList.get(i).getUsername(),
                            accountList.get(i).getPassword(), accountList.get(i).getIban(), newBalance);
                    accountList.get(i).setBalance(newBalance);
                    System.out.printf("You withdraw %s$", doubleFormatter.format(withdrawAmount));
                    System.out.println();
                    log.info("You withdraw " + doubleFormatter.format(withdrawAmount) + "$");
                    System.out.printf("New balance: %s$", doubleFormatter.format(newBalance));
                    System.out.println();
                    log.info("New balance: " + doubleFormatter.format(newBalance) + "$");

                } else {
                    System.out.printf("You cannot enter amount %s$ to withdraw...",
                            doubleFormatter.format(withdrawAmount));
                    System.out.println();
                }
            }
        }
    }

    public static void makeMoneyOrder(List<Account> accountList, String username) throws IOException {
        sendMoney(accountList, username);
    }

    public static void eft(List<Account> accountList, String username) throws IOException {
        sendMoney(accountList, username);
    }

    private static void sendMoney(List<Account> accountList, String username) throws IOException {
        Account account = new Account();
        sc.nextLine();
        System.out.print("Enter IBAN number to select customer: ");
        String ibanNumber = sc.nextLine();
        System.out.println();

        System.out.print("Amount to send money: ");
        double sendMoney = sc.nextDouble();
        System.out.println();
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username)) {
                if (accountList.get(i).getBalance() >= sendMoney) {
                    for (int j = 0; j < accountList.size(); j++) {
                        if (accountList.get(j).getIban().equals(ibanNumber)) {
                            double senderNewBalance = accountList.get(i).getBalance() - sendMoney;
                            account.updateAccountStatus(accountList, accountList.get(i).getUsername(),
                                    accountList.get(i).getPassword(), accountList.get(i).getIban(), senderNewBalance);
                            accountList.get(i).setBalance(senderNewBalance);
                            double receiverNewBalance = accountList.get(j).getBalance() + sendMoney;
                            account.updateAccountStatus(accountList, accountList.get(j).getUsername(),
                                    accountList.get(j).getPassword(), accountList.get(j).getIban(), receiverNewBalance);
                            accountList.get(j).setBalance(receiverNewBalance);
                            System.out.println();
                            System.out.printf("Your new balance is: %s$\n", doubleFormatter.format(senderNewBalance));
                            log.info("Your new balance is: " + senderNewBalance + "$");
                            System.out.printf("You send %s$ to user %s\n", doubleFormatter.format(sendMoney), accountList.get(j).getUsername());
                            log.info("You send " + sendMoney + "$" + " to user " + accountList.get(j).getUsername());
                            System.out.printf("Receiver with username %s and IBAN %s new balance is: %s$\n",
                                    accountList.get(j).getUsername(),
                                    accountList.get(j).getIban(),
                                    doubleFormatter.format(receiverNewBalance));
                            log.info("Receiver with username " + accountList.get(j).getUsername()
                                    + " and IBAN " + accountList.get(j).getIban()
                                    + " new balance is: " + doubleFormatter.format(receiverNewBalance) + "$");
                        }
                    }
                } else {
                    System.out.println("Insufficient funds to send...");
                    log.info("Insufficient funds to send...");
                }
            }
        }
    }
}

