package com.gmail.model;

import com.gmail.dto.EtiquetaDTO;

class Etiqueta extends AbsEtiqueta {

    public Etiqueta(){
        super();
    }

    public Etiqueta(EtiquetaDTO dto) {
        super(dto);
    }

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
