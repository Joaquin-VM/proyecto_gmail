package com.gmail.model;

public class Etiqueta extends AbsEtiqueta {

    @Override
    public int getIdEtiqueta() {
        return this.idEtiqueta;
    }

    @Override
    public Etiqueta setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
        return this;
    }

    @Override
    public String getNombreEtiqueta() {
        return this.nombreEtiqueta;
    }

    @Override
    public Etiqueta setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
        return this;
    }

    @Override
    public int getIdUsuario() {
        return this.idUsuario;
    }

    @Override
    public Etiqueta setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

}
