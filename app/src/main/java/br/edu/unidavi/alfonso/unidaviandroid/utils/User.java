package br.edu.unidavi.alfonso.unidaviandroid.utils;

/**
 * Created by Alfonso on 03/03/2018.
 */

public class User {

    private String nome = "";
    private String usuario = "";
    private String imagemURL = "";
    private String token = "";


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
