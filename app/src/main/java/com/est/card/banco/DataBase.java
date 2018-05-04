package com.est.card.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;

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
                "status text," +
                "imagem text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS app");
    }

    public static void deleteFiles(String path) {

        File file = new File(path);

        if (file.exists()) {
            String deleteCmd = "rm -r " + path;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
            } catch (IOException e) {
                Log.e(LOG_ERROR, e.getMessage()); }
        }
    }
}
