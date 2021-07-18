package com.gmail.model;

import com.gmail.dto.FiltroDTO;

public abstract class AbsFiltro implements Cloneable {

  protected int idFiltro;
  protected int idUsuario;
  protected int idEmisor;
  protected int idReceptor;
  protected String asunto;
  protected String contiene;
  protected Boolean leido;
  protected Boolean destacar;
  protected Boolean importante;
  protected Boolean eliminar;
  protected Boolean spam;
  protected int idEtiqueta;
  protected int idUsuarioReenviar;

  public AbsFiltro() {
  }

  public AbsFiltro(FiltroDTO dto) {
    this.idFiltro = dto.getIdFiltro();
    this.idUsuario = dto.getIdUsuario();
    this.idEmisor = dto.getIdEmisor();
    this.idReceptor = dto.getIdReceptor();
    this.asunto = dto.getAsunto();
    this.contiene = dto.getContiene();
    this.leido = dto.getLeido();
    this.destacar = dto.getDestacar();
    this.importante = dto.getImportante();
    this.eliminar = dto.getEliminar();
    this.spam = dto.getSpam();
    this.idEtiqueta = dto.getIdEtiqueta();
    this.idUsuarioReenviar = dto.getIdUsuarioReenviar();
  }

  public abstract int getIdFiltro();

  public abstract AbsFiltro setIdFiltro(int idUsuario);

  public abstract int getIdUsuario();

  public abstract AbsFiltro setIdUsuario(int idUsuario);

  public abstract int getIdEmisor();

  public abstract AbsFiltro setIdEmisor(int idEmisor);

  public abstract int getIdReceptor();

  public abstract AbsFiltro setIdReceptor(int idReceptor);

  public abstract String getAsunto();

  public abstract AbsFiltro setAsunto(String asunto);

  public abstract String getContiene();

  public abstract AbsFiltro setContiene(String contiene);

  public abstract Boolean getLeido();

  public abstract AbsFiltro setLeido(Boolean leido);

  public abstract Boolean getDestacar();

  public abstract AbsFiltro setDestacar(Boolean destacar);

  public abstract Boolean getImportante();

  public abstract AbsFiltro setImportante(Boolean importante);

  public abstract Boolean getEliminar();

  public abstract AbsFiltro setEliminar(Boolean eliminar);

  public abstract Boolean getSpam();

  public abstract AbsFiltro setSpam(Boolean spam);

  public abstract int getIdEtiqueta();

  public abstract AbsFiltro setIdEtiqueta(int idEtiqueta);

  public abstract int getIdUsuarioReenviar();

  public abstract AbsFiltro setIdUsuarioReenviar(int idUsuarioReenviar);

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
