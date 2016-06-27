package com.google.android.gms.samples.vision.barcodereader.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cleber T. Moreira on 12/03/2016.
 */
public class CervejaDao {

    private SQLiteDatabase conn;

    public CervejaDao(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void adicionarCerveja(Cerveja cerveja){

        ContentValues contentValues = new ContentValues();
        contentValues.put("BARCODE", cerveja.getBarcode());
        contentValues.put("MARCA", cerveja.getMarca());
        contentValues.put("ROTULO", cerveja.getRotulo());
        conn.insertOrThrow("CERVEJA", null, contentValues);

    }

    public ArrayList<Cerveja> buscraCervejas(){
        ArrayList<Cerveja> cervejas = new ArrayList<Cerveja>();

        Cursor cursor = conn.query("CERVEJA", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Cerveja cerveja = new Cerveja();
                cerveja.set_id(cursor.getInt(0));
                cerveja.setBarcode(cursor.getString(1));
                cerveja.setMarca(cursor.getString(2));
                cerveja.setRotulo(cursor.getString(3));

                cervejas.add(cerveja);

            }while (cursor.moveToNext());

        }

        return cervejas;
    }

    public Cerveja getCerveja(String barcode) {
        Cerveja cerveja = new Cerveja();
        Cursor aux = null;
        try {
            aux = conn.rawQuery("select * from cerveja where barcode='" + barcode + "'", null);
            aux.moveToFirst();
            while (aux.moveToNext()) {
                cerveja.setBarcode(barcode);
                cerveja.setRotulo(aux.getString(2));
                cerveja.setMarca(aux.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cerveja;

    }

    public Cursor getCodigo() {
        Cursor getCodigo = null;
        try {
            getCodigo = conn.rawQuery("select max(_id)from cerveja", null);
            getCodigo.moveToFirst();
            int codigo = getCodigo.getInt(0);
            System.out.println("codigo:" + codigo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getCodigo/*.getColumnIndex("_id")*/;
    }
}
