package com.electroscholars.manash.salesproductivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Typewriting effect
        Typewriter writer = (Typewriter) findViewById(R.id.salesBoosterTextView);

        writer.setCharacterDelay(150);
        writer.animateText("Sales Booster");

    }
}
