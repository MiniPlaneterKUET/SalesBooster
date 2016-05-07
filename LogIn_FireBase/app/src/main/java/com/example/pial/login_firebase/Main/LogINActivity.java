
//Author: Pial Kanti

package com.example.pial.login_firebase.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pial.login_firebase.Model.User;
import com.example.pial.login_firebase.R;
import com.firebase.client.Firebase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LogINActivity extends AppCompatActivity {

    public CoordinatorLayout coordinatorLayout;
    public com.rengwuxian.materialedittext.MaterialEditText userEditText,passwordEdtText;
    public info.hoang8f.widget.FButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_log_in);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        userEditText=(MaterialEditText)findViewById(R.id.usernameEditText);
        passwordEdtText=(MaterialEditText)findViewById(R.id.passwordEditText);
        loginButton=(info.hoang8f.widget.FButton)findViewById(R.id.logInButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This checks if Internet is turn om or off

                ConnectivityManager cm =
                        (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(!isConnected){
                    //Showing SnackBar that internet is turned off

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No Inetrnet connection", Snackbar.LENGTH_LONG)
                            .setAction("Turn on",new View.OnClickListener(){
                                public void onClick(View view) {
                                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.YELLOW);

                    snackbar.show();
                }

            }
        });


    }
}
