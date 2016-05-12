package com.example.rubelahmed.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView newsListView;

    public static String[] headlines = {"C (programming language)", "C++ (programming language)",
            "JAVA (programming language)", "PHP: Hypertext Preprocessor"};
    public static String[] content = {"C was originally developed by Dennis Ritchie between 1969 and 1973",
            "C++ is a middle-level programming language developed by Bjarne Stroustrup",
            "Java is a general-purpose computer programming language that is concurrent, class-based," +
                    " object-oriented, and specifically designed ", "Server-side HTML embedded scripting language. " +
            "It provides web developers with a full suite of tools for building dynamic websites"};
    public static String[] date = {"29/10/1994", "20/12/2015", "19/1/1995", "11/11/2011"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsListView = (ListView) findViewById(R.id.newsListView);
        newsListView.setAdapter(new NewsAdapter(MainActivity.this, headlines, content, date));
    }
}
