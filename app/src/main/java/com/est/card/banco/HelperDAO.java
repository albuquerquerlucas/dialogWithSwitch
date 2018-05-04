package com.est.card.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.est.card.entity.Cidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 25/02/2018.
 */

public class HelperDAO {

    private static final String TAG_ERROR = "ERRO";

    private DataBase helper;
    private SQLiteDatabase db = null;

    public HelperDAO(Context ctx){
        this.helper = new DataBase(ctx);
    }

    public void insert(Cidade cidade){
        this.db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cidade", cidade.getCidade());
        cv.put("status", cidade.getStatus());
        cv.put("imagem", cidade.getImagem());
        this.db.insert("app", null, cv);
        this.db.close();
    }

    public void updateCidade(String nome, String status){
        this.db = helper.getReadableDatabase();
        String sql = "update app set status = " + "\'" + status + "\'" + " where cidade = " + "\'" + nome + "\'";
        this.db.execSQL(sql);
        this.db.close();
    }

    public void updateImagem(String nome, String imagem){
        this.db = helper.getReadableDatabase();
        String sql = "update app set imagem = " + "\'" + imagem + "\'" + " where cidade = " + "\'" + nome + "\'";
        this.db.execSQL(sql);
        this.db.close();
    }

    public List<Cidade> getAllCidades(){

        List<Cidade> lista = new ArrayList<>();
        Cidade cidade;

        this.db = helper.getReadableDatabase();
        Cursor cursor = this.db.rawQuery("select * from app", null);

        try{
            if(cursor.moveToFirst()){
                do{
                    cidade = new Cidade(
                            cursor.getString(cursor.getColumnIndex("cidade")),
                            cursor.getString(cursor.getColumnIndex("status")),
                            cursor.getString(cursor.getColumnIndex("imagem"))
                    );
                    lista.add(cidade);
                }while (cursor.moveToNext());
                cursor.close();
                this.db.close();
            }
        }catch (Exception e){
            Log.e(TAG_ERROR, e.getMessage());
        }

        return lista;
    }

    public void deleteFiles(String cidade, String path){
        updateImagem(cidade, "");
        DataBase.deleteFiles(path);
    }
}
