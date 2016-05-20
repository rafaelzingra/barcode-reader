package com.google.android.gms.samples.vision.barcodereader.dataBase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.samples.vision.barcodereader.MainActivity;
import com.google.android.gms.samples.vision.barcodereader.control.CervejaDao;
import com.google.android.gms.samples.vision.barcodereader.control.EstadoDao;
import com.google.android.gms.samples.vision.barcodereader.model.Cerveja;

/**
 * Created by Cleber T. Moreira on 12/03/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context){

        super(context, "BEER_FINDER", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptsSQL.criarTabelaCerveja());
        db.execSQL(ScriptsSQL.criarTabelaEstado());
        db.execSQL(ScriptsSQL.criarTabelaCidade());
        db.execSQL(ScriptsSQL.criarTabelaEstabelecimento());
        db.execSQL(ScriptsSQL.criarTabelaPreco());
        db.execSQL(ScriptsSQL.criarTrigger());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
