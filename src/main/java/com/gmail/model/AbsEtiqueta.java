package com.gmail.model;

import com.gmail.dto.EtiquetaDTO;

public abstract class AbsEtiqueta implements Cloneable {

  protected int idEtiqueta;
  protected String nombreEtiqueta;
  protected int idUsuario;

  public AbsEtiqueta() {
  }

  public AbsEtiqueta(EtiquetaDTO dto) {
    this.idEtiqueta = dto.getIdEtiqueta();
    this.nombreEtiqueta = dto.getNombreEtiqueta();
    this.idUsuario = dto.getIdUsuario();
  }

  public abstract int getIdEtiqueta();

  public abstract AbsEtiqueta setIdEtiqueta(int idEtiqueta);

  public abstract String getNombreEtiqueta();

  public abstract AbsEtiqueta setNombreEtiqueta(String nombreEtiqueta);

  public abstract int getIdUsuario();

  public abstract AbsEtiqueta setIdUsuario(int idUsuario);

  @Override
  public abstract String toString();

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}