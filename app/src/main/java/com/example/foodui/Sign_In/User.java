package com.example.foodui.Sign_In;

import java.util.Date;

public class User {
    String username;
    String Email;
    Date sessionExpiryDate;

    public void setUsername(String username){this.username = username;}
    public void setEmail(String email){this.Email = email;}
    public void setSessionExpiryDate(Date sessionExpiryDate){this.sessionExpiryDate = sessionExpiryDate;}

    public String getUsername(){return username;}
    public String getEmail(){return Email;}
    public Date getSessionExpiryDate(){return sessionExpiryDate;}
}
