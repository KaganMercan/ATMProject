package com.kaganmercan.airties.ATMProject.management;

/**
 * @author kaganmercan
 */
interface Menu {
    static void mainMenu() {
        System.out.print("""
                *****Welcome To AirtiesATM*****
                1 -> Admin Panel\t
                2 -> Login Screen
                Enter selection:""");
    }

    static void adminPanelMenu() {
        System.out.print("""
                 *****Admin Panel*****\t
                1-) Create account\t
                2-) Display accounts\t
                3-) Back\t
                Enter a number for your operation:""");
    }

    static void addCustomerMenu() {
        System.out.print("""
                *****Add New Account*****
                1-) Add Account\t
                2-) Back
                Enter a number for you operation:""");
    }

    static void optionsMenu(String user){
        System.out.printf("Welcome to %s Bank Account", user);
        System.out.println();
        System.out.println("""
                0-)Display Balance \t
                1-)Deposit Money \t
                2-)Withdraw Money \t
                3-)Make a Money Order \t
                4-)EFT \t
                5-)Quit \t
                """);
    }
}
