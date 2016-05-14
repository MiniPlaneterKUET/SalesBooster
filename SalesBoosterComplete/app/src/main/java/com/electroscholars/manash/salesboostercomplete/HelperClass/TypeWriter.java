package com.electroscholars.manash.salesboostercomplete.HelperClass;

import android.content.Context;
import android.os.Handler;
import android.renderscript.Type;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Manash on 5/14/2016.
 */
public class TypeWriter extends TextView{

    private CharSequence text;
    private int index;
    private long delay = 500;

    private Handler handler = new Handler();

    public TypeWriter(Context context){
        super(context);
    }

    public TypeWriter(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, index++));
            if (index <= text.length()){
                handler.postDelayed(characterAdder, delay);
            }
        }
    };

    public void animateText(CharSequence text){
        this.text = text;
        index = 0;
        setText("");
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder, delay);
    }

    public void setCharacterDelay(long millis){
        delay = millis;
    }

}
