package com.electroscholars.manash.salesvstargetchart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;


public class SalesVsTargetMainActivity extends AppCompatActivity {

    private static final String REMAINING = "Remaining";
    private static final String SALES = "Sales";

    private static int SALES_COUNT = 30;
    private int REMAINING_COUNT = 60;
    private int TARGET_COUNT = 0;

    private static final String REMAINING_COLOR = "#004D40";
    private static final String SALES_COLOR = "#8BC34A";

    private MaterialEditText setTargetEditText;
    private FButton updateTargetButton;
    private PieChart salesVsTargetPieChart;
    private PieModel remainingPieModel;
    private PieModel salesPieModel;

    private TextView salesCountTextView;

    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_vs_target_main);

        salesVsTargetPieChart = (PieChart) findViewById(R.id.salesVsTargetPieChart);
        setTargetEditText = (MaterialEditText) findViewById(R.id.setTargetEditText);
        updateTargetButton = (FButton) findViewById(R.id.updateChartButton);

        salesPieModel = new PieModel(SALES, SalesVsTargetMainActivity.SALES_COUNT, Color.parseColor
                (SALES_COLOR));
        remainingPieModel = new PieModel(REMAINING, REMAINING_COUNT, Color.parseColor
                (REMAINING_COLOR));
        salesCountTextView = (TextView) findViewById(R.id.salesCountTextView);

        salesCountTextView.setText("Current Sales Count: " + String.valueOf(SALES_COUNT));

        //Adding Listener
        updateTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setTargetEditText.getText().toString().isEmpty()){
                    Toast.makeText(SalesVsTargetMainActivity.this, "Target is Empty!", Toast
                            .LENGTH_LONG).show();
                } else {
                    hideSoftKeyboard(SalesVsTargetMainActivity.this, view);
                    TARGET_COUNT = Integer.valueOf(setTargetEditText.getText().toString());
                    REMAINING_COUNT = TARGET_COUNT - SALES_COUNT;
                    remainingPieModel.setValue(REMAINING_COUNT);
                    salesVsTargetPieChart.clearChart();
                    salesVsTargetPieChart.addPieSlice(salesPieModel);
                    salesVsTargetPieChart.addPieSlice(remainingPieModel);
                    salesVsTargetPieChart.startAnimation();
                }
            }
        });

        salesVsTargetPieChart.addPieSlice(salesPieModel);
        salesVsTargetPieChart.addPieSlice(remainingPieModel);
        salesVsTargetPieChart.startAnimation();

    }
}
