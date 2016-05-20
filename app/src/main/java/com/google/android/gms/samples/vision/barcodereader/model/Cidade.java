package com.google.android.gms.samples.vision.barcodereader.model;

/**
 * Created by Cleber T. Moreira on 19/03/2016.
 */
public class Cidade {
    private int id;
    private String cidade;
    private int codEstado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }
}
