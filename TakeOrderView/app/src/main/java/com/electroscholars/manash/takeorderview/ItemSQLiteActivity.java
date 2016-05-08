package com.electroscholars.manash.takeorderview;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemSQLiteActivity extends ListActivity {

    private SQLiteDatabase itemDb;
    private ItemDbHelper itemDbHelper;

    public EditText addItemEditText;
    public EditText addItemPriceEditText;

    private Button addNewItem;
    private Button getItemInfo;

    String addItemName;
    Integer addItemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_sqlite);

        itemDbHelper = new ItemDbHelper(this);
        itemDb = itemDbHelper.getWritableDatabase();

        addNewItem = (Button) findViewById(R.id.addNewItem);
        getItemInfo = (Button) findViewById(R.id.getItemInfo);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        getItemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemInfo();
            }
        });

        displayItems();


    }



    public List<String> getItems(){
        List<String> items = new ArrayList<>();

        Cursor cursor = itemDb.query(itemDbHelper.TABLE_NAME, itemDbHelper.TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String itemName = cursor.getString(itemDbHelper.COLNO_ITEM_NAME);
            items.add(itemName);
            cursor.moveToNext();
        }

        cursor.close();
        return items;
    }

    public void displayItems(){
        List<String> itemEntries = getItems();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemEntries);
        setListAdapter(stringArrayAdapter);
    }

    public void getItemInfo(){
        Cursor cursor = itemDb.rawQuery("select _id, item_name, item_price " + "from items", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            String item = cursor.getString(itemDbHelper.COLNO_ITEM_NAME);
            Integer price = cursor.getInt(itemDbHelper.COLNO_ITEM_PRICE);
            Toast.makeText(this, "The " + item + " price is " + String.valueOf(price), Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }

        cursor.close();
    }

    private void addItem(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View addView = layoutInflater.inflate(R.layout.dialog_layout, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        addItemEditText = (EditText) addView.findViewById(R.id.addItemNameEditText);
        addItemPriceEditText = (EditText) addView.findViewById(R.id.addItemPriceEditText);

        dialogBuilder.setView(addView);

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("addItem", addItemEditText.getText().toString());
                Log.i("addItem", addItemPriceEditText.getText().toString());
                addItemName = addItemEditText.getText().toString();
                addItemPrice = Integer.valueOf(addItemPriceEditText.getText().toString());

                addItemToDatabase(addItemName, addItemPrice);
                displayItems();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "No Item was added", Toast.LENGTH_LONG).show();
            }
        });

        dialogBuilder.setTitle("Add Item Name and Price");

        AlertDialog addDialog = dialogBuilder.create();

        addDialog.show();

    }

    public void addItemToDatabase(String item, int price){

        ContentValues values = new ContentValues(2);

        values.put("item_name", item);
        values.put("item_price", price);

        itemDb.insert(itemDbHelper.TABLE_NAME, "item_name" ,
                values);



    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        itemDb.close();
    }

}
