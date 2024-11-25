/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Antonella
 */
public class StorageUser {
    
    // Instancia Singleton
    private static StorageUser instance;
    
    // Atributos del StorageUser
    private ArrayList<User> users;
    
    private StorageUser() {
        this.users = new ArrayList<>();
    }
    
    public static StorageUser getInstance() {
        if (instance == null) {
            instance = new StorageUser();
        }
        return instance;
    }
    
    public boolean addUser(User user) {
        for (User p : this.users) {
            if (p.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
    
    public User getUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
    public List<User> getAllUsers() {
    return this.users; 
}
       
}
