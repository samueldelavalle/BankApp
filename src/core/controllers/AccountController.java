/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.StorageAccount;
import core.models.storage.StorageUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountController {
    
    public static String generateId(String userId){
        int userIdInt;
        try {
            userIdInt = Integer.parseInt(userId);
            User selectedUser = null;
            //for (User user : this.users) {
            StorageUser storage = StorageUser.getInstance();
            User user = storage.getUser(userIdInt);
                if (user.getId() == userIdInt && selectedUser == null) {
                    selectedUser = user;
                }
            //}
            String accountId = null;
            if (selectedUser != null) {
             Random random = new Random();
               int first = random.nextInt(1000);
               int second = random.nextInt(1000000);
               int third = random.nextInt(100);
                
               accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
        }
            return accountId;
       } catch (Exception ex) {
           return null;
       }
    }
    
    public static Response createAccount(String userId, String initialBalance){
        
        try{    
            int userIdInt;
            double initialBalanceDouble;
            
            try{ 
            
                if(userId.length()>9){
                return new Response("Id must have 9 digits maximum", Status.BAD_REQUEST);
            }
            userIdInt = Integer.parseInt(userId);
            if(userIdInt<0){
                 return new Response("Id must be positive", Status.BAD_REQUEST);
            }

        } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
        }
            
            
            
            try{
               
            initialBalanceDouble = Double.parseDouble(initialBalance);
            if(initialBalanceDouble<0){
                 return new Response("Initial Balance must be positive", Status.BAD_REQUEST);
            } 
                } catch (NumberFormatException ex) {
                return new Response("Initial Balance must be numeric", Status.BAD_REQUEST);
                }
            
            String accountId = generateId(userId);
            
            StorageAccount storage = StorageAccount.getInstance();
            StorageUser storageUser = StorageUser.getInstance();
            User user = storageUser.getUser(userIdInt);

            if (user == null) {
            return new Response("User not found", Status.BAD_REQUEST);
            }

            Account account = new Account(accountId, user, initialBalanceDouble);
            storage.addAccount(account);
            
            return new Response("Account created successfully", Status.CREATED);
        } catch(Exception ex){
          return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
        public static Response refreshAccountResponse(){
         return new Response("Account list refreshed successfully", Status.CREATED);
    }
    
    public static List<Account> refreshAccount(){
        try{
        StorageAccount storage = StorageAccount.getInstance();  
        List<Account> accounts = storage.getAllAccounts();
        
        accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));
         return accounts;
        } catch (Exception ex) {
           return new ArrayList<>();
        }
        
    }
}
