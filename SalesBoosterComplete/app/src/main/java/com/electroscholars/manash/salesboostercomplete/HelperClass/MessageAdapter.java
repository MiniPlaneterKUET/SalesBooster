package com.electroscholars.manash.salesboostercomplete.HelperClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.electroscholars.manash.salesboostercomplete.R;

/**
 * Created by Manash on 5/16/2016.
 */
public class MessageAdapter extends BaseAdapter {
    private Context context;
    private String[] headLines;
    private String[] content;
    private String[] date;

    private static LayoutInflater inflater = null;

    public MessageAdapter(Context context, String[] headLines, String[] content, String[] date){
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
        protected TextView messageHeadLineTextView;
        protected TextView messageContentTextView;
        protected TextView messageDateTextView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        Holder messageHolder = new Holder();
        View messageView;

        messageView = inflater.inflate(R.layout.message_layout, null);
        messageHolder.messageHeadLineTextView = (TextView) messageView.findViewById(R.id.messageHeadLineTextView);
        messageHolder.messageContentTextView = (TextView) messageView.findViewById(R.id.messageContentTextView);
        messageHolder.messageDateTextView = (TextView) messageView.findViewById(R.id.messageDateTextView);

        messageHolder.messageHeadLineTextView.setText(headLines[position]);
        messageHolder.messageContentTextView.setText(content[position]);
        messageHolder.messageDateTextView.setText(date[position]);

        return messageView;
    }
}
