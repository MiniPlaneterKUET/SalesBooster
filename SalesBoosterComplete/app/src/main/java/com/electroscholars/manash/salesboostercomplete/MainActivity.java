package com.electroscholars.manash.salesboostercomplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.electroscholars.manash.salesboostercomplete.Authentication.LogInActivity;
import com.electroscholars.manash.salesboostercomplete.HelperClass.SetPreferences;


public class MainActivity extends AppCompatActivity {

    private final static String HOMEPAGE_TITLE = "Homepage";

    //Toolbar
    private Toolbar toolbar;

    //Buttons
    private Button targetVsSalesButton;
    private Button messageButton;
    private Button takeOrderButton;
    private Button newsButton;
    private Button viewClientsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        //Getting the support action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(HOMEPAGE_TITLE);

        //Getting the button references
        targetVsSalesButton = (Button) findViewById(R.id.targetVsSalesButton);
        messageButton = (Button) findViewById(R.id.messageButton);
        takeOrderButton = (Button) findViewById(R.id.takeOrderButton);
        newsButton = (Button) findViewById(R.id.newsButton);
        viewClientsButton = (Button) findViewById(R.id.viewClientsButton);

        //Setting button on action listener
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu with user login icon
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logOut:
                SetPreferences preferences = new SetPreferences();
                preferences.setPreference(getApplicationContext(), "000");
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
