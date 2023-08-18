package com.example.myapplication;

public class Users {

    String emailAddress, password;

    public Users(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

     public Users(){}

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
