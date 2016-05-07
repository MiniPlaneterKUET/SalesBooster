package com.electroscholars.manash.takeorderclient.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import com.electroscholars.manash.takeorderclient.R;

public class TakeOrderActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private final static int COLUMN_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        gridLayout.setColumnCount(COLUMN_COUNT);
        gridLayout.setRowCount(10);




        for (int i = 0; i < COLUMN_COUNT; i++){
            for (int j = 0; j < 10; j++){
                GridLayout.Spec row = GridLayout.spec(j);
                GridLayout.Spec col = GridLayout.spec(i);

                TextView textView = new TextView(this);
                textView.setText("(" + String.valueOf(i) + " , " + String.valueOf(j) + ")");

                gridLayout.addView(textView, new GridLayout.LayoutParams(row, col));
            }
        }

    }
}
