package com.electroscholars.manash.takeorderclient.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import com.electroscholars.manash.takeorderclient.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderSubmissionActivity extends AppCompatActivity {


    private MaterialEditText chequeDateEditText;
    private MaterialEditText deliveryDateEditText;

    //It is required to prevent the datepicker from appearing multiple times
    private boolean isChequeDatePickerOpened = false;
    private boolean isDeliveryDatePickerOpened = false;

    private boolean isChequeDatePicked = false;

    private String chequeDate = "";


    class ChequeDateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            isChequeDatePickerOpened = false;
            isChequeDatePicked = true;
            chequeDate = String.valueOf(day) + " - " + String.valueOf(month) + " - " + String.valueOf(year);
        }
    }

    class ChequeOnDismissListener implements DialogInterface.OnDismissListener{
        @Override
        public void onDismiss(DialogInterface dialog){
            if (isChequeDatePicked){
                chequeDateEditText.setText(chequeDate);
                isChequeDatePicked = false;
                isChequeDatePickerOpened = false;
            } else {
                chequeDateEditText.setText(null);
                isChequeDatePickerOpened = false;
            }
        }
    }


    class DeliveryDateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){

        }
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submission);

        //Initializing
        chequeDateEditText = (MaterialEditText) findViewById(R.id.chequeDateEditText);
        deliveryDateEditText = (MaterialEditText) findViewById(R.id.deliveryDateEditText);

        //If ChequeEditText is touhced then a datepicker would pop up
        chequeDateEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if (!isChequeDatePickerOpened){

                    isChequeDatePicked = false;
                    isChequeDatePickerOpened = true;

                    DatePickerDialog dialog = new DatePickerDialog(OrderSubmissionActivity.this,
                            new ChequeDateListener(), year, month, day);
                    dialog.setOnDismissListener(new ChequeOnDismissListener());
                    dialog.show();
                }
                return false;
            }
        });

        deliveryDateEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if (!isDeliveryDatePickerOpened){
                    isDeliveryDatePickerOpened = true;
                    DatePickerDialog dialog = new DatePickerDialog(OrderSubmissionActivity.this,
                            new DeliveryDateListener(), year, month, day);
                    dialog.show();
                }
                return false;
            }
        });



    }
}
