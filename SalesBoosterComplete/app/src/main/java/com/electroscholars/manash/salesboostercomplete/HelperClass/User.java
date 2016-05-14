package com.electroscholars.manash.salesboostercomplete.HelperClass;

/**
 * Created by Manash on 5/14/2016.
 *
 * Original Author: Pial Kanti
 */
public class User {
    public String userID;
    public String passWord;

    public User(){

    }

    public User(String userID, String password){
        this.userID = userID;
        this.passWord = password;
    }

    public String getUserID(){ return this.userID; }

    public String getPassword() { return this.passWord; }

}
