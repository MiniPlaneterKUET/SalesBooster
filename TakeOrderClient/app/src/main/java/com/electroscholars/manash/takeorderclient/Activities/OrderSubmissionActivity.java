package com.electroscholars.manash.takeorderclient.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;

import com.electroscholars.manash.takeorderclient.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

public class OrderSubmissionActivity extends AppCompatActivity {

    //Required fields
    private MaterialEditText chequeDateEditText;
    private MaterialEditText deliveryDateEditText;

    //It is required to prevent the datepicker from appearing multiple times
    private boolean isChequeDatePickerOpened = false;
    private boolean isDeliveryDatePickerOpened = false;

    //If the datepicker is cancelled these flags will handle the issue
    private boolean isChequeDatePicked = false;
    private boolean isDeliveryDatePicked = false;

    //Strings for handling the dates in strings
    //TODO:
    //1. Add DateFormatter class to handle it
    private String chequeDate = "";
    private String deliveryDate = "";

    //If date is picked a chequeDatePicked flag will be set in order to transfer the date setting
    // workaround to OnDismissListener
    class ChequeDateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            isChequeDatePickerOpened = false;
            isChequeDatePicked = true;
            chequeDate = String.valueOf(day) + " - " + String.valueOf(month) + " - " + String.valueOf(year);
        }
    }

    //Handling the datepicker cancel events
    class ChequeOnDismissListener implements DialogInterface.OnDismissListener {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (isChequeDatePicked) {
                chequeDateEditText.setText(chequeDate);
                isChequeDatePicked = false;
                isChequeDatePickerOpened = false;
            } else {
                chequeDateEditText.setText(null);
                isChequeDatePickerOpened = false;
            }
        }
    }

    //Same as chequeDateListener
    class DeliveryDateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            isDeliveryDatePickerOpened = false;
            isDeliveryDatePicked = true;
            deliveryDate = String.valueOf(day) + " - " + String.valueOf(month) + " - " + String
                    .valueOf(year);
        }
    }


    //Same as ChequeOnDismiss
    class DeliveryOnDismissListener implements DialogInterface.OnDismissListener {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            if (isDeliveryDatePicked) {
                deliveryDateEditText.setText(deliveryDate);
                isDeliveryDatePicked = false;
                isDeliveryDatePickerOpened = false;
            } else {
                deliveryDateEditText.setText(null);
                isDeliveryDatePickerOpened = false;
            }
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submission);

        //Initializing
        chequeDateEditText = (MaterialEditText) findViewById(R.id.chequeDateEditText);
        deliveryDateEditText = (MaterialEditText) findViewById(R.id.deliveryDateEditText);

        //Preventing keypad from appearing
        chequeDateEditText.setFocusable(false);
        deliveryDateEditText.setFocusable(false);

        //Handling the long click events of chequeEditText
        chequeDateEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if (!isChequeDatePickerOpened) {
                    isChequeDatePicked = false; //Multiple touch issue solver flag
                    isChequeDatePickerOpened = true; // Cancel event issue solver flag

                    //Creating a datepickerdialog
                    DatePickerDialog dialog = new DatePickerDialog(OrderSubmissionActivity.this,
                            new ChequeDateListener(), year, month, day);

                    //Adding the dismisslistener
                    dialog.setOnDismissListener(new ChequeOnDismissListener());

                    //No comment needed
                    dialog.show();
                }
                return false;
            }
        });


        //Handling the long touch events of deliverydateEdittext
        deliveryDateEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if (!isDeliveryDatePickerOpened) {

                    isDeliveryDatePickerOpened = true;
                    isDeliveryDatePicked = false;

                    DatePickerDialog dialog = new DatePickerDialog(OrderSubmissionActivity.this,
                            new DeliveryDateListener(), year, month, day);
                    dialog.setOnDismissListener(new DeliveryOnDismissListener());
                    dialog.show();
                }
                return false;
            }
        });


    }
}
