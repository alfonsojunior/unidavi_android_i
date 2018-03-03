package br.edu.unidavi.alfonso.unidaviandroid.utils;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.edu.unidavi.alfonso.unidaviandroid.R;

/**
 * Created by Alfonso on 03/03/2018.
 */

public class WebTaskLogin extends WebTaskBase {

    private static String SERVICE_URL = "login";
    private String email;
    private String password;

    public WebTaskLogin(Context context, String email, String password){
        super(context, SERVICE_URL);
        this.email = email;
        this.password = password;
    }

    @Override
    public void handleResponse(String response) {
        User user = new User();
        try {
            JSONObject responseAsJSON = new JSONObject(response);
            String name = responseAsJSON.getString("nome");
            user.setNome(name);
            String username = responseAsJSON.getString("usuario");
            user.setUsuario(username);
            String photoUrl = responseAsJSON.getString("imagem");
            user.setImagemURL(photoUrl);
            EventBus.getDefault().post(user);

        } catch (JSONException e) {
            if(!isSilent()){
                EventBus.getDefault().post(new Error(getContext().getString(R.string.label_error_invalid_response)));
            }
        }
    }

    private User readUser(JSONObject userAsJSON)  throws  JSONException{
        User user = new User();
        user.setNome(userAsJSON.getString("nome"));
        user.setUsuario(userAsJSON.getString("usuario"));
        user.setImagemURL(userAsJSON.getString("imagem"));
        return user;
    }

    @Override
    public String getRequestBody(){
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("usuario", email);
        requestMap.put("senha", password);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return  jsonString;
    }

}
