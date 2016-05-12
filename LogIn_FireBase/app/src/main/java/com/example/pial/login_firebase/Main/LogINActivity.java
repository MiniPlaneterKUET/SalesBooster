
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.pial.login_firebase.Model.SetPreferences;
import com.example.pial.login_firebase.Model.User;
import com.example.pial.login_firebase.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LogINActivity extends AppCompatActivity {

    private final static String TAG = "Check";

    public CoordinatorLayout coordinatorLayout;
    public com.rengwuxian.materialedittext.MaterialEditText userEditText, passwordEdtText;
    public info.hoang8f.widget.FButton loginButton;
    public String username;
    public String password;
    public Firebase ref;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_log_in);

        //Setting ToolBar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        userEditText = (MaterialEditText) findViewById(R.id.usernameEditText);
        passwordEdtText = (MaterialEditText) findViewById(R.id.passwordEditText);
        loginButton = (info.hoang8f.widget.FButton) findViewById(R.id.logInButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This checks if Internet is turn om or off

                ConnectivityManager cm =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if (isConnected) {
                    username = userEditText.getText().toString();
                    password = passwordEdtText.getText().toString();


                    //Checking username and password is not empty
                    if (!username.isEmpty() && !password.isEmpty()) {
                        //firebase object
                        ref = new Firebase("https://sales-booster.firebaseio.com/User").child(username);

                        //getting value from firebase

                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    //getting password from Firebase
                                    User user = dataSnapshot.getValue(User.class);
                                    String pass = user.getPassWord();
                                    if (password.equals(pass)) {
                                        // Saving username in Shared preferences for Session
                                        SetPreferences preferences=new SetPreferences();
                                        preferences.setPreference(getApplicationContext(),username);

                                        Intent intent = new Intent(LogINActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(coordinatorLayout, "Wrong password", Snackbar.LENGTH_LONG).show();
                                    }

                                } else {
                                    Snackbar.make(coordinatorLayout, "Wrong username", Snackbar.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    } else {
                        //Checking username or password is null
                        Snackbar.make(coordinatorLayout, "Please enter username or password", Snackbar.LENGTH_LONG).show();
                    }


                } else {
                    //Showing SnackBar that internet is turned off

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No Inetrnet connection", Snackbar.LENGTH_LONG)
                            .setAction("Turn on", new View.OnClickListener() {
                                public void onClick(View view) {
                                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            });

                    // Changing action message text color
                    snackbar.setActionTextColor(Color.YELLOW);

                    snackbar.show();
                }

            }
        });


    }
}
