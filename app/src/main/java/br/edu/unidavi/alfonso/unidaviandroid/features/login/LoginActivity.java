package br.edu.unidavi.alfonso.unidaviandroid.features.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.alfonso.unidaviandroid.features.home.MainActivity;
import br.edu.unidavi.alfonso.unidaviandroid.R;
import br.edu.unidavi.alfonso.unidaviandroid.session.Session;
import br.edu.unidavi.alfonso.unidaviandroid.utils.User;
import br.edu.unidavi.alfonso.unidaviandroid.utils.WebTaskLogin;

/**
 * Created by Alfonso on 17/02/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private Session session = new Session();
    private WebTaskLogin mAuthTask = null;
    private ProgressDialog progressDialog = null;
    private View formLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ImageView btnCancelar = (ImageView) findViewById(R.id.btnCancel);

        String ultimoLogin = session.getEmailInSession(getApplicationContext());
        EditText edLogin = (EditText)findViewById(R.id.login);
        edLogin.setText(ultimoLogin);

        EditText edSenha = (EditText)findViewById(R.id.senha);
        if (!ultimoLogin.equals("")) {
            edSenha.requestFocus();
        }

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login();
                    }
                }
        );

        formLogin = findViewById(R.id.formulario);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void sair(View v) {
        finish();
    }

    public void login() {

        EditText login = (EditText) findViewById(R.id.login);
        String loginValue = login.getText().toString();

        EditText senha = (EditText) findViewById(R.id.senha);
        String senhaValue = senha.getText().toString();

        showDialog();
        mAuthTask = new WebTaskLogin(this, loginValue, senhaValue);
        mAuthTask.execute();

        /*
        if ("teste".equals(loginValue) && "teste".equals(senhaValue)) {
            Log.d("DEBUG", "Deu certo!!");

            session.saveEmailInSession(this, loginValue);
            goToHome();

        } else {
            Log.d("DEBUG", "Deu errado!!");
        }
        */
    }

    private void goToHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        // Destruir a lista de Activities abertas
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Enviar parâmetros para a próxima Activity
        //intent.putExtra(FIELD_USERNAME, loginValue);

        startActivity(intent);
        finish();
    }

    public void esqueci(View v) {

    }

    @Subscribe
    public void onEvent(User user){
        mAuthTask = null;
        hideDialog();

        session.saveEmailInSession(this, user.getUsuario());
        session.saveInSession(this, "sessao", "nome", user.getNome());
        session.saveInSession(this, "sessao", "imagem", user.getImagemURL());
        session.saveInSession(this, "sessao", "token", user.getToken());
        goToHome();

    }

    @Subscribe
    public void onEvent(Error error){
        mAuthTask = null;
        hideDialog();

        Snackbar.make(formLogin, "error.getMessage()", Snackbar.LENGTH_LONG).show();

        //Toast toast = Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT);
        //toast.show();
    }

    public void showDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.message_wait));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}
