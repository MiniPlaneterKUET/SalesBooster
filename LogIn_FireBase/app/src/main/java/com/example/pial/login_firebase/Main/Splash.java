package com.example.pial.login_firebase.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pial.login_firebase.Model.SetPreferences;
import com.example.pial.login_firebase.R;

public class Splash extends AppCompatActivity {

    private  String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread(){
            @Override
            public void run() {
                try {
                    //Screen freeze for 2 seconds
                    sleep(2*1000);

                    SetPreferences preferences = new SetPreferences();
                    userName = preferences.getPreference(getApplicationContext());

                    if(userName.equals("000")){
                        Intent intent = new Intent(Splash.this,LogINActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Splash.this,MainActivity.class);
                        startActivity(intent);
                    }

                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        background.start();
    }
}
