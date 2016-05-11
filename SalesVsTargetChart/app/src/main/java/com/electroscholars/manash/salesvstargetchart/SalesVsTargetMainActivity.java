package com.electroscholars.manash.salesvstargetchart;

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
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;



public class SalesVsTargetMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_vs_target_main);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);

//        pieChart.addPieSlice(new PieModel("Remaining", 70, Color.parseColor("#FE6DA8")));
//        pieChart.addPieSlice(new PieModel("Sales", 30, Color.parseColor("#FED70E")));

        PieModel remainingModel = new PieModel("Remaining", 70, Color.parseColor("#004D40"));
        remainingModel.setShowLabel(true);

        PieModel salesModel = new PieModel("Sales", 30, Color.parseColor("#8BC34A"));
        salesModel.setShowLabel(true);

        pieChart.addPieSlice(remainingModel);
        pieChart.addPieSlice(salesModel);

        pieChart.startAnimation();
    }
}
