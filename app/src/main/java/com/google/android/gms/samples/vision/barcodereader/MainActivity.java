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

package com.google.android.gms.samples.vision.barcodereader;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.database.sqlite.*;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.samples.vision.barcodereader.control.CervejaDao;
import com.google.android.gms.samples.vision.barcodereader.control.EstadoDao;
import com.google.android.gms.samples.vision.barcodereader.dataBase.DataBase;
import com.google.android.gms.samples.vision.barcodereader.model.Cerveja;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final int RC_BARCODE_CAPTURE_ADD = 9002;
    private static final String TAG = "C.barras principal";
    // use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private CervejaDao cervejaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = (TextView)findViewById(R.id.lbl_cerveja);
        barcodeValue = (TextView)findViewById(R.id.barcode_value);

        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

          /* ContentValues valuesCerveja = new ContentValues();
            valuesCerveja.put("BARCODE","7891027130664");
            valuesCerveja.put("MARCA","Tilibra");
            valuesCerveja.put("ROTULO", "Caderno");
            conn.insertOrThrow("CERVEJA", null, valuesCerveja);

            ContentValues valuesEstado = new ContentValues();
            valuesEstado.put("ESTADO","Acre");
            valuesEstado.put("SIGLA", "AC");
            conn.insertOrThrow("ESTADO", null, valuesEstado);

            ContentValues valuesCidade = new ContentValues();
            valuesCidade.put("CIDADE", "Indaiatuba");
            valuesCidade.put("COD_ESTADO", "1");
            conn.insertOrThrow("CIDADE", null, valuesCidade);

            valuesCidade.put("CIDADE", "Campinas");
            valuesCidade.put("COD_ESTADO", "1");
            conn.insertOrThrow("CIDADE", null, valuesCidade);

            ContentValues valuesEstabelecimento = new ContentValues();
            valuesEstabelecimento.put("ESTABELECIMENTO","Cato");
            valuesEstabelecimento.put("COD_CIDADE", "1");
            conn.insertOrThrow("ESTABELECIMENTO", null, valuesEstabelecimento);

            ContentValues valuesPreco = new ContentValues();
            valuesPreco.put("PRECO","Cato");
            valuesPreco.put("COD_CERVEJA","1");
            valuesPreco.put("COD_ESTABELECIMENTO", "1");
            conn.insertOrThrow("PRECO", null, valuesPreco);*/

            AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
            mensagem.setMessage("Base de dados criada com sucesso");
            mensagem.setNeutralButton("OK", null);
            mensagem.show();
        }catch (SQLiteException ex){
            AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
            mensagem.setMessage("Erro ao criar base de dados" + ex.getMessage());
            mensagem.setNeutralButton("OK", null);
            mensagem.show();
        }


        findViewById(R.id.read_barcode).setOnClickListener(this);
        findViewById(R.id.btn_adicionar).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());
            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }else{
            if(v.getId() == R.id.btn_adicionar){
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("CERVEJA", barcodeValue.getText().toString());
                startActivity(intent);
            }
        }
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
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

                    for(int i = 0; i < cervejaDao.buscraCervejas().size(); i++){
                        if(barcode.displayValue.trim().equals(cervejaDao.buscraCervejas().get(i).getBarcode())){
                            barcodeValue.setText(cervejaDao.buscraCervejas().get(i).getRotulo().toString());
                            break;
                        }else{
                            barcodeValue.setText("Produto não cadastrado");
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
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
