package com.electroscholars.manash.salesboostercomplete.News;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.electroscholars.manash.salesboostercomplete.HelperClass.NewsAdapter;
import com.electroscholars.manash.salesboostercomplete.R;

public class NewsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_news);
        newsListView = (ListView) findViewById(R.id.newsListView);
        newsListView.setAdapter(new NewsAdapter(NewsActivity.this, headLines, content, date));

        newsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewsActivity
                        .this);
                alertDialogBuilder.setTitle("Selected News");
                TextView title = (TextView) view.findViewById(R.id.newsHeadlineTextView);
                alertDialogBuilder.setMessage(title.getText().toString());

                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
                return false;
            }
        });
    }
}
