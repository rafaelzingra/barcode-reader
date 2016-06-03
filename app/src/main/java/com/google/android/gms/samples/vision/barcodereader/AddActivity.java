package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.control.CervejaDao;
import com.google.android.gms.samples.vision.barcodereader.control.CidadeDao;
import com.google.android.gms.samples.vision.barcodereader.control.EstadoDao;
import com.google.android.gms.samples.vision.barcodereader.dataBase.DataBase;

public class AddActivity extends Activity implements View.OnClickListener {

    private Spinner spnEstados;
    private Spinner spnCidades;

    private ArrayAdapter<String> adpEstados;
    private ArrayAdapter<String> adpCidades;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private CervejaDao cervejaDao;
    private EstadoDao estadoDao;
    private CidadeDao cidadeDao;

    private TextView txtNomeCerveja;
    private EditText nomeCerveja;
    private EditText precoCerveja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        carregarSpnEstados();
        carregarSpnCidades();

        txtNomeCerveja = (TextView)findViewById(R.id.lbl_nome_cerveja);
        Bundle bundle = getIntent().getExtras();

        nomeCerveja = (EditText) findViewById(R.id.edt_estabelecimento);
        precoCerveja = (EditText) findViewById(R.id.edt_preco);



        if(bundle.containsKey("CERVEJA")){
            txtNomeCerveja.setText(bundle.getString("CERVEJA").toString());
        }

        findViewById(R.id.btn_voltar).setOnClickListener(this);
        findViewById(R.id.btn_add_valor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_voltar){
            finish();
        } else {
            if(v.getId() == R.id.btn_add_valor){
                //conn.execSQL("select * from cerveja");

                Cursor resultSet = conn.rawQuery("Select * from cerveja", null);
                resultSet.moveToFirst();
                while (resultSet.moveToNext()) {
                    Toast.makeText(getApplicationContext(), resultSet.getString(2), Toast.LENGTH_SHORT).show();
                }
                /*String barcode = resultSet.getString(1);
                String marca = resultSet.getString(2);*/

                /*AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
                mensagem.setMessage();
                mensagem.setNeutralButton("OK", null);
                mensagem.show();*/
            }
        }
    }

    private void carregarSpnEstados(){

        spnEstados = (Spinner)findViewById(R.id.spn_estados);

        adpEstados = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        estadoDao = new EstadoDao(conn);

        for(int i = 0; i < estadoDao.buscraEstados().size(); i++){
            adpEstados.add(estadoDao.buscraEstados().get(i).getSigla().toString());
        }

        spnEstados.setAdapter(adpEstados);
    }

    private void carregarSpnCidades(){

        spnCidades = (Spinner)findViewById(R.id.spn_cidades);

        adpCidades = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpCidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        cidadeDao = new CidadeDao(conn);

        for(int i = 0; i < cidadeDao.buscraCidades().size(); i++){
            adpCidades.add(cidadeDao.buscraCidades().get(i).getCidade().toString());
        }

        spnCidades.setAdapter(adpCidades);
    }
}
