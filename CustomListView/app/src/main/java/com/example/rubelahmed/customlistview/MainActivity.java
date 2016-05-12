package com.example.rubelahmed.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView newsListView;


    public static String[] date = {"29th, Dec 1994", "29th, Dec 1994", "29th, Dec 1994" , "29th, " +
            "Dec 1994" , "29th, Dec 1994", "29th, Dec 1994"};

    public static String[] headLines = {"New Item Added",
                                        "Item Price Updated",
                                        "Item Price Reduced",
                                        "Item sold out",
                                        "Delivery will be delayed",
                                        "Meeting at 2 PM"

    };

    public static String[] content  = {"A new item was added to the inventory",
                                    "Item of the price has been updated",
                                    "item of the price has been reduced",
                                    "Item sold out!",
                                    "Upcoming delivery to this shop will be delayed",
                                    "All representatives are requested to come to the meeting at " +
                                            "2 PM"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsListView = (ListView) findViewById(R.id.newsListView);
        newsListView.setAdapter(new NewsAdapter(MainActivity.this, headLines, content, date));
    }
}
