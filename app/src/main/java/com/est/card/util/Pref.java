package com.est.card.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mrluke on 25/02/2018.
 */

public class Pref {

    private final SharedPreferences mSharedPreferences;

    public Pref(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
    }

    public void grava(String key, String value){
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String recupera(String key){
        return this.mSharedPreferences.getString(key, "");
    }
}
