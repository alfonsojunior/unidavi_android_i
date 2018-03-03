package br.edu.unidavi.alfonso.unidaviandroid.features.home;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.edu.unidavi.alfonso.unidaviandroid.R;
import br.edu.unidavi.alfonso.unidaviandroid.session.Session;

public class MainActivity extends AppCompatActivity {

    private Session session = new Session();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagem = (ImageView) findViewById(R.id.img);

        String url = session.getInSession(this, "sessao", "imagem");//"https://www.w3schools.com/css/trolltunga.jpg";
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.coruja)
                .error(R.drawable.coruja)
                .into(imagem);

        String mensagem = EventBus.getDefault().removeStickyEvent(String.class);
        Log.d("DEBUG", mensagem);

        // Carrega parâmetro enviado
        //String username = getIntent().getStringExtra(LoginActivity.FIELD_USERNAME);
        Log.d("LIFECICLE", "CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECICLE", "START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECICLE", "RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECICLE", "PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECICLE", "STOP");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFECICLE", "RESTART");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECICLE", "DESTROY");
    }
}
