/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Transaction;
import core.controllers.utils.TransactionType;
import core.models.Account;
import core.models.storage.StorageAccount;
import core.models.storage.StorageTransaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TransactionController {
    public static Response executeTransaction(String type,String sourceAccountId,String destinationAccountId,String amount ){
        try {
            double amountDouble;
            try{
            amountDouble = Double.parseDouble(amount);
            if (amountDouble <= 0){
                return new Response("Amount must be positive", Status.BAD_REQUEST);
            }
            }catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }
            
            StorageAccount storageAccount = StorageAccount.getInstance();
            StorageTransaction storage = StorageTransaction.getInstance();
            List<Account> accounts = storageAccount.getAllAccounts();
            
            switch (type) {
                case "Deposit": {
                   
                    Account destinationAccount = null;
                    for (Account account : accounts) {
                        if (account.getId().equals(destinationAccountId)) {
                            destinationAccount = account;
                        }
                    }
                    if (destinationAccount != null) {
                        destinationAccount.deposit(amountDouble);
                        
                        storage.addTransaction(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountDouble));
                        return new Response("Deposit successful", Status.CREATED);
                    }
                    return new Response("Destination account not found", Status.BAD_REQUEST);
                }
                case "Withdraw": {
                    
                    Account sourceAccount = null;
                    for (Account account : accounts) {
                        if (account.getId().equals(sourceAccountId)) {
                            sourceAccount = account;
                        }
                    }
                    if (sourceAccount != null && sourceAccount.withdraw(amountDouble)) {
                        storage.addTransaction(new Transaction(TransactionType.WITHDRAW, sourceAccount, null, amountDouble));
                        return new Response("Withdrawal successful", Status.CREATED);
                    }
                    return new Response("Insufficient funds or source account not found", Status.BAD_REQUEST);
                }
                case "Transfer": {

                    Account sourceAccount = null;
                    Account destinationAccount = null;
                    for (Account account : accounts) {
                        if (account.getId().equals(sourceAccountId)) {
                            sourceAccount = account;
                        }
                    }
                    for (Account account : accounts) {
                        if (account.getId().equals(destinationAccountId)) {
                            destinationAccount = account;
                        }
                    }
                    if (sourceAccount != null && destinationAccount != null && sourceAccount.withdraw(amountDouble)) {
                        destinationAccount.deposit(amountDouble);
                        
                        storage.addTransaction(new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amountDouble));
                        return new Response("Transfer successful", Status.CREATED);
                    }
                    return new Response("Transfer failed. Check accounts and balance", Status.BAD_REQUEST);
                }
                default: {
                    return new Response("Invalid transaction type", Status.BAD_REQUEST);
                }
            }
        } catch(Exception ex){
          return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response refreshTransactionResponse(){
         return new Response("Transaction list refreshed successfully", Status.CREATED);
    }
    
    public static List<Transaction> refreshTransaction(){
        try{
        StorageTransaction storage = StorageTransaction.getInstance();  
        List<Transaction> transactions = storage.getAllTransactions();
        List<Transaction> transactionsCopy = new ArrayList<>(transactions);
        Collections.reverse(transactionsCopy);
        
         return transactionsCopy;
        } catch (Exception ex) {
           return new ArrayList<>();
        }
    }
}
