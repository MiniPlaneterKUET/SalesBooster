package com.electroscholars.manash.takeorderview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dev.dworks.libs.astickyheader.SectionedGridAdapter;

public class TakeOrderMainActivity extends AppCompatActivity {


    public void createDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
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