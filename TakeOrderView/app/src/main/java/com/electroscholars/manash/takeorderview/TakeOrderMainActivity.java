package com.electroscholars.manash.takeorderview;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
import android.database.DatabaseUtils;

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

    private EditText discountEditText;

    private ImageButton incrementButton;
    private ImageButton decrementButton;
    private Button qtyClearButton;
    private Button discountClearButton;

    private float totalPrice;
    private int itemRate;
    private int itemQuantity = 0;
    private float discount = 0;

    boolean wasAddClicked = false;

    //Returns integer
    public static Integer getInt(String number){
        if (number.isEmpty())
            return null;
        return Integer.valueOf(number);
    }

    //Returns integer from edittext [checks for null stings]
    public static Integer getIntFromEditText(EditText editText){
        if (!editText.getText().toString().isEmpty())
            return getInt(editText.getText().toString());
        return null;
    }


    //Creates the custom AlertDialog box
    public void createDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        wasAddClicked = false;

        //When Add Button is clicked
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TakeOrderMainActivity.this, "Add was clicked,", Toast
                        .LENGTH_SHORT).show();
                wasAddClicked = true;
            }
        });

        //When cancel button is clicked
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                wasAddClicked = false;
            }
        });

        dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (!wasAddClicked)
                    Toast.makeText(TakeOrderMainActivity.this, "Nothing was added!", Toast.LENGTH_SHORT)
                        .show();
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
        discountClearButton = (Button) dialogView.findViewById(R.id.discountClearButton);
        discountEditText = (EditText) dialogView.findViewById(R.id.discountEditText);

        //Adds a 0 on beginning discountEditText
        discountEditText.setText("");
        discountEditText.append("0");

        //Adds a 0 on editText
        qtyClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyEditText.setText("");
                qtyEditText.append("0");
            }
        });

        //Adds a 0 on discountEditText
        discountClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discountEditText.setText("");
                discountEditText.append("0");
            }
        });

        //Increases current value by 1
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

        //Decreases current value by 1
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


        //Database workaround
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

        //Converts StringArray to regular String Array
        String[] itemArray = items.toArray(new String[items.size()]);

        //Array Adapter for spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter <> (this, android.R.layout
                .simple_spinner_dropdown_item, itemArray);


        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = spinner.getItemAtPosition(i).toString();
//                Log.d("itemname", getPrice(select));
                rateEditText.setText(getPrice(select));
                Toast.makeText(TakeOrderMainActivity.this, spinner.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Change the price when quantity is changed
        //Listens to the change in qty
        qtyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().isEmpty()){
                    itemRate = Integer.valueOf(rateEditText.getText().toString());
                    totalPrice = (float) itemRate * Integer.valueOf(qtyEditText.getText().toString());
                    totalPriceEditText.setText(String.valueOf(totalPrice));
                    Log.d("totalprice", String.valueOf(totalPrice));
                }
            }
        });

        //Listens to the change in DiscountEditText
        discountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("discountEditText", editable.toString());

                if (!editable.toString().isEmpty()) {

                    discount = totalPrice * Float.parseFloat(editable.toString()) / (float) 100.0;

                    Log.d("discount", String.valueOf(discount));
                } else {
                    discount = (float) 0.0;
                }

            }
        });


        alertDialog.setTitle("Add New Item");
        alertDialog.show();
    }


    public String getPrice(String itemName){

        String command = "SELECT item_price FROM 'items' WHERE item_name LIKE " + "'" +
                itemName + "'";

        Cursor c = itemDatabase.rawQuery(command, null);

        int price = 0;

        c.moveToFirst();

        price = c.getInt(0);

        Log.d("dump", String.valueOf(price));
        return String.valueOf(price);
    }


    //clears all fields
    private void clearAll(){

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