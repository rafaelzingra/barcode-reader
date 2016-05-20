package com.google.android.gms.samples.vision.barcodereader.model;

/**
 * Created by Cleber T. Moreira on 15/03/2016.
 */
public class Estado {
    private int _id;
    private String estado;
    private String sigla;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
