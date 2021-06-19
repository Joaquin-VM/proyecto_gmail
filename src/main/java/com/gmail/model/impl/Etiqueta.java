package com.gmail.model.impl;

import com.gmail.dto.EtiquetaDTO;
import com.gmail.model.AbsEtiqueta;

class Etiqueta extends AbsEtiqueta {

  public Etiqueta() {
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

  @Override
  public String toString() {
    return "Etiqueta{" +
        "idEtiqueta=" + idEtiqueta +
        ", nombreEtiqueta='" + nombreEtiqueta + '\'' +
        ", idUsuario=" + idUsuario +
        '}';
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Etiqueta)) {
      return false;
    }

    AbsEtiqueta etiqueta = (AbsEtiqueta) o;

    return idEtiqueta == etiqueta.getIdEtiqueta();
  }

  @Override
  public int hashCode() {
    return this.idEtiqueta +
        this.nombreEtiqueta.length() +
        this.idUsuario;
  }

}
