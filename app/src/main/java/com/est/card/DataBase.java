package com.est.card;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mrluke on 25/02/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String LOG_ERROR = "ERRO";
    private static final String NOME_DB = "app.db";
    private static final int VERSAO_DB = 1;

    public DataBase(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table app ( " +
                "id integer primary key autoincrement,  " +
                "cidade text," +
                "status text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS app");
    }
}
