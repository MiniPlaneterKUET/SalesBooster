package com.electroscholars.manash.takeorderclient.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;

import com.electroscholars.manash.takeorderclient.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderSubmissionActivity extends AppCompatActivity {

    private static final int DATE_DIALOG_ID = 999;

    private Calendar calendar;
    private DatePickerDialog chequeDatePickerDialog;
    private MaterialEditText chequeDateEditText;
    private SimpleDateFormat dateFormat;

    //It is required to prevent the datepicker from appearing multiple times
    private boolean isChequeDatePickerOpened = false;

    //Just in case
    protected static int day;
    protected static int month;
    protected static int year;


    class ChequeDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            OrderSubmissionActivity.month = month;
            OrderSubmissionActivity.year = year;
            OrderSubmissionActivity.day = day;
            chequeDateEditText.setText(String.valueOf(day) + " - " + String.valueOf(month + 1) + " - " + String.valueOf(year));
            isChequeDatePickerOpened = false;

        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submission);

        //Initializing
        chequeDateEditText = (MaterialEditText) findViewById(R.id.chequeDateEditText);


        //If ChequeEditText is clicked then a datepicker would pop up
        chequeDateEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if (!isChequeDatePickerOpened){
                    isChequeDatePickerOpened = true;
                    DatePickerDialog dialog = new DatePickerDialog(OrderSubmissionActivity.this, new ChequeDateSetListener(), year, month, day);
                    dialog.show();
                }
                return false;
            }
        });





    }
}
