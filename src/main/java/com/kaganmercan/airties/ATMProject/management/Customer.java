package com.kaganmercan.airties.ATMProject.management;

/**
 * @author kaganmercan
 */
class Customer {
    private String username;
    private String password;
    private String iban;
    private double balance;

    public Customer(){}
    // Constructor with parameter.
    public Customer(String username, String password, String iban, double balance) {
        this.username = username;
        this.password = password;
        this.iban = iban;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
