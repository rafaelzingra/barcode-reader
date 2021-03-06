/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.barcodereader.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.samples.vision.barcodereader.R;
import com.google.android.gms.samples.vision.barcodereader.dataBase.DataBase;
import com.google.android.gms.samples.vision.barcodereader.model.CervejaDao;
import com.google.android.gms.samples.vision.barcodereader.model.PrecoDao;
import com.google.android.gms.vision.barcode.Barcode;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final int RC_BARCODE_CAPTURE_ADD = 9002;
    private static final String TAG = "C.barras principal";

    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;
    private TextView marcaValue;
    private TextView mensagem;
    private TextView precoMedio;
    private TextView menorPreco;
    private TextView menorPreco_local;
    private TextView maiorPreco;
    private TextView maiorPreco_local;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private CervejaDao cervejaDao;
    private PrecoDao precoDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = (TextView)findViewById(R.id.lbl_cerveja);
        barcodeValue = (TextView)findViewById(R.id.barcode_value);
        marcaValue = (TextView) findViewById(R.id.marca_value);
        mensagem = (TextView) findViewById(R.id.mensagem);
        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);
        precoMedio = (TextView) findViewById(R.id.precoMedio_value);
        menorPreco = (TextView) findViewById(R.id.menorPreco_value);
        menorPreco_local = (TextView) findViewById(R.id.menorPreco_local);
        maiorPreco = (TextView) findViewById(R.id.maiorPreco_value);
        maiorPreco_local = (TextView) findViewById(R.id.maiorPreco_local);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            /*AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
            mensagem.setMessage("Base de dados criada com sucesso");
            mensagem.setNeutralButton("OK", null);
            mensagem.show();*/
        } catch (SQLiteException ex) {
            AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
            mensagem.setMessage("Erro ao criar base de dados" + ex.getMessage());
            mensagem.setNeutralButton("OK", null);
            mensagem.show();
        }


        findViewById(R.id.read_barcode).setOnClickListener(this);
        findViewById(R.id.btn_adicionar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());
            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        } else {
            if (v.getId() == R.id.btn_adicionar) {

                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("CERVEJA", barcodeValue.getText().toString());
                startActivity(intent);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);

                    dataBase = new DataBase(this);
                    conn = dataBase.getWritableDatabase();
                    cervejaDao = new CervejaDao(conn);
                    precoDao = new PrecoDao(conn);

                    for (int i = 0; i < cervejaDao.buscraCervejas().size(); i++) {
                        if (barcode.displayValue.trim().equals(cervejaDao.buscraCervejas().get(i).getBarcode())) {
                            barcodeValue.setText("Nome: " + cervejaDao.buscraCervejas().get(i).getRotulo().toString().toUpperCase());

                            marcaValue.setText("Marca: " + cervejaDao.buscraCervejas().get(i).getMarca().toString().toUpperCase());
                            precoMedio.setText("Preço Médio: " + precoDao.getPrecoMedio(cervejaDao.buscraCervejas().get(i).get_id()));
                            menorPreco.setText("Menor preço: 1.99");
                            menorPreco_local.setText("Local: Cato");
                            maiorPreco.setText("Maior preço: 2.15");
                            maiorPreco_local.setText("Local: Pao de Açucar");



                        }
                       /*Cerveja cerveja = cervejaDao.getCerveja(barcode.displayValue.trim());
                        if(cerveja != null)
                        {
                            //barcodeValue.setText(cerveja.getRotulo().toString());
                            //textView2.setText(cerveja.getMarca());

                        }*/
                        else {
                            barcodeValue.setText(barcode.displayValue.trim().toString());
                           /* {
                                   String tmp = (barcode == null)?"nur":barcode.displayValue.trim().toString();
                               if (mensagem==null)
                                   Toast.makeText(getApplicationContext(),"nurrr",Toast.LENGTH_LONG);
                                   else{
                                    mensagem.setText(tmp);}
                                Toast.makeText(getApplicationContext(),tmp,Toast.LENGTH_LONG);



                            }*/
                            // mensagem.setText("Teste");

                        }
                    }

                    Log.d(TAG, "Leitura de código de barras: " + barcode.displayValue);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "Código de barras não detectado, dados da intent nulos");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
