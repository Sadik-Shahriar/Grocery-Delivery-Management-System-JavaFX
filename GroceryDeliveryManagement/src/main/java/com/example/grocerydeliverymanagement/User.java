package com.example.grocerydeliverymanagement;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String username;
    private String password;
    private String phoneNumber;
    public User(String username, String password,String phoneNumber ) {
        this.username = username;
        this.password = password;
        this.phoneNumber=phoneNumber;
    }
    public void setUsername(String username){
         this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
