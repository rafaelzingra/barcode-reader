package com.google.android.gms.samples.vision.barcodereader.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cleber T. Moreira on 19/03/2016.
 */
public class CidadeDao {
    private SQLiteDatabase conn;

    public CidadeDao(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void adicionarCidades(Cidade cidade){

        ContentValues contentValues = new ContentValues();
        contentValues.put("CIDADE", cidade.getCidade());
        contentValues.put("COD_ESTADO", cidade.getCodEstado());
        conn.insertOrThrow("CIDADE", null, contentValues);

    }

    public ArrayList<Cidade> buscraCidades(){
        ArrayList<Cidade> cidades = new ArrayList<Cidade>();

        Cursor cursor = conn.query("CIDADE", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Cidade cidade = new Cidade();
                cidade.setId(cursor.getInt(0));
                cidade.setCidade(cursor.getString(1));
                cidade.setCodEstado(cursor.getInt(2));

                cidades.add(cidade);

            }while (cursor.moveToNext());

        }

        return cidades;
    }
}
