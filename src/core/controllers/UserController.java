/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.StorageUser;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    
    public static Response registerUser(String id,String firstname,String lastname,String age){  
    try {
        int idInt,ageInt;
        try{
            if(id.length()>9){
                return new Response("Id must have 9 digits maximum", Status.BAD_REQUEST);
            }
            idInt = Integer.parseInt(id);
            if(idInt<0){
                 return new Response("Id must be positive", Status.BAD_REQUEST);
            }

        } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
        }
        
        if(firstname.equals("")){
              return new Response("Firstname must be not empty", Status.BAD_REQUEST);
        }
        
        if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
        }
            
        try{
            if(age.length()>9){
                return new Response("Age must have 9 digits maximum", Status.BAD_REQUEST);
            }
            ageInt = Integer.parseInt(age);
            if(ageInt<18){
                 return new Response("Age must be positive and over 18", Status.BAD_REQUEST);
            } 
        } catch (NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
        }
        StorageUser storage = StorageUser.getInstance();            
            if (!storage.addUser(new User(idInt, firstname, lastname, ageInt))) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            }
        return new Response("User created successfully", Status.CREATED);
    } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public static Response refreshUsersResponse(){
         return new Response("User list refreshed successfully", Status.CREATED);
    }
    public static List<User> refreshUsers(){
        try{
        StorageUser storage = StorageUser.getInstance();  
        List<User> users = storage.getAllUsers();
        
        users.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));
         return users;
         
         
        } catch (Exception ex) {
           return new ArrayList<>();
        }
        
    }
}
    
