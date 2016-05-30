package com.google.android.gms.samples.vision.barcodereader.control;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.samples.vision.barcodereader.model.Estado;

import java.util.ArrayList;

/**
 * Created by Cleber T. Moreira on 15/03/2016.
 */
public class EstadoDao {

    private SQLiteDatabase conn;

    public EstadoDao(SQLiteDatabase conn){

        this.conn = conn;
    }

    public void adicionarEstado(Estado estado){

        ContentValues contentValues = new ContentValues();
        contentValues.put("ESTADO", estado.getEstado());
        contentValues.put("SIGLA", estado.getSigla());
        conn.insertOrThrow("ESTADO", null, contentValues);

    }

    public ArrayList<Estado> buscraEstados(){
        ArrayList<Estado> estados = new ArrayList<Estado>();

        Cursor cursor = conn.rawQuery("select * from estado", null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Estado estado = new Estado();
                estado.set_id(cursor.getInt(0));
                estado.setEstado(cursor.getString(1));
                estado.setSigla(cursor.getString(2));

                estados.add(estado);

            }while (cursor.moveToNext());

        }

        return estados;
    }
}
