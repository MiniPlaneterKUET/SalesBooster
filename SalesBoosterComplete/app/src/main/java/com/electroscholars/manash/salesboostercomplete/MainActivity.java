package com.electroscholars.manash.salesboostercomplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.electroscholars.manash.salesboostercomplete.Authentication.LogInActivity;
import com.electroscholars.manash.salesboostercomplete.HelperClass.SetPreferences;
import com.electroscholars.manash.salesboostercomplete.Message.MessageActivity;
import com.electroscholars.manash.salesboostercomplete.News.NewsActivity;
import com.electroscholars.manash.salesboostercomplete.TakeOrder.TakeOrderClientActivity;
import com.electroscholars.manash.salesboostercomplete.TargetVsSales.TargetVsSalesActivity;
import com.electroscholars.manash.salesboostercomplete.ViewClient.ClientViewActivity;


public class MainActivity extends AppCompatActivity {

    private final static String HOMEPAGE_TITLE = "Homepage";

    //Intents
    private Intent TARGET_VS_SALES_INTENT;
    private Intent NEWS_INTENT;
    private Intent TAKE_ORDER_INTENT;
    private Intent MESSAGE_INTENT;
    private Intent VIEW_CLIENT_INTENT;

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
        targetVsSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open targetVsSales activity
                TARGET_VS_SALES_INTENT = new Intent(MainActivity.this, TargetVsSalesActivity
                        .class);
                startActivity(TARGET_VS_SALES_INTENT);
            }
        });

        //Message activity
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MESSAGE_INTENT = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(MESSAGE_INTENT);
            }
        });

        //news activity
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NEWS_INTENT = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(NEWS_INTENT);
            }
        });

        //view client activity listener
        viewClientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VIEW_CLIENT_INTENT = new Intent(MainActivity.this, ClientViewActivity.class);
                startActivity(VIEW_CLIENT_INTENT);
            }
        });


        //take order activity listener
        takeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TAKE_ORDER_INTENT = new Intent(MainActivity.this, TakeOrderClientActivity.class);
                startActivity(TAKE_ORDER_INTENT);
            }
        });
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
