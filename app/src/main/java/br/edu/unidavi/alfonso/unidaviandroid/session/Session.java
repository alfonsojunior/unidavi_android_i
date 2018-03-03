package br.edu.unidavi.alfonso.unidaviandroid.session;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Alfonso on 02/03/2018.
 */

public class Session {

    private final String FIELD_USERNAME = "username";
    private final String PREF_NAME = "sessao";

    public void saveInSession(Context ctx, String sessionNome, String campo, String valor) {

        SharedPreferences sharedPref = ctx.getSharedPreferences(sessionNome, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(campo, valor);
        editor.commit();

    }

    public String getInSession(Context ctx, String sessionNome, String campo) {

        SharedPreferences sharedPref = ctx.getSharedPreferences(sessionNome, Context.MODE_PRIVATE);
        return sharedPref.getString(campo, "");

    }

    public void saveEmailInSession(Context ctx, String valor) {

        SharedPreferences sharedPref = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(FIELD_USERNAME, valor);

        EventBus.getDefault().postSticky("INFORMAÇÃO");
        // Recuperar a informação do EventBus
        // Uma informação por tipo
        //String mensagem = EventBus.getDefault().removeStickyEvent(String.class);

        editor.commit();

    }

    public String getEmailInSession(Context ctx) {

        SharedPreferences sharedPref = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(FIELD_USERNAME, "");

    }

}
