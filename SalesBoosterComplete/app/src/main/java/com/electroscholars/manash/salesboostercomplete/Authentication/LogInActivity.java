package com.electroscholars.manash.salesboostercomplete.Authentication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.renderscript.Type;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.electroscholars.manash.salesboostercomplete.HelperClass.SetPreferences;
import com.electroscholars.manash.salesboostercomplete.HelperClass.TypeWriter;
import com.electroscholars.manash.salesboostercomplete.HelperClass.User;
import com.electroscholars.manash.salesboostercomplete.MainActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;
import com.electroscholars.manash.salesboostercomplete.R;



public class LogInActivity extends AppCompatActivity {

    private final static String FIREBASE_APP_URL = "https://sales-booster.firebaseio.com/User";

    private final static String TAG = "Check";

    //App Title
    private final static String SALES_BOOSTER_TITLE = "Sales Booster";

    private CoordinatorLayout coordinatorLayout;
    private MaterialEditText usernameEditText;
    private MaterialEditText passwordEditText;
    private FButton loginButton;

    private String username;
    private String password;
    public Firebase firebase;

    private Toolbar toolbar;


    private TypeWriter salesBoosterTitleTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_log_in);
        salesBoosterTitleTextView = (TypeWriter) findViewById(R.id.salesBoosterTextView);
        salesBoosterTitleTextView.setText(SALES_BOOSTER_TITLE);
        salesBoosterTitleTextView.animate();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        usernameEditText = (MaterialEditText) findViewById(R.id.usernameEditText);
        passwordEditText = (MaterialEditText) findViewById(R.id.passwordEditText);
        loginButton = (FButton) findViewById(R.id.logInButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

                boolean isConnected = activeNetwork != null && activeNetwork
                        .isConnectedOrConnecting();

                if (isConnected) {
                    username = usernameEditText.getText().toString();
                    password = passwordEditText.getText().toString();

                    //Checking username and password is not empy
                    if (!username.isEmpty() && !password.isEmpty()){
                        firebase = new Firebase(FIREBASE_APP_URL).child(username);

                        firebase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    User user = dataSnapshot.getValue(User.class);
                                    String pass = user.getPassword();
                                    if (password.equals(pass)){
                                        //Saving username in shared preference for session

                                        SetPreferences preferences = new SetPreferences();
                                        preferences.setPreference(getApplicationContext(), username);

                                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Snackbar.make(coordinatorLayout, "Wrong password", Snackbar.LENGTH_SHORT).show();
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
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection", Snackbar.LENGTH_SHORT).setAction("Turn on", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        });

                        //Changing action message text color
                        snackbar.setActionTextColor(Color.YELLOW);
                        snackbar.show();
                    }
                }
            }
        });
    }
}
