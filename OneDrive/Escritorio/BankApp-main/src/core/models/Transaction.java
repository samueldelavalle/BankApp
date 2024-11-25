/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.utils.TransactionType;

/**
 *
 * @author Antonella
 */
public class Transaction {
    
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    
    public Transaction(TransactionType type, Account sourceAccount, Account destinationAccount, double amount) {
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
