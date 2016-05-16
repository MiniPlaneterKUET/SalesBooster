package com.electroscholars.manash.salesboostercomplete.TakeOrder;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.electroscholars.manash.salesboostercomplete.HelperClass.Database.ItemDbHelper;
import com.electroscholars.manash.salesboostercomplete.R;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    private final static String ADD_ITEMS = "Add Items";

    private final static int GRIDVIEW_COLUMN = 5;

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

    private GridView headerGridView;

    private float totalPrice;
    private int itemRate;
    private int itemQuantity = 0;
    private float discount = 0;


    private String selectedItem;
    private String selectedItemPriceRate;
    private String selectedItemDiscount;
    private String selectedItemQuantity;
    private String selectedItemTotalPrice;

    boolean wasAddClicked = false;

    private ArrayList<String> itemDetailsList;
    private ArrayAdapter<String> itemDetailsArrayAdapter;

    //Proceed button
    private Button proceedButton;

    private int row;

    //Updates discount
    public void updateDiscount(){

        if (!discountEditText.getText().toString().isEmpty()) {

            discount = totalPrice * Float.parseFloat(discountEditText.getText().toString()) / (float) 100.0;
            int discountPrice = (int) (totalPrice - discount);
            totalPrice = discountPrice;
            totalPriceEditText.setText(String.valueOf(discountPrice));
        } else {
            discount = (float) 0.0;
        }
    }

    //Updates price
    public void updatePrice(){
        if (!qtyEditText.getText().toString().isEmpty()){
            totalQtyEditText.setText(qtyEditText.getText().toString());
            itemRate = Integer.valueOf(rateEditText.getText().toString());
            totalPrice = (float) itemRate * Integer.valueOf(qtyEditText.getText().toString());
            updateDiscount();
            totalPriceEditText.setText(String.valueOf(totalPrice));
            Log.d("totalprice", String.valueOf(totalPrice));
        } else {
            totalQtyEditText.setText("0");
        }
    }


    //Creates the custom AlertDialog box
    public void createDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        wasAddClicked = false;

        //When Add Button is clicked
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddItemActivity.this, "Add was clicked,", Toast
                        .LENGTH_SHORT).show();
                wasAddClicked = true;
                selectedItemPriceRate = rateEditText.getText().toString();
                selectedItemDiscount = discountEditText.getText().toString();
                selectedItemQuantity = qtyEditText.getText().toString();
                selectedItemTotalPrice = totalPriceEditText.getText().toString();

                String[] itemDetails = {selectedItem, selectedItemPriceRate,
                        selectedItemDiscount, selectedItemQuantity, selectedItemTotalPrice};

                //Replaces previous values with the current one [checks for item name]
                if (itemDetailsList.contains(selectedItem)){
                    int index = itemDetailsList.indexOf(selectedItem);
                    itemDetailsList.set(index + 2, selectedItemDiscount);
                    itemDetailsList.set(index + 3, selectedItemQuantity);
                    itemDetailsList.set(index + 4, selectedItemTotalPrice);
                }

                else {

                    for (String value : itemDetails) {
                        itemDetailsList.add(value);
                    }
                }

                gridView.setAdapter(itemDetailsArrayAdapter);

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
                    Toast.makeText(AddItemActivity.this, "Nothing was added!", Toast.LENGTH_SHORT)
                            .show();
            }
        });


        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_item_dialog, null);
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

                selectedItem = select;

                rateEditText.setText(getPrice(select));
                Toast.makeText(AddItemActivity.this, spinner.getItemAtPosition(i).toString(), Toast
                        .LENGTH_SHORT).show();

                //Updates fields on change of items
                totalQtyEditText.setText(qtyEditText.getText().toString());
                itemRate = Integer.valueOf(rateEditText.getText().toString());
                totalPrice = (float) itemRate * Integer.valueOf(qtyEditText.getText().toString());
                totalPriceEditText.setText(String.valueOf(totalPrice));
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
                updatePrice();
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
                updatePrice();

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


    //Adapter for gridview
    //TODO: Need to adapt for Item Name, item price rate, quantity, discount
    public class TextViewAdapter extends BaseAdapter {
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
            } else {
                textView = (TextView) convertView;
            }

            textView.setTextSize(17);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setText(strings.get(position));
            textView.setTextColor(Color.DKGRAY);
            textView.setShadowLayer(2, 4, 4, Color.LTGRAY);
            textView.setPadding(0, 0, 0, 30);
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
        setContentView(R.layout.activity_add_item);

        //Gets the toolbar and sets title
        Toolbar toolbar = (Toolbar) findViewById(R.id.addItemToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ADD_ITEMS);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                createDialog();
            }
        });



        gridView = (GridView) findViewById(R.id.gridView);

        //GridView for headers
        headerGridView = (GridView) findViewById(R.id.headerGridView);


        adapter = new TextViewAdapter(this);

        String[] headerItems = {"Item", "CPI", "Disc", "Qty", "Total"};



        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout
                .item_spinner_layout, headerItems);

        headerGridView.setAdapter(stringArrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("onItemClick","i: " + String.valueOf(i) + "       ");
                Log.d("onItemClick", "l: " + String.valueOf(l) + "      ");
            }
        });

        itemDetailsList = new ArrayList<>();

        itemDetailsArrayAdapter = new ArrayAdapter<String>(this, R.layout.item_grid_layout_text,
                itemDetailsList);

        proceedButton = (Button) findViewById(R.id.proceedButton);

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddItemActivity.this, "Proceed Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //Delete item on long click, shows a AlertDialog to verify
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Gets row from index
                row = (int) Math.floor(i / GRIDVIEW_COLUMN + 1);

                AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder
                        (AddItemActivity.this);

                confirmDialogBuilder.setTitle("Do you want to delete row " + String.valueOf(row)
                        + " ?");
                confirmDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int removeFromIndex = (row - 1) * GRIDVIEW_COLUMN;
                        for (int j = 0; j < GRIDVIEW_COLUMN; j++){
                            itemDetailsList.remove(removeFromIndex);
                        }
                        gridView.setAdapter(itemDetailsArrayAdapter);
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
                return false;
            }
        });

    }


}
