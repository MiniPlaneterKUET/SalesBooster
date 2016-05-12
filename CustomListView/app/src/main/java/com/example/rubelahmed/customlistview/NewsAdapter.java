package com.example.rubelahmed.customlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Manash on 5/13/2016.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private String[] headLines;
    private String[] content;
    private String[] date;

    private static LayoutInflater inflater = null;

    public NewsAdapter(Context context, String[] headLines, String[] content, String[] date){
        this.headLines = headLines;
        this.context = context;
        this.content = content;
        this.date = date;

        inflater = LayoutInflater.from(context);
    }

    public Object getItem(int position){
        return position;
    }

    public int getCount(){
        return headLines.length;
    }

    public long getItemId(int position){
        return Long.valueOf(position);
    }

    protected static class Holder{
        protected TextView headLineTextView;
        protected TextView contentTextView;
        protected TextView dateTextView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        Holder newsHolder = new Holder();
        View newsView;

        newsView = inflater.inflate(R.layout.news_layout, null);
        newsHolder.headLineTextView = (TextView) newsView.findViewById(R.id.newsHeadlineTextView);
        newsHolder.contentTextView = (TextView) newsView.findViewById(R.id.newsContentTextView);
        newsHolder.dateTextView = (TextView) newsView.findViewById(R.id.dateTextView);

        newsHolder.headLineTextView.setText(headLines[position]);
        newsHolder.contentTextView.setText(content[position]);
        newsHolder.dateTextView.setText(date[position]);

        return newsView;
    }
}
