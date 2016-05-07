package com.electroscholars.manash.takeorderclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.electroscholars.manash.takeorderclient.Activities.OrderSubmissionActivity;
import com.electroscholars.manash.takeorderclient.Activities.TakeOrderActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MainActivity extends AppCompatActivity {

    //Key
    public final static String EXTRA_MESSAGE = "MainActivity.MESSAGE";

    //Buttons
    private Button resetButton;
    private Button saveButton;
    private Button nextButton;

    //TextFields and spinners
    private MaterialEditText clientNameEditText;
    private MaterialEditText clientIDEditText;
    private MaterialEditText clientContactEditText;
    private MaterialEditText clientEmailEditText;
    private MaterialEditText clientAddressEditText;
    private MaterialEditText clientCityEditText;
    private MaterialEditText clientCountryEditText;
    private MaterialBetterSpinner clientCategoryBetterSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting string array from resources
        final String[] clientCategories = getResources().getStringArray(R.array.clientCategory);

        //Creating adapter
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clientCategories);

        //Binding adapter with the spinner
        clientCategoryBetterSpinner = (MaterialBetterSpinner) findViewById(R.id
                 .categoryMaterialBetterSpinner);
        clientCategoryBetterSpinner.setAdapter(categoryAdapter);

        //Assigning all the editTexts
        clientNameEditText = (MaterialEditText) findViewById(R.id.clientNameEditText);
        clientIDEditText = (MaterialEditText) findViewById(R.id.clientIDEditText);
        clientContactEditText = (MaterialEditText) findViewById(R.id.clientContactEditText);
        clientEmailEditText = (MaterialEditText) findViewById(R.id.clientEmailEditText);
        clientAddressEditText = (MaterialEditText) findViewById(R.id.clientAddressEditText);
        clientCityEditText = (MaterialEditText) findViewById(R.id.clientCityEditText);
        clientCountryEditText = (MaterialEditText) findViewById(R.id.clientCountryEditText);

        //Getting the buttons
        resetButton = (Button) findViewById(R.id.resetButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        //Adding Listener to reset Buttons

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientNameEditText.setText(null);
                clientIDEditText.setText(null);
                clientContactEditText.setText(null);
                clientEmailEditText.setText(null);
                clientAddressEditText.setText(null);
                clientCityEditText.setText(null);
                clientCountryEditText.setText(null);
                clientCategoryBetterSpinner.setText(null);

                //Need to clear focus from the spinner
                clientCategoryBetterSpinner.clearFocus();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TakeOrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "DemoMessage");
                startActivity(intent);
            }
        });
    }


}
