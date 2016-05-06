package com.electroscholars.manash.takeorderclient.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.electroscholars.manash.takeorderclient.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderSubmissionActivity extends AppCompatActivity {

    private Calendar calendar;
    private DatePickerDialog chequeDatePickerDialog;
    private MaterialEditText chequeDate;
    private SimpleDateFormat dateFormat;

    private int day, month, year;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            setDate(day, month, year);
        }
    };

    public void setDate(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submission);

        //Initializing
        chequeDate = (MaterialEditText) findViewById(R.id.chequeDateEditText);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        calendar = Calendar.getInstance();



        chequeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                DatePickerDialog datePickerDialog = new DatePickerDialog(OrderSubmissionActivity
                        .this, dateSetListener,
                        year, month, day);
                datePickerDialog.show();
            }
        });

    }
}
