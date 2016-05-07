package com.example.pial.login_firebase.Main;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Manash on 5/3/2016.
 */
public class Typewriter extends TextView {

    private CharSequence text;
    private int index;
    private long delay = 500;

    public Typewriter(Context context){
        super(context);
    }

    public Typewriter(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    private Handler handler = new Handler();

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
