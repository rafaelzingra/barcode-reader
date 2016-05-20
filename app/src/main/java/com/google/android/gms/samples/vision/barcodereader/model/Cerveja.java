package com.google.android.gms.samples.vision.barcodereader.model;

/**
 * Created by Cleber T. Moreira on 10/03/2016.
 */
public class Cerveja {
    private int _id;
    private String barcode;
    private String marca;
    private String rotulo;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
