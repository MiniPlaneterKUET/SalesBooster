package com.electroscholars.manash.gridviewtest;

import android.app.IntentService;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridMainActivity extends AppCompatActivity {

    private final static int GRIDVIEW_COLUMN = 5;

    private GridView gridView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
    private int row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_main);

        gridView = (GridView) findViewById(R.id.gridView);

        arrayList = new ArrayList<>();

        for (int i = 0; i < 25; i++){
            arrayList.add(String.valueOf(i + 1));
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.bal,
                arrayList);

        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                //Gets row from index
                row = (int) Math.floor(i / GRIDVIEW_COLUMN + 1);

                AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder
                        (GridMainActivity.this);

                confirmDialogBuilder.setTitle("Do you want to delete row " + String.valueOf(row)
                        + " ?");
                confirmDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int removeFromIndex = (row - 1) * GRIDVIEW_COLUMN;
                        for (int j = 0; j < GRIDVIEW_COLUMN; j++){
                            arrayList.remove(removeFromIndex);
                        }
                        gridView.setAdapter(arrayAdapter);
                        Toast.makeText(getApplicationContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                    }
                });

                confirmDialogBuilder.setNegativeButton("No!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Nothing was changed!", Toast
                                .LENGTH_SHORT).show();
                    }
                });

                AlertDialog confirmDialog = confirmDialogBuilder.create();
                confirmDialog.show();
            }

        });

    }
}
