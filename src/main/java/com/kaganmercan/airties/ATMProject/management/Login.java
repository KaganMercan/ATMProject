package com.kaganmercan.airties.ATMProject.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/**
 * @author kaganmercan
 */

public class Login {
    public Login() {
    }

    static Scanner sc = new Scanner(System.in);
    private final static Logger log = LoggerFactory.getLogger(Options.class);


    public static void loginChecker(List<Account> accountList) {
        try {
            // Max login count should be 3. If login count > 3 then account should be blocked.
            int loginCount = 3;
            boolean exit = true;
            while (exit) {
                System.out.print("Enter username: ");
                String usernameEntered = sc.nextLine();
                // Password
                System.out.print("Enter password: ");
                String passwordEntered = sc.nextLine();
                System.out.println();
                for (int i = 0; i < accountList.size(); i++) {
                    // Username
                    if (accountList.get(i).getUsername().equals(usernameEntered) &&
                            accountList.get(i).getPassword().equals(passwordEntered)) {
                        System.out.println("Login success");
                        // Menu Screen Start
                        Menu.optionsMenu(usernameEntered);
                        Options.menuScreen(accountList, usernameEntered);
                    }
                }
                if (loginCount <= 1) {
                    log.warn("Too many failed login attempts. Your account has been locked.");
                    System.out.println("Too many failed login attempts. Your account has been locked.");
                    System.exit(0);
                    exit = false;
                } else {
                    loginCount--;
                    log.info("Login failed...");
                    System.out.println("Login failed");
                }
            }
        } catch (Exception e) {
            log.warn("System Error\t\nException: ", e);
        }

    }
}


