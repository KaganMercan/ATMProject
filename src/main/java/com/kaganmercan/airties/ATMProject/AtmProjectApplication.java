package com.kaganmercan.airties.ATMProject;

import com.kaganmercan.airties.ATMProject.management.Account;

import java.io.IOException;

public class AtmProjectApplication {

    public static void main(String[] args) throws IOException {
        Account account = new Account();
        account.fillAccountList();
        account.welcomeScreen();
    }
}
