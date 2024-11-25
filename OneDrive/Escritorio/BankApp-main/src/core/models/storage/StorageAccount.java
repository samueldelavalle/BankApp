/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonella
 */
public class StorageAccount {
    
    // Instancia Singleton
    private static StorageAccount instance;
    
    // Atributos del StorageAccount
    private ArrayList<Account> accounts;
    
    private StorageAccount() {
        this.accounts = new ArrayList<>();
    }
    
    public static StorageAccount getInstance() {
        if (instance == null) {
            instance = new StorageAccount();
        }
        return instance;
    }
    
    public boolean addAccount(Account account) {
        for (Account p : this.accounts) {
            if (p.getId() == account.getId()) {
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }
    
    public Account getAccount(double balance) {
        for (Account account : this.accounts) {
            if (account.getBalance() == balance) {
                return account;
            }
        }
        return null;
    }
    
    public List<Account> getAllAccounts() {
    return this.accounts; 
}

}
