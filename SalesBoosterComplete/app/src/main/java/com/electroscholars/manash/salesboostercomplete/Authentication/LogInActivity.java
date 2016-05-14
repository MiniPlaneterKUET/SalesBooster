package com.electroscholars.manash.salesboostercomplete.Authentication;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.firebase.client.Firebase;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;
import com.electroscholars.manash.salesboostercomplete.R;



public class LogInActivity extends AppCompatActivity {

    private final static String TAG = "Check";

    private CoordinatorLayout coordinatorLayout;
    private MaterialEditText usernameEditText;
    private MaterialEditText passwordEditText;
    private FButton loginButton;

    private String username;
    private String password;
    public Firebase firebase;

    private Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_log_in);

    }
}
