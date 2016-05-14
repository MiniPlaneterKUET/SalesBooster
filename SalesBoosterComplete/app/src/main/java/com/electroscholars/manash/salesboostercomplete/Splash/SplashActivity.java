package com.electroscholars.manash.salesboostercomplete.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.electroscholars.manash.salesboostercomplete.Authentication.LogInActivity;
import com.electroscholars.manash.salesboostercomplete.HelperClass.SetPreferences;
import com.electroscholars.manash.salesboostercomplete.MainActivity;
import com.electroscholars.manash.salesboostercomplete.R;


public class SplashActivity extends AppCompatActivity {

    //Sleep time
    private final static int FREEZE_TIME = 2000;

    //username variable
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(FREEZE_TIME);
                    SetPreferences preferences = new SetPreferences();
                    username = preferences.getPreference(getApplicationContext());

                    if (username.equals("000")){
                        Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    finish();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        background.start();
    }
}
