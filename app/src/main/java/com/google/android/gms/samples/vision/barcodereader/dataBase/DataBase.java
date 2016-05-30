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
        db.execSQL("CREATE TABLE IF NOT EXISTS ESTADO(" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "ESTADO VARCHAR(40) NOT NULL," +
                "SIGLA VARCHAR(4) NOT NULL)");

        db.execSQL("INSERT INTO ESTADO VALUES(1, 'Acre', 'AC')");
        db.execSQL("INSERT INTO ESTADO VALUES(2, 'Alagoas', 'AL')");
        db.execSQL("INSERT INTO ESTADO VALUES(3, 'Amazonas', 'AM')");
        db.execSQL("INSERT INTO ESTADO VALUES(4, 'Amapá', 'AP')");
        db.execSQL("INSERT INTO ESTADO VALUES(5, 'Bahia', 'BA')");
        db.execSQL("INSERT INTO ESTADO VALUES(6, 'Ceará', 'CE')");
        db.execSQL("INSERT INTO ESTADO VALUES(7, 'Distrito Federal', 'DF')");
        db.execSQL("INSERT INTO ESTADO VALUES(8, 'Espírito Santo', 'ES')");
        db.execSQL("INSERT INTO ESTADO VALUES(9, 'Goiás', 'GO')");
        db.execSQL("INSERT INTO ESTADO VALUES(10, 'Maranhão', 'MA')");
        db.execSQL("INSERT INTO ESTADO VALUES(11, 'Minas Gerais', 'MG')");
        db.execSQL("INSERT INTO ESTADO VALUES(12, 'Mato Grosso do Sul', 'MS')");
        db.execSQL("INSERT INTO ESTADO VALUES(13, 'Mato Grosso', 'MT')");
        db.execSQL("INSERT INTO ESTADO VALUES(14, 'Pará', 'PA')");
        db.execSQL("INSERT INTO ESTADO VALUES(15, 'Paraíba', 'PB')");
        db.execSQL("INSERT INTO ESTADO VALUES(16, 'Pernambuco', 'PE')");
        db.execSQL("INSERT INTO ESTADO VALUES(17, 'Piauí', 'PI')");
        db.execSQL("INSERT INTO ESTADO VALUES(18, 'Paraná', 'PR')");
        db.execSQL("INSERT INTO ESTADO VALUES(19, 'Rio de Janeiro', 'RJ')");
        db.execSQL("INSERT INTO ESTADO VALUES(20, 'Rio Grande do Norte', 'RN')");
        db.execSQL("INSERT INTO ESTADO VALUES(21, 'Rondônia', 'RO')");
        db.execSQL("INSERT INTO ESTADO VALUES(22, 'Roraima', 'RR')");
        db.execSQL("INSERT INTO ESTADO VALUES(23, 'Rio Grande do Sul', 'RS')");
        db.execSQL("INSERT INTO ESTADO VALUES(24, 'Santa Catarina', 'SC')");
        db.execSQL("INSERT INTO ESTADO VALUES(25, 'Sergipe', 'SE')");
        db.execSQL("INSERT INTO ESTADO VALUES(26, 'Sao Paulo', 'SP')");
        db.execSQL("INSERT INTO ESTADO VALUES(27, 'Tocantins', 'TO')");

        db.execSQL("CREATE TABLE IF NOT EXISTS CIDADE( " +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "CIDADE VARCHAR(40) NOT NULL, " +
                "COD_ESTADO   INTEGER NOT NULL) ");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
