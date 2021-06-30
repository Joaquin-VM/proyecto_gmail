package com.gmail.dto;

import com.gmail.model.AbsEtiqueta;

public class EtiquetaDTO {

  int idEtiqueta;
  String nombreEtiqueta;
  int idUsuario;

  public EtiquetaDTO() {

  }

  public EtiquetaDTO(AbsEtiqueta etiqueta) {
    this.idEtiqueta = etiqueta.getIdEtiqueta();
    this.nombreEtiqueta = etiqueta.getNombreEtiqueta();
    this.idUsuario = etiqueta.getIdUsuario();
  }

  public int getIdEtiqueta() {
    return idEtiqueta;
  }

  public EtiquetaDTO setIdEtiqueta(int idEtiqueta) {
    this.idEtiqueta = idEtiqueta;
    return this;
  }

  public String getNombreEtiqueta() {
    return nombreEtiqueta;
  }

  public EtiquetaDTO setNombreEtiqueta(String nombreEtiqueta) {
    this.nombreEtiqueta = nombreEtiqueta;
    return this;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public EtiquetaDTO setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
    return this;
  }

}
