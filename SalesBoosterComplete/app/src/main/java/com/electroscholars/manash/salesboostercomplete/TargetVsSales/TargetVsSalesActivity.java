package com.electroscholars.manash.salesboostercomplete.TargetVsSales;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.electroscholars.manash.salesboostercomplete.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import info.hoang8f.widget.FButton;

public class TargetVsSalesActivity extends AppCompatActivity {

    private static final String REMAINING = "Remaining";
    private static final String SALES = "Sales";

    //Setting default sales count to 30
    private static int SALES_COUNT = 30;
    private int REMAINING_COUNT = 60;
    private int TARGET_COUNT = 0;

    //Color in hex
    private static final String REMAINING_COLOR = "#004D40";
    private static final String SALES_COLOR = "#8BC34A";

    //Fields
    private MaterialEditText setTargetEditText;
    private FButton updateTargetButton;
    private PieChart salesVsTargetPieChart;
    private PieModel remainingPieModel;
    private PieModel salesPieModel;

    private TextView salesCountTextView;

    //Hide keypad after target is set
    public static void HIDE_SOFT_KEYBOARD(Activity activity, View view){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_vs_sales);

        //Getting field references
        salesVsTargetPieChart = (PieChart) findViewById(R.id.salesVsTargetPieChart);

        setTargetEditText = (MaterialEditText) findViewById(R.id.setTargetEditText);

        updateTargetButton = (FButton) findViewById(R.id.updateChartButton);

        salesCountTextView = (TextView) findViewById(R.id.salesCountTextView);

        //Creating the models
        salesPieModel = new PieModel(SALES, TargetVsSalesActivity.SALES_COUNT, Color.parseColor(SALES_COLOR));

        remainingPieModel = new PieModel(REMAINING, REMAINING_COUNT, Color.parseColor(REMAINING_COLOR));

        salesCountTextView.setText("Current Sales Count: " + String.valueOf(SALES_COUNT));

        //Adding Listener
        updateTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setTargetEditText.getText().toString().isEmpty()){
                    Toast.makeText(TargetVsSalesActivity.this, "Target is Empty!", Toast.LENGTH_LONG).show();
                } else {
                    HIDE_SOFT_KEYBOARD(TargetVsSalesActivity.this, view);
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
