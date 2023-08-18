package com.example.myapplication;

public class Teachers {

    String emailAddressT, passwordT;

    public Teachers(String emailAddressT, String passwordT) {
        this.emailAddressT = emailAddressT;
        this.passwordT = passwordT;
    }

    public Teachers() {}

    public String getEmailAddressT() {
        return emailAddressT;
    }

    public void setEmailAddressT(String emailAddressT) {
        this.emailAddressT = emailAddressT;
    }

    public String getPasswordT() {
        return passwordT;
    }

    public void setPasswordT(String passwordT) {
        this.passwordT = passwordT;
    }
}
