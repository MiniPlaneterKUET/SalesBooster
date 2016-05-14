package com.electroscholars.manash.salesboostercomplete.HelperClass;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Manash on 5/14/2016.
 *
 * Original Author: Pial Kanti
 */
public class SetPreferences {
    private String key = "Status";
    private String value;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void setPreference(Context context, String value){
        editor = context.getSharedPreferences("Remember_user", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreference(Context context){
        sharedPreferences = context.getSharedPreferences("Remember_user", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "000");
        return value;
    }
}
