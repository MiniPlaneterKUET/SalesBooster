package com.electroscholars.manash.takeorderview;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dev.dworks.libs.astickyheader.SectionedGridAdapter;

public class TakeOrderMainActivity extends AppCompatActivity {

    private ItemDbHelper itemDatabaseHelper;
    private SQLiteDatabase itemDatabase;

    //AlertDialog Fields
    private EditText rateEditText;
    private EditText qtyEditText;
    private EditText totalPriceEditText;
    private EditText totalQtyEditText;
    private ImageButton incrementButton;
    private ImageButton decrementButton;
    private Button qtyClearButton;

    public void createDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);


        //When Add Button is clicked
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //When cancel button is clicked
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner);



        rateEditText = (EditText) dialogView.findViewById(R.id.rateEditText);
        qtyEditText = (EditText) dialogView.findViewById(R.id.qtyEditText);
        totalPriceEditText = (EditText) dialogView.findViewById(R.id.totalPriceEditText);
        totalQtyEditText = (EditText) dialogView.findViewById(R.id.totalQtyEditText);
        incrementButton = (ImageButton) dialogView.findViewById(R.id.incrementButton);
        decrementButton = (ImageButton) dialogView.findViewById(R.id.decrementButton);
        qtyClearButton = (Button) dialogView.findViewById(R.id.qtyClearButton);


        qtyClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyEditText.setText("");
                qtyEditText.append("0");
            }
        });

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qtyEditText.getText().toString().isEmpty()){
                    qtyEditText.setText("");
                    qtyEditText.append("0");
                } else {
                    int value = Integer.valueOf(qtyEditText.getText().toString());
                    value += 1;
                    qtyEditText.setText("");
                    qtyEditText.append(String.valueOf(value));
                }
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qtyEditText.getText().toString().isEmpty()){
                    qtyEditText.setText("");
                    qtyEditText.append("0");
                } else {
                    int value = Integer.valueOf(qtyEditText.getText().toString());
                    if (value > 0) value -= 1;
                    else value = 0;
                    qtyEditText.setText("");
                    qtyEditText.append(String.valueOf(value));
                }
            }
        });

        rateEditText.setText("12345689");




        itemDatabaseHelper = new ItemDbHelper(this);
         itemDatabase = itemDatabaseHelper.getWritableDatabase();

        List<String> items = new ArrayList<>();

        Cursor cursor = itemDatabase.query(itemDatabaseHelper.TABLE_NAME, itemDatabaseHelper.TABLE_COLUMNS, null,
                null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String itemName = cursor.getString(itemDatabaseHelper.COLNO_ITEM_NAME);
            items.add(itemName);
            cursor.moveToNext();
        }

        cursor.close();

        String[] itemArray = items.toArray(new String[items.size()]);

        String[] hello = {"Hello", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World", "World"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter <> (this, android.R.layout
                .simple_spinner_dropdown_item, itemArray);


        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = spinner.getItemAtPosition(i).toString();

                Toast.makeText(TakeOrderMainActivity.this, spinner.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        alertDialog.setTitle("Add New Item");

        alertDialog.show();
    }





    public class TextViewAdapter extends BaseAdapter{
        public ArrayList<Integer> id;

        private Context context;

        public TextViewAdapter(Context c){
            context = c;
            id = new ArrayList<>();
            strings = new ArrayList<>();
        }

        public int getCount(){
//            return textId.length;
            return strings.size();
        }


        public long getItemId(int position){
            return 0;
        }

        public Object getItem(int position){
            return null;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            TextView textView;

            if (convertView == null){
                textView = new TextView(context);
                textView.setLayoutParams(new GridView.LayoutParams(100, 100));
            } else {
                textView = (TextView) convertView;
            }

            textView.setText(strings.get(position));
            textView.setTextColor(Color.RED);
            return textView;
        }


        public ArrayList<String> strings;


        public void addItem(String item){
            strings.add(item);
        }


    }

    private GridView gridView;
    private RelativeLayout relativeLayout;
    private TextViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                adapter.addItem("Hello World");
                gridView.setAdapter(adapter);
                createDialog();
            }
        });

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new TextViewAdapter(this);
        gridView.setAdapter(adapter);

//        Intent intent = new Intent(this, ItemSQLiteActivity.class);
//        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_take_order_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}