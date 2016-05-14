package com.electroscholars.manash.salesboostercomplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.electroscholars.manash.salesboostercomplete.Authentication.LogInActivity;
import com.electroscholars.manash.salesboostercomplete.HelperClass.SetPreferences;


public class MainActivity extends AppCompatActivity {

    //Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        //Getting the support action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
