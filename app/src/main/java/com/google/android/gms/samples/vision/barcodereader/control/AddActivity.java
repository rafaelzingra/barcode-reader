package com.google.android.gms.samples.vision.barcodereader.control;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.R;
import com.google.android.gms.samples.vision.barcodereader.dataBase.DataBase;
import com.google.android.gms.samples.vision.barcodereader.model.Cerveja;
import com.google.android.gms.samples.vision.barcodereader.model.CervejaDao;
import com.google.android.gms.samples.vision.barcodereader.model.CidadeDao;
import com.google.android.gms.samples.vision.barcodereader.model.EstabelecimentoDao;
import com.google.android.gms.samples.vision.barcodereader.model.EstadoDao;
import com.google.android.gms.samples.vision.barcodereader.model.Preco;
import com.google.android.gms.samples.vision.barcodereader.model.PrecoDao;

public class AddActivity extends Activity implements View.OnClickListener {

    private Spinner spnEstados;
    private Spinner spnCidades;

    private ArrayAdapter<String> adpEstados;
    private ArrayAdapter<String> adpCidades;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    EstabelecimentoDao estabelecimentoDao = new EstabelecimentoDao(conn);
    CervejaDao cervejaDao = new CervejaDao(conn);
    PrecoDao precoDao = new PrecoDao(conn);
    private EstadoDao estadoDao;
    private CidadeDao cidadeDao;
    private TextView txtNomeCerveja;
    private EditText estabelecimentoCerveja;
    private EditText precoCerveja;
    private EditText marcaCerveja;
    private EditText nomeCerveja;
    private TextView barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        carregarSpnEstados();
        carregarSpnCidades();

        txtNomeCerveja = (TextView)findViewById(R.id.lbl_nome_cerveja);
        Bundle bundle = getIntent().getExtras();

        estabelecimentoCerveja = (EditText) findViewById(R.id.edt_estabelecimento);
        precoCerveja = (EditText) findViewById(R.id.edt_preco);

        marcaCerveja = (EditText) findViewById(R.id.edt_marca);
        nomeCerveja = (EditText) findViewById(R.id.edt_nomeCerveja);
        barcode = (TextView) findViewById(R.id.lbl_nome_cerveja);



        if(bundle.containsKey("CERVEJA")){
            txtNomeCerveja.setText(bundle.getString("CERVEJA").toString());
        }

        findViewById(R.id.btn_voltar).setOnClickListener(this);
        findViewById(R.id.btn_add_valor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_voltar) {
            finish();
        } else {
            if(v.getId() == R.id.btn_add_valor){

               /*Toast.makeText(getApplicationContext(), spnEstados.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), spnCidades.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), estabelecimentoCerveja.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), precoCerveja.getText(), Toast.LENGTH_SHORT).show();*/
                try {
                    try {
                        try {
                            Cerveja cerveja = new Cerveja();
                            cerveja.setBarcode(barcode.getText().toString());
                            cerveja.setMarca(marcaCerveja.getText().toString());
                            cerveja.setRotulo(nomeCerveja.getText().toString());
                            conn = dataBase.getWritableDatabase();
                            cervejaDao = new CervejaDao(conn);
                            cervejaDao.adicionarCerveja(cerveja);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        conn = dataBase.getWritableDatabase();
                        if (estabelecimentoDao.verificaEstabelecimento(estabelecimentoCerveja.getText().toString()) == null) {
                            estabelecimentoDao.insertEstabelecimento(estabelecimentoCerveja.getText().toString(), spnCidades.getSelectedItem().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Estabelecimento já cadastrado", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Preco preco = new Preco();
                    preco.setPreco(Double.parseDouble(precoCerveja.getText().toString()));
                    //preco.setCod_cerveja(cervejaDao.getCodigo());
                    preco.setCod_estabelecimento(estabelecimentoDao.getCodigo(estabelecimentoCerveja.getText().toString()));
                    conn = dataBase.getWritableDatabase();
                    precoDao = new PrecoDao(conn);
                    precoDao.adicionarPreco(preco);

                } catch (Exception e) {

                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Obrigado pela contribuição!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                /*Cursor resultSet = conn.rawQuery("Select * from cerveja", null);
                resultSet.moveToFirst();
                while (resultSet.moveToNext())
                {Toast
                    .makeText(getApplicationContext(), resultSet.getString(2), Toast.LENGTH_SHORT).show();
                }*/
                /*String barcode = resultSet.getString(1);
                String marca = resultSet.getString(2);*/

                /*AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
                mensagem.setMessage();
                mensagem.setNeutralButton("OK", null);
                mensagem.show();*/
            }
        }
    }

    private void carregarSpnEstados() {

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

    private void carregarSpnCidades() {

        spnCidades = (Spinner)findViewById(R.id.spn_cidades);

        adpCidades = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpCidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();
        cidadeDao = new CidadeDao(conn);

        for (int i = 0; i < cidadeDao.buscraCidades().size(); i++) {
            adpCidades.add(cidadeDao.buscraCidades().get(i).getCidade().toString());
        }

        spnCidades.setAdapter(adpCidades);
    }
}
