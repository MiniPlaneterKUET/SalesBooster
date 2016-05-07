package com.example.pial.login_firebase.Model;

/**
 * Created by Pial on 5/6/2016.
 */
public class User {
    public String userID;
    public String passWord;
    public User() {

    }
    public User(String userID, String passWord) {
        this.userID = userID;
        this.passWord = passWord;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassWord() {
        return passWord;
    }
}
