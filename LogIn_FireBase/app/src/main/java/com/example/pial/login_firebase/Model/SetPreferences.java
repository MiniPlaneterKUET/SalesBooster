package com.example.pial.login_firebase.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Pial on 5/6/2016.
 */
public class SetPreferences {
    String key = "Status";
    String value;

    SharedPreferences preferences;
    Editor editor;

    public void setPreference(Context context, String value) {
        editor = context.getSharedPreferences("Remember_User", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }

    public String getPreference(Context context) {
        preferences = context.getSharedPreferences("Remember_User", Context.MODE_PRIVATE);
        String value = preferences.getString(key, "000");
        return value;

    }
}
