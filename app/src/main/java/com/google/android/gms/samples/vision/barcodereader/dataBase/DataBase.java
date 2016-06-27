package com.google.android.gms.samples.vision.barcodereader.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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

        db.execSQL("INSERT INTO ESTADO VALUES(1, 'Sao Paulo', 'SP')");
        /*db.execSQL("INSERT INTO ESTADO VALUES(2, 'Alagoas', 'AL')");
        db.execSQL("INSERT INTO ESTADO VALUES(3, 'Amazonas', 'AM')");
        db.execSQL("INSERT INTO ESTADO VALUES(4, 'Amapá', 'AP')");
        db.execSQL("INSERT INTO ESTADO VALUES(5, 'Bahia', 'BA')");
        db.execSQL("INSERT INTO ESTADO VALUES(6, 'Ceará', 'CE')");
        db.execSQL("INSERT INTO ``ESTADO VALUES(7, 'Distrito Federal', 'DF')");
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
        db.execSQL("INSERT INTO ESTADO VALUES(27, 'Tocantins', 'TO')");*/

        db.execSQL("CREATE TABLE IF NOT EXISTS CIDADE( " +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "CIDADE VARCHAR(40) NOT NULL, " +
                "COD_ESTADO   INTEGER NOT NULL)");

        db.execSQL("INSERT INTO CIDADE VALUES(1, 'Campinas', 1)");
        db.execSQL("INSERT INTO CIDADE VALUES(2, 'Indaiatuba', 1)");


        db.execSQL("CREATE TABLE IF NOT EXISTS CERVEJA(" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "BARCODE VARCHAR(13)NOT NULL, " +
                "MARCA VARCHAR(20)NOT NULL, " +
                "ROTULO VARCHAR(20)NOT NULL)");

        db.execSQL("INSERT INTO CERVEJA VALUES(1, '7891027130664', 'Tilibra', 'Caderno');");
        db.execSQL("INSERT INTO CERVEJA VALUES(2, '7891149101900', 'Stella Artois', 'Stella Artois');");
        db.execSQL("INSERT INTO CERVEJA VALUES(3, '7896045504831', 'Amstel', 'Lager');");
        db.execSQL("INSERT INTO CERVEJA VALUES(4, '7897149106608', 'Skol', 'Beats Senses');");
        db.execSQL("INSERT INTO CERVEJA VALUES(5, '8480017641397', 'Leite Condensado', 'Leite Condensado');");

        db.execSQL("CREATE TABLE IF NOT EXISTS ESTABELECIMENTO(  " +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "ESTABELECIMENTO VARCHAR(30) NOT NULL, " +
                "COD_CIDADE INTEGER NOT NULL)");

        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (1, 'Carrefour', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (2, 'Sumerbol', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (3, 'Paulistão', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (4, 'Disk Breja', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (5, 'Cervejaria do Alemão', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (6, 'Pão de Açucar', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (7, 'Extra', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (8, 'Walmart', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (9, 'Tenda', 2);");
        db.execSQL("INSERT INTO ESTABELECIMENTO VALUES (10, 'Cato', 2);");

        db.execSQL("CREATE TABLE IF NOT EXISTS PRECO( " +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "PRECO REAL NOT NULL, " +
                "COD_CERVEJA INTEGER NOT NULL, " +
                "COD_ESTABELECIMENTO INTEGER NOT NULL)");

        db.execSQL("INSERT INTO PRECO VALUES (1, 3.32, 1, 1);");
        db.execSQL("INSERT INTO PRECO VALUES (2, 4.41, 1, 2);");
        db.execSQL("INSERT INTO PRECO VALUES (3, 5.11, 1, 3);");
        db.execSQL("INSERT INTO PRECO VALUES (4, 3.41, 1, 4);");
        db.execSQL("INSERT INTO PRECO VALUES (5, 2.21, 1, 5);");
        db.execSQL("INSERT INTO PRECO VALUES (6, 1.50, 1, 6);");
        db.execSQL("INSERT INTO PRECO VALUES (7, 2.10, 1, 7);");
        db.execSQL("INSERT INTO PRECO VALUES (8, 3.20, 1, 8);");
        db.execSQL("INSERT INTO PRECO VALUES (9, 4.00, 1, 9);");
        db.execSQL("INSERT INTO PRECO VALUES (10, 2.00, 1, 10);");

        db.execSQL("INSERT INTO PRECO VALUES (11, 3.32, 2, 1);");
        db.execSQL("INSERT INTO PRECO VALUES (12, 4.51, 2, 2);");
        db.execSQL("INSERT INTO PRECO VALUES (13, 4.41, 2, 3);");
        db.execSQL("INSERT INTO PRECO VALUES (14, 6.41, 2, 4);");
        db.execSQL("INSERT INTO PRECO VALUES (15, 2.21, 2, 5);");
        db.execSQL("INSERT INTO PRECO VALUES (16, 1.50, 2, 6);");
        db.execSQL("INSERT INTO PRECO VALUES (17, 1.10, 2, 7);");
        db.execSQL("INSERT INTO PRECO VALUES (18, 2.20, 2, 8);");
        db.execSQL("INSERT INTO PRECO VALUES (19, 5.00, 2, 9);");
        db.execSQL("INSERT INTO PRECO VALUES (20, 5.00, 2, 10);");

        db.execSQL("INSERT INTO PRECO VALUES (21, 3.32, 3, 1);");
        db.execSQL("INSERT INTO PRECO VALUES (22, 4.41, 3, 2);");
        db.execSQL("INSERT INTO PRECO VALUES (23, 2.41, 3, 3);");
        db.execSQL("INSERT INTO PRECO VALUES (24, 3.41, 3, 4);");
        db.execSQL("INSERT INTO PRECO VALUES (25, 2.21, 3, 5);");
        db.execSQL("INSERT INTO PRECO VALUES (26, 1.50, 3, 6);");
        db.execSQL("INSERT INTO PRECO VALUES (27, 3.10, 3, 7);");
        db.execSQL("INSERT INTO PRECO VALUES (28, 3.20, 3, 8);");
        db.execSQL("INSERT INTO PRECO VALUES (29, 4.00, 3, 9);");
        db.execSQL("INSERT INTO PRECO VALUES (30, 4.00, 3, 10);");

        db.execSQL("INSERT INTO PRECO VALUES (31, 3.32, 4, 1);");
        db.execSQL("INSERT INTO PRECO VALUES (32, 9.41, 4, 2);");
        db.execSQL("INSERT INTO PRECO VALUES (33, 4.41, 4, 3);");
        db.execSQL("INSERT INTO PRECO VALUES (34, 3.41, 4, 4);");
        db.execSQL("INSERT INTO PRECO VALUES (35, 2.21, 4, 5);");
        db.execSQL("INSERT INTO PRECO VALUES (36, 1.50, 4, 6);");
        db.execSQL("INSERT INTO PRECO VALUES (37, 2.10, 4, 7);");
        db.execSQL("INSERT INTO PRECO VALUES (38, 3.20, 4, 8);");
        db.execSQL("INSERT INTO PRECO VALUES (39, 4.00, 4, 9);");
        db.execSQL("INSERT INTO PRECO VALUES (40, 1.00, 4, 10);");
        db.execSQL("insert into preco values(41,1.79,5,1)");

        db.execSQL("CREATE  TRIGGER updateOnDelete AFTER delete ON cerveja BEGIN delete from preco where cod_cerveja = OLD._id; END;");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
