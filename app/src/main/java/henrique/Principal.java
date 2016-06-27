package henrique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.R;

import java.security.NoSuchAlgorithmException;

public class Principal extends AppCompatActivity {
    BancoDados banco = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
    }

    public void clickLogin(View v) throws NoSuchAlgorithmException {
        Crypto senhacr = new CryptoSHA512();
        EditText a = (EditText)findViewById(R.id.edtUsuario);
        String user = a.getText().toString();
        EditText b = (EditText)findViewById(R.id.edtSenha);
        String senha = senhacr.encrypt(b.getText().toString());

        String password = banco.buscaSenha(user);

        if(senha.equals(password)){
            Intent i = new Intent(Principal.this,Logedin.class);
            startActivity(i);
        }
        else{
            Toast err = Toast.makeText(Principal.this, "Usuário e senha não coincidem!", Toast.LENGTH_SHORT);
            err.show();
        }
    }


    public void clickSignup(View v){
        if(v.getId() == R.id.btnSignup){
            Intent i = new Intent(Principal.this, SignUp.class);
            startActivity(i);
        }
    }

}
