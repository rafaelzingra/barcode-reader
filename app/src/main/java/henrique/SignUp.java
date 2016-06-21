package henrique;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.R;

/**
 * Created by henrique.5in on 20/06/16.
 */
public class SignUp extends Activity {
    BancoDados banco = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public void clickGravar(View v){
        if(v.getId()== R.id.btnLogin){
            EditText nome = (EditText)findViewById(R.id.edtNome);
            EditText email = (EditText)findViewById(R.id.edtEmail);
            EditText senha = (EditText)findViewById(R.id.edtSenha);
            EditText senha2 = (EditText)findViewById(R.id.edtConfirmaSenha);

            String strnome = nome.getText().toString();
            String stremail = email.getText().toString();
            String strsenha = senha.getText().toString();
            String strsenha2 = senha2.getText().toString();

            if(!strsenha.equals(strsenha2)){
                Toast erro = Toast.makeText(SignUp.this, "As senhas não conferem", Toast.LENGTH_SHORT);
                erro.show();
            }
            else
            {
                Usuario user = new Usuario();
                user.setNome(strnome);
                user.setEmail(stremail);
                user.setSenha(strsenha);

                banco.insereUsuario(user);
            }
        }
    }
}
