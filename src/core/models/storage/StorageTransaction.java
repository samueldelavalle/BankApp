/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonella
 */
public class StorageTransaction {
    
    // Instancia Singleton
    private static StorageTransaction instance;
    
    // Atributos del StorageTransaction
    private ArrayList<Transaction> transactions;
    
    private StorageTransaction() {
        this.transactions = new ArrayList<>();
    }
    
    public static StorageTransaction getInstance() {
        if (instance == null) {
            instance = new StorageTransaction();
        }
        return instance;
    }
    
    public boolean addTransaction(Transaction transaction) {
        
        this.transactions.add(transaction);
        return true;
    }
    
    public Transaction getSourceAccount(Account sourceAccount) {
        for (Transaction transaction : this.transactions) {
            if (transaction.getSourceAccount() == sourceAccount) {
                return transaction;
            }
        }
        return null;
    }
    public Transaction getDestinationAccount(Account destinationAccount) {
        for (Transaction transaction : this.transactions) {
            if (transaction.getDestinationAccount() == destinationAccount) {
                return transaction;
            }
        }
        return null;
    }
    public List<Transaction> getAllTransactions() {
    return this.transactions; 
    }

}
